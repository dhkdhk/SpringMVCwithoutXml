package com.domain.entity;

import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long memberId;
    private String memberPassword;
    private String memberName;


}
