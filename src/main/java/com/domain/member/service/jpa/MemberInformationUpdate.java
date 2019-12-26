package com.domain.member.service.jpa;

import com.domain.member.entity.Member;

public interface MemberInformationUpdate {


    int updateMemberInformation(Member member);

    boolean deleteMember(Long memberId);
}
