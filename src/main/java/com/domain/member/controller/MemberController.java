package com.domain.member.controller;

import com.domain.global.JsonMappingUtils;
import com.domain.global.error.ResponseErrors;
import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberAccount;
import com.domain.member.service.jpa.MemberFinder;
import com.domain.member.service.jpa.MemberIProfile;
import com.domain.member.service.jpa.MemberSignUp;
import com.domain.member.utill.validation.MemberValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberValidator memberValidator;

    private final MemberAccount memberAccount;
    private final MemberIProfile memberProfileUpdator;
    private final MemberSignUp memberSignUp;
    private final MemberFinder memberFinder;


    @GetMapping("/login")
    public void login() {
    }

    @PostMapping("/api/members")
    public ResponseEntity signUp(@RequestBody MemberDto memberDto, Errors errors) {

        memberValidator.validate(memberDto, errors);
        if (errors.hasErrors()) {
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

       Member result = memberSignUp.ourHomePageToSignUp(memberDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity getMember(@PathVariable final Long memberId)  {
       return ResponseEntity.ok(memberFinder.findById(memberId));
    }

    @DeleteMapping("/api/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        boolean deleteSuccess = memberAccount.deleteMember(memberId);

        if (deleteSuccess) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/api/members/{memberId}")
    public ResponseEntity updateInformation(@PathVariable Long memberId, @RequestBody MemberDto memberDto, Errors errors) {
        memberValidator.validate(memberDto, errors);
        if (errors.hasErrors()) {
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(responseErrors.getResponseErrorsList());
        }
        return ResponseEntity.ok().body(memberProfileUpdator.editProfile(memberId , memberDto));
    }

}
