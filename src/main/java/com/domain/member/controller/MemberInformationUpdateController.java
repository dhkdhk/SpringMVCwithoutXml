package com.domain.member.controller;

import com.domain.globalutill.JsonMappingUtils;
import com.domain.globalutill.ResponseErrors;
import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.service.jpa.MemberInformationUpdateService;
import com.domain.member.validation.MemberValidatior;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberInformationUpdateController {

    private final MemberValidatior memberValidatior;
    private final MemberInformationUpdateService memberInformationUpdateService;

    @PostMapping("/api/member")
    public ResponseEntity addMember(@RequestBody MemberDto memberDto, Errors errors)  {

        memberValidatior.validate(memberDto, errors);
        if(errors.hasErrors()){
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member result = memberInformationUpdateService.addMember(memberDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/member/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId){
        return ResponseEntity.ok(memberInformationUpdateService.getMember(memberId));
    }

    @DeleteMapping("/api/member/{memberId}")
    public ResponseEntity deteleMember(@PathVariable Long memberId){
        boolean result = memberInformationUpdateService.deleteMember(memberId);

        if(result){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    @PatchMapping("/api/member/")
    public ResponseEntity updateMember(@RequestBody MemberDto memberDto, Errors errors){
        return null;
    }
}
