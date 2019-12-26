package com.domain.member.controller;

import com.domain.globalutill.JsonMappingUtils;
import com.domain.globalutill.ResponseErrors;
import com.domain.member.entity.Member;
import com.domain.member.dto.MemberDto;
import com.domain.member.service.jpa.MemberFinder;
import com.domain.member.service.jpa.MemberInformationUpdate;
import com.domain.member.service.jpa.MemberSignUp;
import com.domain.member.validation.MemberValidatior;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberValidatior memberValidatior;

    private final MemberInformationUpdate memberInformationUpdate;
    private final MemberSignUp memberSignUp;
    private final MemberFinder memberFinder;


    @PostMapping("/api/member")
    public ResponseEntity addMember(@RequestBody MemberDto memberDto, Errors errors)  {

        memberValidatior.validate(memberDto, errors);
        if(errors.hasErrors()){
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member result = memberSignUp.ourHomePageToJoin(memberDto.toEntity());

        //TODO password 제외하고 응답값 던져야 함
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/member/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId){
        return ResponseEntity.ok(memberFinder.findById(memberId));
    }

    @DeleteMapping("/api/member/{memberId}")
    public ResponseEntity deteleMember(@PathVariable Long memberId){
        boolean result = memberInformationUpdate.deleteMember(memberId);

        if(result){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    @PutMapping("/api/member/{memberId}")
    public ResponseEntity informationUpdate(@PathVariable Long memberId, @RequestBody MemberDto memberDto, Errors errors){
        memberValidatior.validate(memberDto, errors);
        if(errors.hasErrors()){
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member member = memberFinder.findById(memberId);
        Member entityMember = memberDto.toEntity(member);
        int result = memberInformationUpdate.updateMemberInformation(memberDto.toEntity(member));

        if(result<=0){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(entityMember);
    }

}
