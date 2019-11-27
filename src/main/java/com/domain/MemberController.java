package com.domain;

import com.domain.entity.Member;
import com.domain.service.MemberService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/member")
@NoArgsConstructor
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
