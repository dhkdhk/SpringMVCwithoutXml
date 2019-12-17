package com.domain.member.validation;

import com.domain.member.entity.MemberDto;
import com.domain.member.repository.jpa.MemberCommonRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class MemberValidatior {

    private final MemberCommonRepository memberCommonRepository;

    public MemberValidatior(MemberCommonRepository memberCommonRepository){
        this.memberCommonRepository = memberCommonRepository;
    }

    public void validate(MemberDto memberDto, Errors errors) {
        boolean emailEnabled = memberCommonRepository.existsMemberByMemberEmail(memberDto.getMemberEmail());
        if(!emailEnabled ){
            errors.reject("duplicateEmail","다른사람과 중복된 email입니다. 다른 email을 선택해주세요");
        }

        if(!memberDto.getMemberPassword().equals(memberDto.getMemberCheckPassword())){
            errors.reject("inconsistentPassword", "패스워드가 일치하지 않습니다.");
        }
    }
}
