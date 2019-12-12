package com.domain.member.service.jdbc;


import com.domain.member.entity.Member;
import com.domain.member.repository.jdbc.MemberManagementJdbcRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "dataSourceTransactionManager")
public class MemberManagementJdbcImple implements MemberManagementJdbcService{

    private final MemberManagementJdbcRepo memberManagementJdbcRepo;

    public MemberManagementJdbcImple(MemberManagementJdbcRepo memberManagementJdbcRepo){
        this.memberManagementJdbcRepo = memberManagementJdbcRepo;
    }

    @Override
    public int addMemeber(Member member) {
        return memberManagementJdbcRepo.addMemeber(member);
    }

    @Override
    public Member getMember(Long memberId) {
        return memberManagementJdbcRepo.getMember(memberId);
    }
}
