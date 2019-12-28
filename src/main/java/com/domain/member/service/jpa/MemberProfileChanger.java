package com.domain.member.service.jpa;

import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(transactionManager="jpaTransactionManager")
public class MemberProfileChanger implements MemberIProfile {

    private final MemberCommonRepository memberCommonRepository;


    @Override
    public Member editProfile(final Long memberId, MemberDto memberDto) {
        Member member = memberCommonRepository.findById(memberId)
                     .orElseThrow(() -> new IllegalArgumentException(" 수정 할 수 없습니다. 입력값을 다시 학번확인해주세요"));

        member.updateInformation(memberId, memberDto);

        return member;
    }



}
