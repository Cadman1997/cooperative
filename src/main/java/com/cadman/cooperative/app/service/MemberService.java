package com.cadman.cooperative.app.service;


import com.cadman.cooperative.app.model.Member;
import com.cadman.cooperative.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String memberId, String password) {
        return memberRepository.findByMemberId(memberId)
                .filter(member -> member.getPassword().equals(password));

    }
}


