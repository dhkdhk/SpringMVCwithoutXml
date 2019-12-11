package com.domain.member.service;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;

public interface MemberManagementService {

    Member addMemeber(Member member);

    Member updateMember(MemberDto memberDto);

    Member getMember();

    void deleteMember();
}
