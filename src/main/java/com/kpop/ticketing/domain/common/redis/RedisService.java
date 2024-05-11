package com.kpop.ticketing.domain.common.redis;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private static final String sortedSetName = "wait_tokens_sorted_set";
    private final RedisTemplate redisTemplate;

    // Redis Sorted Set에 멤버를 추가하는 메서드
    public void addToSortedSet(Long value, double score) {
        redisTemplate.opsForZSet().add(sortedSetName, value, score);
    }

    public boolean isExistWaitToken(Long userId) {
        return redisTemplate.opsForZSet().score(sortedSetName, userId) != null;
    }

    // 사용자의 대기번호를 조회하는 메서드
    public Long getWaitNumber(Long userId) {
        Long rank = redisTemplate.opsForZSet().rank(sortedSetName, String.valueOf(userId));
        // rank가 null이면 해당 사용자의 토큰이 없음을 의미함
        // rank가 null이 아니라면 0부터 시작하는 순번이므로 1을 더하여 대기번호를 반환
        return rank != null ? rank + 1 : null;
    }

    public Set<String> getRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Set<String> getRangeByRank(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public void removeFromSortedSet(String key, String value) {
        redisTemplate.opsForZSet().remove(key, value);
    }


}
