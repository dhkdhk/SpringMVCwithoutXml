package com.domain.member.controller;

import com.config.spring.RootAppContextConfiguration;
import com.config.spring.WebAppContextConfiguration;
import com.domain.member.entity.AccountEnable;
import com.domain.member.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
public class ValidationTest {

    @Autowired
    private MemberController memberController;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp()  {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void emailDuplicate() throws Exception {
        //Given
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        AccountEnable accountEnable = new AccountEnable();
        accountEnable.setAccountNonExpired(true);
        accountEnable.setAccountNonLocked(true);
        accountEnable.setCredentialsNonExpired(true);
        accountEnable.setEnabled(true);


        Member member = Member.builder()
                .memberPassword("123")
                .memberName("dhk")
                .memberEmail("abc@abc.com")
                .memberSex("남")
                .memberAge(31)
                .roles(roles)
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-010-010")
                .memberGrade("TEST")
                .accountEnable(accountEnable)
                .build();

        //When & Then
        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
