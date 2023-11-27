package com.coupon_project;

import com.coupon_project.coupon.*;
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
    private CouponServiceV2 couponServiceV2;

    @Autowired
    private CouponServiceV3 couponServiceV3;

    @Autowired
    private CouponServiceV4 couponServiceV4;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 발급 갯수 테스트")
    void CouponIssueTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (long i=1; i<=200; i++) {
            Member member = memberRepository.findById(i).orElseGet(null);
            service.submit(() -> couponService.issueCoupon(member));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        Long couponCount = couponRepository.count();
        Assertions.assertThat(couponCount).isEqualTo(100);
    }

    @Test
    @DisplayName("트랜잭션 범위 다르게 테스트")
    void CouponTest2() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (long i=1; i<=200; i++) {
            Member member = memberRepository.findById(i).orElseGet(null);
            service.submit(() -> couponServiceV2.issueCoupon(member));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        Long couponCount = couponRepository.count();
        Assertions.assertThat(couponCount).isEqualTo(100);
    }

    @Test
    @DisplayName("멤버 조회 repository 안에서 하는 방식")
    void CouponTest3() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (long i=1; i<=1000; i++) {
            final long memberId = i;
            service.submit(() -> couponServiceV3.issueCoupon(memberId));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        Long couponCount = couponRepository.count();
        Assertions.assertThat(couponCount).isEqualTo(100);
    }

    @Test
    @DisplayName("비관적 락 없이 실행하면 데드락이 발생하지 않는다.")
    void notUsingPessimisticLock() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(30);

        for (long i=1; i<=200; i++) {
            Member member = memberRepository.findById(i).orElseGet(null);
            service.submit(() -> couponServiceV4.issueCoupon(member));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        Long couponCount = couponRepository.count();
        Assertions.assertThat(couponCount).isEqualTo(100);
    }
}
