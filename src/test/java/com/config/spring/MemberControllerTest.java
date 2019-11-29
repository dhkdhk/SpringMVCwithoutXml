package com.config.spring;

import com.domain.MemberController;
import com.domain.entity.Member;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootAppConfig.class, WebAppConfig.class})
public class MemberControllerTest {

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
    public void addMember() throws Exception{
        //Given
        Member member = Member.builder()
                .memberName("KDH")
                .memberPassword("123")
                .build();

        //When
        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(member)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
