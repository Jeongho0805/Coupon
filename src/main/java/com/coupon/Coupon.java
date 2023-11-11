package com.coupon;

import javax.persistence.*;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
