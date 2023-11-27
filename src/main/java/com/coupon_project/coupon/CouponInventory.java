package com.coupon_project.coupon;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CouponInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int remainingCoupons;

    @Version
    private Integer version;

    public void plusCount() {
        this.remainingCoupons++;
    }

}
