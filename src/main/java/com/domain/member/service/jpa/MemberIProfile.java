package com.domain.member.service.jpa;

import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;

public interface MemberIProfile {


    Member editProfile(Long memberId, MemberDto member);

//    void changePassword(Long memberId, String password);

}
