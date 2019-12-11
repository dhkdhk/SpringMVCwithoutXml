package com.domain.member.repository;

import com.domain.member.entity.Member;

public interface MemberUpdateJdbcRepo {

    int addMemeber(Member member);

    Member getMember(Long memberId);

}
