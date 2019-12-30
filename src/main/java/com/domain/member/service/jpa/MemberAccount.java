package com.domain.member.service.jpa;

public interface MemberAccount {

    boolean deleteMember(Long memberId);

    void changePassword(Long memberId, String password);

}
