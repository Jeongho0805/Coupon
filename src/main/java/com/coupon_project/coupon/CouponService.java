package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponService {

    private final int MAX_COUNT = 10000;
    private final CouponRepository couponRepository;

    @Transactional
    public String issueCoupon(Member member) {
        long count = couponRepository.count();
        if (count > MAX_COUNT) {
            throw new CouponException("모든 쿠폰이 소진되었습니다.");
        }
        if (!member.getCoupons().isEmpty()) {
            throw new CouponException("쿠폰 중복 발급은 불가능합니다.");
        }
        return makeCoupon(member);
    }

    private String makeCoupon(Member member) {
        String couponCode = UUID.randomUUID().toString().substring(0, 20);
        Coupon coupon = new Coupon(couponCode, member);
        member.getCoupons().add(coupon);
        couponRepository.save(coupon);
        return couponCode;
    }
}