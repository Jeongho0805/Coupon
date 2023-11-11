package com.coupon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String account;

    @OneToMany(mappedBy = "member")
    private List<Coupon> coupons = new ArrayList<>();
}
