package com.domain.member.entity;

import com.domain.member.support.GrantedAuthorityAttributeConverter;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
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
    @Convert(converter = GrantedAuthorityAttributeConverter.class)
    private List<String> grantedAuthority;

    @Embedded
    private AccountEnable accountEnable;


    public void updateInformation(Member member) {
        this.memberName = member.getMemberName();
        this.memberEmail = member.getMemberEmail();
        this.memberAddress = member.getMemberAddress();
        this.memberPhoneNumber = member.getMemberPhoneNumber();

    }

    public void passwordEncode(PasswordEncoder passwordEncoder){
       this.memberPassword = passwordEncoder.encode(this.memberPassword);
    }

}
