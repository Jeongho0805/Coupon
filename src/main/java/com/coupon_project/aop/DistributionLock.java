package com.coupon_project.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributionLock {

    private final RedissonClient redissonClient;

    @Around("@annotation(Lock)")
    public Object getLock(ProceedingJoinPoint joinPoint) throws Throwable {
        RLock rLock = redissonClient.getLock("coupon"); // (1)
        try {
            boolean isAvailable = rLock.tryLock(100000, 100000, TimeUnit.SECONDS);
            if (!isAvailable) {
                log.info("락획득 실패");
                throw new IllegalArgumentException();
            }
            return joinPoint.proceed();
        }
        catch (InterruptedException e) {
            log.info("락획득 실패");
            throw new IllegalArgumentException();
        }
        finally {
            rLock.unlock();
        }

    }
}
