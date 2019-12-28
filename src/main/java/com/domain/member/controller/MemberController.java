package com.domain.member.controller;

import com.domain.globalutill.JsonMappingUtils;
import com.domain.globalutill.ResponseErrors;
import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberFinder;
import com.domain.member.service.jpa.MemberSignUp;
import com.domain.member.service.jpa.MemberUpdator;
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

    private final MemberUpdator memberUpdator;
    private final MemberSignUp memberSignUp;
    private final MemberFinder memberFinder;


    @GetMapping("/login")
    public void login() {
        System.out.println("login...");
    }

    @PostMapping("/api/members")
    public ResponseEntity signUp(@RequestBody MemberDto memberDto, Errors errors) {

        memberValidatior.validate(memberDto, errors);
        if (errors.hasErrors()) {
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member result = memberSignUp.ourHomePageToSignUp(memberDto.toEntity());

        //TODO password 제외하고 응답값 던져야 함
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity getMember(@PathVariable final Long memberId) {
        return ResponseEntity.ok(memberFinder.findById(memberId));
    }

    @DeleteMapping("/api/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        boolean result = memberUpdator.deleteMember(memberId);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/api/members/{memberId}")
    public ResponseEntity updateInformation(@PathVariable Long memberId, @RequestBody MemberDto memberDto, Errors errors) {
        memberValidatior.validate(memberDto, errors);
        if (errors.hasErrors()) {
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }
        return ResponseEntity.ok().body(memberUpdator.updateMemberInformation(memberId , memberDto));
    }

}
