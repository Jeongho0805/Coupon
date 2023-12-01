package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.UUID;

/**
 * 낙관적 락
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceV4 {

    private final static int MAX_COUNT = 100;

    private final CouponRepository couponRepository;

    private final CouponInventoryRepository couponInventoryRepository;

    @Transactional
    public String issueCoupon() {
        try {
            CouponInventory couponInventory = couponInventoryRepository.findCouponInventoryById(1L);
            couponInventory.plusCount();
            return null;
        } catch (OptimisticLockingFailureException e) {
            log.info("에러 발생");
            log.info("message = {}", e.getMessage());
        }
        return null;
    }

    @Transactional
    public String makeCoupon(Member member) {
        String couponCode = UUID.randomUUID().toString().substring(0, 20);
        Coupon coupon = new Coupon(couponCode, member);
        member.getCoupons().add(coupon);
        couponRepository.save(coupon);
        return couponCode;
    }
}
