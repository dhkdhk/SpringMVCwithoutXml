package com.domain.member.service.jpa;

import com.domain.member.entity.Member;

public interface MemberFinder {

    Member findMemberByMemberEmail(String email);

    Member findById(Long memberId);

}
