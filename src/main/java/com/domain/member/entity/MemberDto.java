package com.domain.member.entity;


import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {

    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberAddress;
    private String memberPhoneNumber;

}
