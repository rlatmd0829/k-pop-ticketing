package com.kpop.ticketing.domain.common.redis;

import jakarta.annotation.PostConstruct;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private static final String WAITING_QUEUE_NAME = "wait_tokens_sorted_set";
    private static final String WORKING_QUEUE_NAME = "working_tokens_set";

    private final RedisTemplate<String, String> redisTemplate;

    private ZSetOperations<String, String> zSetOperations;
    private SetOperations<String, String> setOperations;

    @PostConstruct
    private void init() {
        zSetOperations = redisTemplate.opsForZSet();
        setOperations = redisTemplate.opsForSet();
    }

    // Redis Sorted Set에 멤버를 추가하는 메서드
    public void addToWaitingQueue(Long userId, double score) {
        zSetOperations.add(WAITING_QUEUE_NAME, userId.toString(), score);
    }

    public boolean isExistWaitToken(Long userId) {
        return zSetOperations.score(WAITING_QUEUE_NAME, userId.toString()) != null;
    }

    // 사용자의 대기번호를 조회하는 메서드
    public Long getWaitNumber(Long userId) {
        Long rank = zSetOperations.rank(WAITING_QUEUE_NAME, userId.toString());
        // rank가 null이면 해당 사용자의 토큰이 없음을 의미함
        // rank가 null이 아니라면 0부터 시작하는 순번이므로 1을 더하여 대기번호를 반환
        return rank != null ? rank + 1 : null;
    }

    // 대기열에서 토큰을 pop하는 메서드
    public String popTokenFromWaitingQueue() {
        // 대기열에서 가장 먼저 들어온 토큰을 가져와서 제거
        Set<String> tokens = zSetOperations.range(WAITING_QUEUE_NAME, 0, 0);
        if (tokens != null && !tokens.isEmpty()) {
            String userId = tokens.iterator().next();
            zSetOperations.remove(WAITING_QUEUE_NAME, userId);
            return userId;
        }
        return null; // 대기열이 비어있을 경우 null 반환
    }

    public void removeFromWaitingQueue(Long userId) {
        zSetOperations.remove(WAITING_QUEUE_NAME, userId.toString());
    }

    public void addToWorkingQueue(Long userId) {
        setOperations.add(WORKING_QUEUE_NAME, userId.toString());
    }

    public boolean isUserInWorkingQueue(Long userId) {
        return setOperations.isMember(WORKING_QUEUE_NAME, userId.toString());
    }

    public void removeFromWorkingQueue(Long userId) {
        setOperations.remove(WORKING_QUEUE_NAME, userId.toString());
    }
}
