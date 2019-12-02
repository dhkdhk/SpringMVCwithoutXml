package com.domain;

import com.domain.entity.Member;
import com.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/member", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @PostMapping
    public void addMember(@RequestBody Member member){
        memberService.addMemeber(member);
    }
}
