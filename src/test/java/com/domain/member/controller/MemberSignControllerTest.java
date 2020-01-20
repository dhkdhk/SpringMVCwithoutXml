package com.domain.member.controller;

import com.configuration.spring.RootAppContextConfiguration;
import com.configuration.spring.WebAppContextConfiguration;
import com.domain.member.dto.MemberRequestDto;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberSignUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
public class MemberSignControllerTest {

    @Autowired
    private MemberSignUp memberSignUp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void loginTest() throws Exception {

        //Given
        String memberEmail = "login@naver.com";
        String memberPassword= "123";
        Member member = createMember(memberEmail, memberPassword);

        //Then
        mockMvc.perform(formLogin().user("memberEmail", member.getMemberEmail()).password("memberPassword", memberPassword))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/"));
    }

    private Member createMember(String memberEmail, String memberPassword){
        Member member = Member.builder()
                .memberEmail(memberEmail)
                .memberPassword(passwordEncoder.encode(memberPassword))
                .memberName("kdh")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("ADMIN"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-828-8282")
                .memberGrade("admin")
                .build();
        return  memberSignUp.signUp(member);
    }

}
