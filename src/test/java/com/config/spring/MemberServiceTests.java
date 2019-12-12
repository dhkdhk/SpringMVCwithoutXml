package com.config.spring;

import com.domain.member.entity.Member;
import com.domain.member.service.MemberManagementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spock.lang.Specification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppConfig.class)
public class MemberServiceTests extends Specification {

    @Autowired
    private MemberManagementService memberManagementService;

    @Test
    public void addMember(){
        //Given
        Member member = Member.builder()
                .memberPassword("123")
                .memberName("dhk")
                .memberEmail("abc@abc.com")
                .memberSex("남")
                .memberAge(31)
                .memberAddress("서울시 동작구")
                .memberPhoneNumber("010-010-010")
                .memberGrade("TEST")
                .build();

        //When
        Member memberResult = memberManagementService.addMemeber(member);

        // & Then
        Assert.assertNotNull(memberResult.getMemberId());
        Assert.assertEquals(member.getMemberName(), memberResult.getMemberName());
        Assert.assertEquals(member.getMemberPassword(), memberResult.getMemberPassword());
        Assert.assertEquals(member.getMemberName(), memberResult.getMemberName());
        Assert.assertEquals(member.getMemberAddress(), memberResult.getMemberAddress());
        Assert.assertEquals(member.getMemberAge(), memberResult.getMemberAge());
        Assert.assertEquals(member.getMemberEmail(), memberResult.getMemberEmail());
        Assert.assertEquals(member.getMemberGrade(), memberResult.getMemberGrade());
        Assert.assertEquals(member.getMemberPhoneNumber(), memberResult.getMemberPhoneNumber());
        Assert.assertEquals(member.getMemberSex(), memberResult.getMemberSex());

    }
}
