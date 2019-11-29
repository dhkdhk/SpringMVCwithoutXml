package com.domain.entity;

import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name="test")
public class Member {

    private Long memberId;
    private String memberPassword;
    private String memberName;


}
