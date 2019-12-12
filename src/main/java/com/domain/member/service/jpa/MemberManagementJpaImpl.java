package com.domain.member.service.jpa;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.jpa.MemberManagementJpaRepo;
import com.domain.member.service.MemberManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(transactionManager = "jpaTx")
public class MemberManagementJpaImpl implements MemberManagementService {

    private final MemberManagementJpaRepo memberManagementJpaRepo;

    public MemberManagementJpaImpl(MemberManagementJpaRepo memberManagementJpaRepo, ObjectMapper objectMapper){
        this.memberManagementJpaRepo = memberManagementJpaRepo;
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
