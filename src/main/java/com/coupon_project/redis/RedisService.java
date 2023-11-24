package com.coupon_project.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RedisService {


    private final StringRedisTemplate redisTemplate;

    // ZADD: Sorted Set에 요소 추가
    public void addToSortedSet(String value, ZonedDateTime dateTime) {
        redisTemplate.opsForZSet().add("queue", value, dateTime.toEpochSecond());
    }

    // ZRANK: 요소의 순위 반환
    public Long getRank(String value) {
        return redisTemplate.opsForZSet().rank("queue", value);
    }
}
