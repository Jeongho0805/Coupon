package com.coupon_project;

import com.coupon_project.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.ZonedDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisQueueTest {

    @Autowired
    private RedisService redisService;

    @Test
    void redisTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (int i=1; i<=1000; i++) {
            ZonedDateTime now = ZonedDateTime.now();
            redisService.addToSortedSet(String.valueOf(i), now);
            Long rank = redisService.getRank(String.valueOf(i));
            System.out.println("rank = " + rank + " now = " + now);
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }
}
