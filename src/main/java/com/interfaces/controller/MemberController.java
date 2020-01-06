package com.interfaces.controller;

import com.domain.member.dto.MemberDto;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberAccount;
import com.domain.member.service.jpa.MemberFinder;
import com.domain.member.service.jpa.MemberIProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MemberController {

    private final MemberAccount memberAccountChanger;
    private final MemberAccount memberAccountSignUp;
    private final MemberIProfile memberProfileUpdater;
    private final MemberFinder memberFinder;

    public MemberController(MemberAccount memberAccountChanger, MemberAccount memberAccountSignUp,
                            MemberIProfile memberProfileUpdater, MemberFinder memberFinder) {
        this.memberAccountChanger = memberAccountChanger;
        this.memberAccountSignUp = memberAccountSignUp;
        this.memberProfileUpdater = memberProfileUpdater;
        this.memberFinder = memberFinder;
    }


    @PostMapping("/api/members")
    public ResponseEntity signUp(@RequestBody MemberDto memberDto) {

        final Member result = memberAccountSignUp.ourHomePageToSignUp(memberDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity getMember(@PathVariable final Long memberId) {
        return ResponseEntity.ok(memberFinder.findById(memberId));
    }

    @DeleteMapping("/api/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        boolean deleteSuccess = memberAccountChanger.deleteMember(memberId);

        if (deleteSuccess) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/api/members/{memberId}")
    public ResponseEntity updateInformation(@PathVariable Long memberId, @RequestBody MemberDto memberDto) {

        return ResponseEntity.ok().body(memberProfileUpdater.editProfile(memberId, memberDto));
    }

}
