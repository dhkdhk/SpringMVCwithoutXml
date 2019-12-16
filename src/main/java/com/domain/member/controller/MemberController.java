package com.domain.member.controller;

import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberCommonRepository memberCommonRepository;

    @PostMapping("/api/member")
    public ResponseEntity addMember(@RequestBody Member member){
        Member result = memberCommonRepository.save(member);
        return ResponseEntity.ok(result);
    }



}
