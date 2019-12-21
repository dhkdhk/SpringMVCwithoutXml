package com.domain.member.controller;

import com.domain.globalutill.JsonMappingUtils;
import com.domain.globalutill.ResponseErrors;
import com.domain.member.entity.Member;
import com.domain.member.entity.MemberDto;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.domain.member.validation.MemberValidatior;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MemberInformationUpdateController {

    private final MemberValidatior memberValidatior;
    private final MemberCommonRepository memberCommonRepository;
    private final String MEMBER_REST_URI = "/api/member";

    @PostMapping(MEMBER_REST_URI)
    public ResponseEntity addMember(@RequestBody MemberDto memberDto, Errors errors)  {

        memberValidatior.validate(memberDto, errors);
        if(errors.hasErrors()){
            ResponseErrors responseErrors = new ResponseErrors(errors);
            return ResponseEntity.badRequest().body(JsonMappingUtils.toJson(responseErrors.getResponseErrorsList()));
        }

        Member result = memberCommonRepository.save(memberDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(MEMBER_REST_URI+"/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId){
        Optional<Member> memberOptional = memberCommonRepository.findById(memberId);
        return memberOptional.map(member -> ResponseEntity.ok(member))
                            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(MEMBER_REST_URI+"/{memberId}")
    public ResponseEntity deteleMember(@PathVariable Long memberId){
        Optional<Member> memberOptional = memberCommonRepository.findById(memberId);
        return memberOptional
                    .map(member -> { memberCommonRepository.deleteById(memberId);
                                     return  ResponseEntity.ok().build();
                                    })
                    .orElse(ResponseEntity.noContent().build());
    }
}
