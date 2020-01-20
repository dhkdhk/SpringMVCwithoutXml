package com.domain.member.controller;

import com.configuration.spring.RootAppContextConfiguration;
import com.configuration.spring.WebAppContextConfiguration;
import com.domain.member.dto.MemberRequestDto;
import com.domain.member.entity.AccountEnable;
import com.domain.member.repository.jpa.MemberCommonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
public class MemberSignControllerTest {

    private String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;
    private AccountEnable accountEnable;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
        accountEnable = AccountEnable.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    @Test
    public void loginTest() throws Exception {
        //Given
        String memberPassword = "123";
        MemberRequestDto member = MemberRequestDto.builder()
                .memberCheckPassword(memberPassword)
                .memberPassword(memberPassword)
                .memberName("kdh")
                .memberEmail("addMember@naver.com")
                .memberSex("남")
                .memberAge(31)
                .grantedAuthority(Arrays.asList("READ", "WRITE"))
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-828-8282")
                .accountEnable(accountEnable)
                .memberGrade("admin")
                .build();

        //When
        mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print());


        RequestBuilder requestBuilder = formLogin()
                .user("memberEmail", member.getMemberEmail())
                .password("memberPassword", memberPassword);

        //Then
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/"));
    }

}
