package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceV4Facade {

    private final CouponServiceV4 couponServiceV4;

    public String issueCoupon(Member member) {
        try {
            return couponServiceV4.issueCoupon(member);
        } catch (Exception e) {
            log.info("Exception 발생 => {}", e.getClass());
        }
        return null;
    }
}
