package com.domain.member.utill.error;

import com.config.spring.RootAppContextConfiguration;
import com.config.spring.WebAppContextConfiguration;
import com.domain.global.error.ErrorCode;
import com.domain.global.error.MethodNotSupportedException;
import com.domain.member.entity.Member;
import com.domain.member.service.jpa.MemberAccountChanger;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberExceptionHandlerTest {


    protected MockMvc mockMvc;

    @Autowired
    @Qualifier(value = "memberAccountChanger")
    private MemberAccountChanger memberAccountChanger;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void memberNotFound() throws Exception {

        final ResultActions resultActions = requestGetMember(0L);

        //then
        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("[0].errorObject").value(ErrorCode.MEMBER_NOT_FOUND.getErrorObject()))
                .andExpect(jsonPath("[0].rejectValue").value(ErrorCode.MEMBER_NOT_FOUND.getRejectValue()))
                .andExpect(jsonPath("[0].message").value(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

    }

    private ResultActions requestGetMember(Long memberId) throws Exception {
        return mockMvc.perform(get("/api/members/{id}", memberId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Test(expected = MethodNotSupportedException.class)
    public void notSupportedMethod() throws Exception{
        //Then
        Member member = Member.builder()
                .memberName("KDH")
                .memberPassword("123")
                .build();

        //When
        memberAccountChanger.ourHomePageToSignUp(member);



    }



}