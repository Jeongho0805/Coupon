package com.coupon_project.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RedisService {


    private final StringRedisTemplate redisTemplate;

    public void addToSortedSet(String value, ZonedDateTime dateTime) {
        long score = dateTime.toInstant().toEpochMilli();
        redisTemplate.opsForZSet().add("queue", value, score);
    }

    public Long getRank(String value) {
        return redisTemplate.opsForZSet().rank("queue", value);
    }
}
