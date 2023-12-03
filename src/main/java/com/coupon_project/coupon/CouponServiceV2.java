package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceV2 {

    private final static int MAX_COUNT = 10000;
    private final CouponRepository couponRepository;
    private final CouponInventoryRepository couponInventoryRepository;

    @Transactional
    public String issueCoupon(Member member) {
        CouponInventory couponInventory = couponInventoryRepository.countWithPessimisticLock();
        log.info("couponReamining = {}" , couponInventory.getRemainingCoupons());
        if (couponInventory.getRemainingCoupons() >= MAX_COUNT) {
            throw new CouponException("모든 쿠폰이 소진되었습니다.");
        }
        if (!member.getCoupons().isEmpty()) {
            throw new CouponException("쿠폰 중복 발급은 불가능합니다.");
        }
        String code = makeCoupon(member);
        couponInventory.plusCount();
        return code;
    }

    private String makeCoupon(Member member) {
        String couponCode = UUID.randomUUID().toString().substring(0, 20);
        Coupon coupon = new Coupon(couponCode, member);
        member.getCoupons().add(coupon);
        couponRepository.save(coupon);
        return couponCode;
    }
}
