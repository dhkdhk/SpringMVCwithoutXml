package com.domain.member.dto;

import com.domain.member.entity.AccountEnable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private List<String> grantedAuthority;
    private String memberGrade;
    private AccountEnable accountEnable;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
