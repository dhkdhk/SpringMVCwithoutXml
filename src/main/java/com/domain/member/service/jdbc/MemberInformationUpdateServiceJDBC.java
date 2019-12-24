package com.domain.member.service.jdbc;

import com.domain.member.entity.Member;

public interface MemberInformationUpdateServiceJDBC {

    int addMemeber(Member member);

    Member getMember(Long memberId);

}
