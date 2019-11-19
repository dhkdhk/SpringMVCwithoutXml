package com.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping
    public void addMember(@RequestBody Member member){
        memberService.addMemeber(member);
    }
}
