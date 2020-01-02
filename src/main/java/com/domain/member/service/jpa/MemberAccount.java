package com.domain.member.service.jpa;

import com.domain.member.entity.Member;

public interface MemberAccount {

    boolean deleteMember(Long memberId);

    void changePassword(Long memberId, String password);

    Member ourHomePageToSignUp(Member member);
}
