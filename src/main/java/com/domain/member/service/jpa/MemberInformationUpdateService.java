package com.domain.member.service.jpa;

import com.domain.member.entity.Member;

import java.util.Optional;

public interface MemberInformationUpdateService {

    Member addMember(Member member);

    Optional<Member> updateMember();

    Member getMember(Long memberId);

    boolean deleteMember(Long memberId);

}
