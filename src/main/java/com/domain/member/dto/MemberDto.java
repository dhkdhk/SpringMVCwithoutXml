package com.domain.member.dto;


import com.domain.member.entity.AccountEnable;
import com.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private Long memberId;
    private String memberPassword;
    private String memberCheckPassword;

    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private List<String> roles;
    private String memberGrade;
    private AccountEnable accountEnable;


    public Member toEntity(){
        return Member.builder()
                .memberName(memberName)
                .memberPassword(memberPassword)
                .memberEmail(memberEmail)
                .memberAge(memberAge)
                .memberSex(memberSex)
                .memberAddress(memberAddress)
                .memberPhoneNumber(memberPhoneNumber)
                .roles(roles)
                .memberGrade(memberGrade)
                .accountEnable(accountEnable)
                .build();
    }

    public Member toEntity(Member member){
        return Member.builder()
                .memberId(member.getMemberId())
                .memberName(memberName)
                .memberPassword(memberPassword)
                .memberEmail(memberEmail)
                .memberAge(memberAge)
                .memberSex(memberSex)
                .memberAddress(memberAddress)
                .memberPhoneNumber(memberPhoneNumber)
                .roles(roles)
                .memberGrade(memberGrade)
                .accountEnable(accountEnable)
                .build();
    }

}
