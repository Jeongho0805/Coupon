package com.coupon_project.coupon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponControllerAdvice {

    @ExceptionHandler(CouponException.class)
    public ResponseEntity<Object> handleCouponException(CouponException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
