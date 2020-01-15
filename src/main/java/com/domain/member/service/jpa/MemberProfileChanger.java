package com.domain.member.service.jpa;

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
    public Member editProfile(Member member) {
        Member editMember = memberCommonRepository.findById(member.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member.memberId", member.getMemberId()));
        editMember.updateInformation(member);

        return editMember;
    }
}
