package com.domain.member.service.jpa;

import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;

public interface MemberUpdator {


    Member updateMemberInformation(Long memberId, MemberDto member);

    boolean deleteMember(Long memberId);
}
