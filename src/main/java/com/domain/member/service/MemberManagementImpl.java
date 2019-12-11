package com.domain.member.service;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.MemberUpdateJpaRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional @RequiredArgsConstructor
public class MemberManagementImpl implements MemberManagementService {

    private final MemberUpdateJpaRepo memberUpdateJpaRepo;
    private final ObjectMapper objectMapper;

    @Override
    public Member addMemeber(final Member member) {
        return memberUpdateJpaRepo.save(member);
    }

    @Override
    public Member updateMember(MemberDto MemberDto) {
        return null;
    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public void deleteMember() {

    }
}
