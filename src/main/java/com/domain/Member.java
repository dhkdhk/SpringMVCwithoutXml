package com.domain;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Member {

    private Long memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private String memberGrade;
    private List<String> roles;

}
