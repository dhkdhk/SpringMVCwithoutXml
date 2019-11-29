package com.config.spring;

import com.domain.repository.MemberRepository;
import com.domain.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppConfig.class)
public class ApplicationContextConfigTest {

    @Autowired private DataSource dataSource;

    @Autowired private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired private MemberService memberService;

    @Autowired private MemberRepository memberRepository;

    @Test
    public void rootContextDependencyInjection(){
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(memberService);
        assertNotNull(memberRepository);
    }



//    @Test
//    public void jpaEntityManagerTest(){
//
//        //Given
//        LocalContainerEntityManagerFactoryBean lem = (LocalContainerEntityManagerFactoryBean)context.getBean("entityManagerFactory");
//        DataSource dataSource = lem.getDataSource();
//        assertNotNull(lem);
//    }

}
