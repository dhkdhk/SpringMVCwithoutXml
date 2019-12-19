package com.domain.member.entity;

import com.domain.globalutill.RoleAttributeConvertor;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(schema = "base", name = "members")
public class Member extends CreatedAndModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private Integer memberAge;

    private String memberSex;

    private String memberAddress;

    private String memberPhoneNumber;

    private String memberGrade;
    @Convert(converter= RoleAttributeConvertor.class)
    private List<String> roles;

    @Embedded
    private AccountEnable accountEnable;
}
