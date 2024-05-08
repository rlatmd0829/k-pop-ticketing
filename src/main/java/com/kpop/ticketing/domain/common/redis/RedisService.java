package com.kpop.ticketing.domain.common.redis;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    public void addToSortedSet(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
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
