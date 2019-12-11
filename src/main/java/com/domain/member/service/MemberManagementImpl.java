package com.domain.member.service;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.MemberManagementJpaRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class MemberManagementImpl implements MemberManagementService {

    private final MemberManagementJpaRepo memberManagementJpaRepo;
    private final ObjectMapper objectMapper;

    public MemberManagementImpl(MemberManagementJpaRepo memberManagementJpaRepo, ObjectMapper objectMapper){
        this.memberManagementJpaRepo = memberManagementJpaRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public Member addMemeber(final Member member) {
        return memberManagementJpaRepo.save(member);
    }

    @Override
    public Member updateMember(MemberDto MemberDto) {
        return null;
    }

    @Override
    public Member getMember(final Long memberId) {
        Optional<Member> memberOptional = memberManagementJpaRepo.findById(memberId);
       if(memberOptional.isEmpty()){

       }

        return null;
    }

    @Override
    public void deleteMember() {

    }
}
