package com.domain.member.support;

import com.domain.member.dto.MemberRequestDto;
import com.domain.member.dto.MemberResponseDto;
import com.domain.member.entity.Member;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelMappingUtil {

    public static MemberResponseDto entityToDto(Member member) {
        return MemberResponseDto.builder()
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberAge(member.getMemberAge())
                .memberSex(member.getMemberSex())
                .memberAddress(member.getMemberAddress())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .roles(member.getRoles())
                .memberGrade(member.getMemberGrade())
                .accountEnable(member.getAccountEnable())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }

    public static Member dtoToEntity(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .memberId(memberRequestDto.getMemberId())
                .memberPassword(memberRequestDto.getMemberPassword())
                .memberName(memberRequestDto.getMemberName())
                .memberEmail(memberRequestDto.getMemberEmail())
                .memberAge(memberRequestDto.getMemberAge())
                .memberSex(memberRequestDto.getMemberSex())
                .memberAddress(memberRequestDto.getMemberAddress())
                .memberPhoneNumber(memberRequestDto.getMemberPhoneNumber())
                .roles(memberRequestDto.getRoles())
                .memberGrade(memberRequestDto.getMemberGrade())
                .accountEnable(memberRequestDto.getAccountEnable())
                .build();
    }
}
