package com.domain.entity;

import lombok.*;

import javax.persistence.Entity;


@Getter @Setter @Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    private Long memberId;
    private String memberPassword;
    private String memberName;


}
