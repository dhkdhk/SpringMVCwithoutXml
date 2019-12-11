package com.domain.member.controller;

import com.domain.member.entity.Member;
import com.domain.member.service.MemberManagementService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    private MemberManagementService memberManagementService;

    public MemberController(MemberManagementService memberManagementService){
        this.memberManagementService = memberManagementService;
    }


    @PostMapping("/api/member/")
    public void addMember(@RequestBody Member member){

    }













}
