package com.domain.member.entity;

import lombok.*;

import javax.persistence.*;
@Getter @Builder @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "base", name = "members")
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private String memberGrade;

}
