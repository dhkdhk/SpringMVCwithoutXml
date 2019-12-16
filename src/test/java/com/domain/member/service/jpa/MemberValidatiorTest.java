package com.domain.member.service.jpa;

import com.config.spring.RootAppContextConfiguration;
import com.domain.member.entity.Member;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppContextConfiguration.class)
public class MemberValidatiorTest {

    @Autowired
    private MemberValidationService memberValidationService;
    private Member member;

    @Before
    public void setUp(){
        List<String> roles = new ArrayList<>();
        roles.add("Admin");

         member = Member.builder()
                .memberName("dhk")
                .memberEmail("abc@abc.com")
                .build();
    }

    @Test
    public void notDuplicationCheckDBdataEmptySituation() {
        //Given & When
        boolean emailResult = memberValidationService.duplicationCheckEmail(member.getMemberEmail());
        boolean memberNameResult =  memberValidationService.duplicationCheckEmail(member.getMemberName());

        //Then
        Assert.assertTrue(emailResult);
        Assert.assertTrue(memberNameResult);
    }

    @Test
    public void duplicationCheckMemberNameAndEmail() {
        //Given & When
        boolean emailResult = memberValidationService.duplicationCheckEmail(member.getMemberEmail());
        boolean memberNameResult =  memberValidationService.duplicationCheckMemberName(member.getMemberName());

        //Then
        Assert.assertFalse(emailResult);
        Assert.assertFalse(memberNameResult);
    }
}