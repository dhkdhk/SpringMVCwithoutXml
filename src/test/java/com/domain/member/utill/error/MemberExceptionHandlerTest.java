package com.domain.member.utill.error;

import com.config.spring.RootAppContextConfiguration;
import com.config.spring.WebAppContextConfiguration;
import com.domain.global.error.ErrorCode;
import com.domain.member.controller.MemberController;
import com.domain.member.entity.AccountEnable;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppContextConfiguration.class, WebAppContextConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberExceptionHandlerTest {

    private static final StaticApplicationContext applicationContext = new StaticApplicationContext();
    private static final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();

    protected MockMvc mockMvc;

    @InjectMocks
    private MemberController memberController;

    private AccountEnable accountEnable;

    @Before
    public void setUp() {
        applicationContext.registerSingleton("exceptionHandler", ResponseEntityExceptionHandler.class);
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
                .alwaysDo(print())
                .build();

        accountEnable = AccountEnable.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    @Test
    public void memberNotFound() throws Exception {

        final ResultActions resultActions = requestGetMember(0L);

        //then
        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("objectName").value(ErrorCode.MEMBER_NOT_FOUND.getObjectName()))
                .andExpect(jsonPath("errorMessage").value(ErrorCode.MEMBER_NOT_FOUND.getErrorMessage()))
                .andExpect(jsonPath("rejectValue").value(ErrorCode.MEMBER_NOT_FOUND.getRejectValue()));

    }


    private ResultActions requestGetMember(Long memberId) throws Exception {
        return mockMvc.perform(get("/api/members/{id}", memberId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }
}