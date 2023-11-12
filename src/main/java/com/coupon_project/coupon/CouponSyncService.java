package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import com.coupon_project.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponSyncService {

    private final CouponService couponService;
    private final MemberService memberService;

    public synchronized String issueCouponSync(String account) {
        Member findMember = memberService.getMember(account);
        return couponService.issueCoupon(findMember);
    }
}
