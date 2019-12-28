package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpImpl implements MemberSignUp {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member ourHomePageToSignUp(Member member) {
        return memberCommonRepository.save(member);
    }
}
