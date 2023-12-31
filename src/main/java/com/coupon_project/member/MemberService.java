package com.coupon_project.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(String account) {
        return memberRepository.findMemberByAccount(account).orElseThrow(MemberException::new);
    }
}
