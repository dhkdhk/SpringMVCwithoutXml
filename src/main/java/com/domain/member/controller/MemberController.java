package com.domain.member.controller;

import com.domain.globalutill.JsonMappingUtils;
import com.domain.globalutill.ResponseErrors;
import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.domain.member.validation.MemberValidatior;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MemberController {

    private final MemberValidatior memberValidatior;
    private final MemberCommonRepository memberCommonRepository;
    private final ModelMapper modelMapper;

    @PostMapping("/api/member")
    public ResponseEntity addMember(@RequestBody @Valid MemberDto memberDto, BindingResult errors)  {

        memberValidatior.validate(memberDto, errors);

        if(errors.hasErrors()){
            ResponseErrors responseErrors = new ResponseErrors(errors);

            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member member = modelMapper.map(memberDto, Member.class);

        Member result = memberCommonRepository.save(member);

        return ResponseEntity.ok(result);
    }



}
