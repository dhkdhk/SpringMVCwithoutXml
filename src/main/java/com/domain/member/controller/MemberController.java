package com.domain.member.controller;

import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.MemberManagementJpaRepo;
import com.domain.member.service.MemberManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberManagementService memberManagementService;
    private final MemberManagementJpaRepo memberManagementJpaRepo;


    @PostMapping("/api/member")
    public ResponseEntity addMember(@RequestBody MemberDto memberDto){


        return null;
    }

    @GetMapping("/api/member/{id}")
    public void getMember(@RequestBody Member member){

    }

    @PutMapping("/api/member/")
    public void updateMember(@RequestBody Member member){

    }


    @GetMapping
    public void transactionTest(){

    }











}
