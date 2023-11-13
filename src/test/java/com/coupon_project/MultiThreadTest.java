package com.coupon_project;

import com.coupon_project.coupon.CouponRepository;
import com.coupon_project.coupon.CouponService;
import com.coupon_project.member.Member;
import com.coupon_project.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MultiThreadTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 발급 갯수 테스트")
    void CouponIssueTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (long i=1; i<=1000; i++) {
            Member member = memberRepository.findById(i).orElseGet(null);
            service.submit(() -> couponService.issueCoupon(member));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        Long couponCount = couponRepository.count();
        Assertions.assertThat(couponCount).isEqualTo(100);
    }
}
