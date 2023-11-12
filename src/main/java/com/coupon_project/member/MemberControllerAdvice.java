package com.coupon_project.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<Object> handleMemberException(MemberException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
