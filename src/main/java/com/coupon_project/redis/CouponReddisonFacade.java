package com.coupon_project.redis;

import com.coupon_project.coupon.CouponService;
import com.coupon_project.member.Member;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CouponReddisonFacade {

    private final RedissonClient redissonClient;

    private final CouponService couponService;

    public void executeDistributionLock(Member member) throws InterruptedException {

        RLock rLock = redissonClient.getLock("coupon"); // (1)
        try {
            boolean isAvailable = rLock.tryLock(1000, TimeUnit.SECONDS);
            if (!isAvailable) {
                return;
            }
            couponService.issueCoupon(member);
        } finally {
            rLock.unlock();
        }

    }
}
