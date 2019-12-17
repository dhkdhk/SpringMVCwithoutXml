package com.domain.member.entity;


import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {

    private String memberPassword;
    private String memberCheckPassword;

    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private List<String> roles;
    private String memberGrade; //BRONZE , SLIVER , GOLD

}
