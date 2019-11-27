package com.domain.service;

import com.domain.entity.Member;

public interface MemberService {

    int addMemeber(Member member);

    void updateMember();

    Member getMember();

    void deleteMember();
}
