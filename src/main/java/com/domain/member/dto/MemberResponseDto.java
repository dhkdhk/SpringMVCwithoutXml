package com.domain.member.dto;

import com.domain.member.entity.AccountEnable;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private String memberName;
    private String memberEmail;
    private Integer memberAge;
    private String memberSex;
    private String memberAddress;
    private String memberPhoneNumber;
    private List<String> roles;
    private String memberGrade;
    private AccountEnable accountEnable;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
