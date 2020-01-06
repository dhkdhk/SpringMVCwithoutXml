package com.domain.member.utill.error;

import com.configuration.spring.RootAppContextConfiguration;
import com.configuration.spring.WebAppContextConfiguration;
import com.interfaces.exception.handler.ErrorCode;
import com.interfaces.exception.MethodNotSupportedException;
import com.domain.member.dto.MemberDto;
import com.domain.member.entity.AccountEnable;
import com.domain.member.entity.Member;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.domain.member.service.jpa.MemberAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
@Transactional(transactionManager = "jpaTransactionManager")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberExceptionHandlerTest {


    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier(value = "memberAccountChanger")
    private MemberAccount memberAccountChanger;

    @Autowired
    private MemberCommonRepository memberCommonRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void memberNotFound() throws Exception {

        //Given
        final ResultActions resultActions = requestGetMember(0L);

        //Then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(ErrorCode.ENTITY_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("httpStatus").value(ErrorCode.ENTITY_NOT_FOUND.getHttpStatus()))
                .andExpect(jsonPath("code").value(ErrorCode.ENTITY_NOT_FOUND.getCode()));


    }

    private ResultActions requestGetMember(Long memberId) throws Exception {
        return mockMvc.perform(get("/api/members/{id}", memberId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Test
    public void duplicateMemberEmail() throws Exception {

        //Given
        Member member = duplicationTestBeforeSetUp();

        //When
        memberCommonRepository.save(member);

        MemberDto memberDto = MemberDto.builder()
                .memberEmail(member.getMemberEmail())
                .build();

        //Then
        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(memberDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(ErrorCode.DUPLICATION_FIELD.getMessage()))
                .andExpect(jsonPath("httpStatus").value(ErrorCode.DUPLICATION_FIELD.getHttpStatus()))
                .andExpect(jsonPath("code").value(ErrorCode.DUPLICATION_FIELD.getCode()));
    }


    @Test
    public void duplicateMemberPhoneNumber() throws Exception {

        //Given
        Member member = duplicationTestBeforeSetUp();

        //When
        memberCommonRepository.save(member);

        MemberDto memberDto = MemberDto.builder()
                .memberEmail(member.getMemberEmail()+"Anothor")
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .build();

        //Then
        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(memberDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(ErrorCode.DUPLICATION_FIELD.getMessage()))
                .andExpect(jsonPath("httpStatus").value(ErrorCode.DUPLICATION_FIELD.getHttpStatus()))
                .andExpect(jsonPath("code").value(ErrorCode.DUPLICATION_FIELD.getCode()));
    }

    private Member duplicationTestBeforeSetUp(){
        AccountEnable accountEnable = AccountEnable.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        Member member = Member.builder()
                .memberPassword("123")
                .memberName("zzzz")
                .memberEmail("duplicateTest@naver.com")
                .memberSex("남")
                .memberAge(31)
                .roles(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-1111-010")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();

       return memberCommonRepository.save(member);
    }

    @Test(expected = MethodNotSupportedException.class)
    public void notSupportedMethod() {
        //Then
        Member member = Member.builder()
                .memberName("KDH")
                .memberPassword("123")
                .build();

        //When
        memberAccountChanger.ourHomePageToSignUp(member);

    }


}