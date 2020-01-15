package com.interfaces.controller;

import com.domain.member.dto.MemberRequestDto;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberAccount;
import com.domain.member.service.jpa.MemberFinder;
import com.domain.member.service.jpa.MemberIProfile;
import com.domain.member.service.jpa.MemberSignUp;
import com.domain.member.support.ModelMappingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberAccount memberAccountChanger;
    private final MemberSignUp memberSignUp;
    private final MemberIProfile memberProfileUpdater;
    private final MemberFinder memberFinder;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/v1/members")
    public ResponseEntity signUp(@RequestBody final MemberRequestDto memberRequestDto) {

        Member member =  memberSignUp.signUp(ModelMappingUtil.dtoToEntity(memberRequestDto));
        member.passwordEncode(passwordEncoder);

        log.debug("###Sign UP", member.getMemberEmail()+" 회원가입 완료");

        return ResponseEntity.status(HttpStatus.CREATED).body(ModelMappingUtil.entityToDto(member));
    }

    @GetMapping("/v1/members/{memberId}")
    public ResponseEntity getMember(@PathVariable final Long memberId) {

        final Member getMember = memberFinder.findById(memberId);

        return ResponseEntity.ok(ModelMappingUtil.entityToDto(getMember));
    }

    @DeleteMapping("/v1/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable final Long memberId) {
        memberAccountChanger.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/v1/members/{memberId}")
    public String updateInformation(@PathVariable final Long memberId, @RequestBody MemberRequestDto memberRequestDto) {

        Member member = ModelMappingUtil.dtoToEntity(memberRequestDto);

        memberProfileUpdater.editProfile(member);

        return "redirect:/v1/members/"+memberId;
    }

}
