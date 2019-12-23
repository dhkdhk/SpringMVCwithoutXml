package com.domain.member.validation;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.jpa.MemberCommonRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@Component
public class MemberValidatior {

    private final MemberCommonRepository memberCommonRepository;

    public MemberValidatior(MemberCommonRepository memberCommonRepository){
        this.memberCommonRepository = memberCommonRepository;
    }

    public void validate(MemberDto memberDto, Errors errors) {
        Optional<Member> validationMember = memberCommonRepository.findMemberByMemberEmail(memberDto.getMemberEmail());

        if(!validationMember.isEmpty()){
            errors.rejectValue("memberEmail","duplicateEmail","다른사람과 중복된 email입니다. 다른 email을 선택해주세요");
        }
        if(!memberDto.getMemberPassword().equals(memberDto.getMemberCheckPassword())){
            errors.rejectValue("memberPassword", "InconsistencyPassword", "패스워드가 일치하지 않습니다.");
        }

        if(!validationMember.isEmpty()){
            errors.rejectValue("memberPhoneNumber", "duplicatePhonNumber", "다른 사람이 사용하는 번호입니다. 다른 번호를 입력해주세요");
        }

    }
}
