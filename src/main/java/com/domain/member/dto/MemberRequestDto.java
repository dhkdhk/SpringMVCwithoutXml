package com.domain.member.dto;


import com.domain.member.entity.AccountEnable;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {

    private Long memberId;
    private String memberPassword;
    private String memberCheckPassword;
    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private List<String> grantedAuthority;
    private String memberGrade;
    private AccountEnable accountEnable;

}
