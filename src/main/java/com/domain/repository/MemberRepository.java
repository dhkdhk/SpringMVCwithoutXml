package com.domain.repository;

import com.domain.entity.Member;

public interface MemberRepository {

    int addMemeber(Member member);

    void updateMember();

    Member getMember(Long memberId);

    void deleteMember();
}
