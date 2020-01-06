package com.domain.member.service.jpa;

public interface MemberAccount {

    void deleteMember(Long memberId);

    void changePassword(Long memberId, String password);

}
