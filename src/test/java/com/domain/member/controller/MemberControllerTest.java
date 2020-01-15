package com.domain.member.controller;

import com.configuration.spring.RootAppContextConfiguration;
import com.configuration.spring.WebAppContextConfiguration;
import com.domain.member.dto.MemberRequestDto;
import com.domain.member.entity.AccountEnable;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberCommonRepository memberCommonRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private AccountEnable accountEnable;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        accountEnable = AccountEnable.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    @Test
    public void A_addMember() throws Exception {
        //Given
        MemberRequestDto member = MemberRequestDto.builder()
                .memberCheckPassword("addMember")
                .memberPassword("addMember")
                .memberName("kdh")
                .memberEmail("addMember@naver.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-828-8282")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();

        //When & Then
        mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print())
                .andExpect(jsonPath("memberName").exists())
                .andExpect(jsonPath("memberEmail").exists())
                .andExpect(jsonPath("memberAge").exists())
                .andExpect(jsonPath("memberSex").exists())
                .andExpect(jsonPath("memberAddress").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("memberGrade").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("grantedAuthority").exists())
                .andExpect(jsonPath("accountEnable").exists())

                .andExpect(jsonPath("memberPassword").doesNotExist())
                .andExpect(status().isCreated());
    }

    @Test
    public void B_validate() throws Exception {
        //Given
        MemberRequestDto member = MemberRequestDto.builder()
                .memberCheckPassword("1232222222")
                .memberPassword("123")
                .memberName("zzzz")
                .memberEmail("addTest@asdfasdfasdf.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1111-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When & Then
        mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print())
                .andExpect(jsonPath("[0].message").value("다른사람과 중복된 email입니다. 다른 email을 선택해주세요"))
                .andExpect(jsonPath("[1].message").value("패스워드가 일치하지 않습니다."))
                .andExpect(jsonPath("[2].message").value("다른 사람이 사용하는 번호입니다. 다른 번호를 입력해주세요"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void C_getMember() throws Exception {
        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("GETTEST")
                .memberEmail("get@asdfasdfasdf.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1001-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);



        //Then
        mockMvc.perform(get("/v1/members/{memberId}", result.getMemberId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("memberName").exists())
                .andExpect(jsonPath("memberEmail").exists())
                .andExpect(jsonPath("memberAge").exists())
                .andExpect(jsonPath("memberSex").exists())
                .andExpect(jsonPath("memberAddress").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("memberGrade").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("grantedAuthority").exists())
                .andExpect(jsonPath("accountEnable").exists())
                .andExpect(jsonPath("createdAt").exists())
                .andExpect(jsonPath("modifiedAt").exists())

                .andExpect(jsonPath("memberPassword").doesNotExist())

        ;
    }

    @Test
    public void D_getMemberNotFound() throws Exception {

        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("GET_NOT_FOUND")
                .memberEmail("get@nnnnnnnnnn.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1188-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(get("/v1/members/{memberId}", result.getMemberId() + 1))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void E_deleteMember() throws Exception {
        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("DELETE_TEST")
                .memberEmail("delete@test.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("012-112-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(delete("/v1/members/{memberId}", result.getMemberId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void F_deleteNotFoundMember() throws Exception {
        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("DELETE_TEST_NOTFOUND")
                .memberEmail("delete_notFound@test.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("013-112-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(delete("/v1/members/{memberId}", result.getMemberId() + 1))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void memberUpdateProfile() throws Exception {
        //Given
        Member member = Member.builder()
                .memberPassword("123")
                .memberName("NotUpdate")
                .memberEmail("NotUpdate@memberUpdate.com")
                .memberSex("남")
                .memberAge(99)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-7788-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();
        Member result = memberCommonRepository.save(member);

        MemberRequestDto memberUpdate = MemberRequestDto.builder()
                .memberId(result.getMemberId())
                .memberName("Update")
                .memberEmail("update@memberUpdate.com")
                .memberAddress("서울시 노량진")
                .memberPhoneNumber("010-1231-1231")
                .build();


        //When & Then
        mockMvc.perform(patch("/v1/members/{memberId}", result.getMemberId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(memberUpdate)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/v1/members/"+result.getMemberId())) ;


    }


}
