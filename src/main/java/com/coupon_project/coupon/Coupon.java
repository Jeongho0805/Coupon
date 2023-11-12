package com.coupon_project.coupon;

import com.coupon_project.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Coupon(String code, Member member) {
        this.code = code;
        this.member = member;
    }
}
