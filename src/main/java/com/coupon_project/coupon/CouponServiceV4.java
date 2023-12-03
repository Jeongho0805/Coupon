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

    private final static int MAX_COUNT = 10000;

    private final CouponRepository couponRepository;

    private final CouponInventoryRepository couponInventoryRepository;

    @Transactional
    public String issueCoupon(Member member) {
        CouponInventory couponInventory = couponInventoryRepository.findCouponInventoryById(1L);
        int count = couponInventory.getRemainingCoupons();
        if (count >= MAX_COUNT) {
            throw new CouponException("모든 쿠폰이 소진되었습니다.");
        }
        if (!member.getCoupons().isEmpty()) {
            throw new CouponException("쿠폰 중복 발급은 불가능합니다.");
        }
        String couponCode = makeCoupon(member);
        couponInventory.plusCount();
        return couponCode;
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
