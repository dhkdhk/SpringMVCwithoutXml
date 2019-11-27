package com.config.spring;

import com.domain.entity.Member;
import com.domain.service.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppConfig.class)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void addMember(){
        //Given
        Member member = Member.builder()
                .memberPassword("123")
                .memberName("dhk")
                .build();

        //When & Then
        Assert.assertEquals(1, memberService.addMemeber(member));
    }
}
