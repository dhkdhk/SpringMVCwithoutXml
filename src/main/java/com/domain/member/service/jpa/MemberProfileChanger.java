package com.domain.member.service.jpa;

import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;
import com.domain.member.exception.MemberNotFoundException;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberProfileChanger implements MemberIProfile {

    private final MemberCommonRepository memberCommonRepository;


    @Override
    public Member editProfile(final Long memberId, MemberDto memberDto) {
        Member member = memberCommonRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member.updateInformation(memberId, memberDto);

        return member;
    }


}
