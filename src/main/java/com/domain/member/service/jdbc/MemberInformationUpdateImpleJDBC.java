package com.domain.member.service.jdbc;


import com.domain.member.entity.Member;
import com.domain.member.repository.jdbc.MemberManagementJdbcRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "dataSourceTransactionManager")
public class MemberInformationUpdateImpleJDBC implements MemberInformationUpdateServiceJDBC {

    private final MemberManagementJdbcRepo memberManagementJdbcRepo;

    public MemberInformationUpdateImpleJDBC(MemberManagementJdbcRepo memberManagementJdbcRepo){
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
