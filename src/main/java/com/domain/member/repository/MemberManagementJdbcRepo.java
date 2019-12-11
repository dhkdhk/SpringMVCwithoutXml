package com.domain.member.repository;

import com.domain.member.entity.Member;

public interface MemberManagementJdbcRepo {

    int addMemeber(Member member);

    Member getMember(Long memberId);

}
