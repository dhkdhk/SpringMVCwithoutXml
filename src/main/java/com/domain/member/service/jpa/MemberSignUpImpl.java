package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberSignUpImpl implements MemberSignUp {

    private final MemberCommonRepository memberCommonRepository;

    @Override
    public Member ourHomePageToJoin(Member member) {
        return memberCommonRepository.save(member);
    }
}
