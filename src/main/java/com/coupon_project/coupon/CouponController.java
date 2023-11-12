package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import com.coupon_project.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final MemberService memberService;
    private final CouponSyncService couponSyncService;

    @PostMapping
    public ResponseEntity<Object> issueCoupon(@CookieValue(name = "account") String account) {
        String couponCode = couponSyncService.issueCouponSync(account);
        return ResponseEntity.ok().body("쿠폰 발급이 완료되었습니다. \n 쿠폰 번호 = " + couponCode);
    }
}
