package com.domain.member.controller;

import com.config.spring.RootAppContextConfiguration;
import com.config.spring.WebAppContextConfiguration;
import com.domain.member.entity.AccountEnable;
import com.domain.member.entity.Member;
import com.domain.member.dto.MemberDto;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberCommonRepository memberCommonRepository;

    private MockMvc mockMvc;
    private AccountEnable accountEnable;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
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
        MemberDto member = MemberDto.builder()
                .memberCheckPassword("123")
                .memberPassword("123")
                .memberName("zzzz")
                .memberEmail("addTest@asdfasdfasdf.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1111-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();

        //When & Then
        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print())
                .andExpect(jsonPath("memberName").exists())
                .andExpect(jsonPath("memberPassword").exists())
                .andExpect(jsonPath("memberEmail").exists())
                .andExpect(jsonPath("memberAge").exists())
                .andExpect(jsonPath("memberSex").exists())
                .andExpect(jsonPath("memberAddress").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("memberGrade").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("roles").exists())
                .andExpect(jsonPath("accountEnable").exists())
                .andExpect(status().isCreated());
    }

    @Test
    public void B_validatie() throws Exception {
        //Given
        MemberDto member = MemberDto.builder()
                .memberCheckPassword("1232222222")
                .memberPassword("123")
                .memberName("zzzz")
                .memberEmail("addTest@asdfasdfasdf.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1111-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When & Then
        mockMvc.perform(post("/api/member")
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
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1001-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(get("/api/member/{memberId}", result.getMemberId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("memberName").exists())
                .andExpect(jsonPath("memberPassword").exists())
                .andExpect(jsonPath("memberEmail").exists())
                .andExpect(jsonPath("memberAge").exists())
                .andExpect(jsonPath("memberSex").exists())
                .andExpect(jsonPath("memberAddress").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("memberGrade").exists())
                .andExpect(jsonPath("memberPhoneNumber").exists())
                .andExpect(jsonPath("createdAt").exists())
                .andExpect(jsonPath("modifiedAt").exists())
                .andExpect(jsonPath("roles").exists())
                .andExpect(jsonPath("accountEnable").exists());
    }

    @Test(expected = IllegalArgumentException.class)
    public void D_getMemberNotFound() throws Exception{

        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("GET_NOT_FOUND")
                .memberEmail("get@nnnnnnnnnn.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1188-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(get("/api/member/{memberId}", result.getMemberId()+1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void E_deleteMember() throws Exception{
        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("DELETE_TEST")
                .memberEmail("delete@test.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("012-112-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(delete("/api/member/{memberId}", result.getMemberId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void F_deleteNotFoundMember() throws Exception{
        //Given
        Member member = Member.builder()
                .memberPassword("55")
                .memberName("DELETE_TEST_NOTFOUND")
                .memberEmail("delete_notFound@test.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("013-112-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();


        //When
        Member result = memberCommonRepository.save(member);

        //Then
        mockMvc.perform(delete("/api/member/{memberId}", result.getMemberId()+1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional(transactionManager = "jpaTransactionManager")
    @Rollback(false)
    public void memberUpdateInformation() throws Exception{
        //Given
        Member member = Member.builder()
                .memberPassword("123")
                .memberName("NotUpdate")
                .memberEmail("NotUpdate@memberUpdate.com")
                .memberSex("남")
                .memberAge(99)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-7788-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();
        Member result = memberCommonRepository.save(member);

        MemberDto memberDto = MemberDto.builder()
                .memberName("Update")
                .memberEmail("update@memberUpdate.com")
                .memberAddress("서울시 노량진")
                .build();


        //When & Then
        mockMvc.perform(put("/api/member/{memberId}", result.getMemberId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(memberDto)))
                .andDo(print())
                .andExpect(jsonPath("memberName").value(memberDto.getMemberName()))
                .andExpect(jsonPath("memberEmail").value(memberDto.getMemberEmail()))
                .andExpect(jsonPath("memberAddress").value(memberDto.getMemberAddress()))
                .andExpect(status().isOk());
    }
}
