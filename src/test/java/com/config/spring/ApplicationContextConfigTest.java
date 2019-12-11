package com.config.spring;

import com.domain.member.repository.MemberUpdateJdbcRepo;
import com.domain.member.service.MemberManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppConfig.class)
public class ApplicationContextConfigTest {

    @Autowired private DataSource dataSource;

    @Autowired private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired private MemberManagementService memberManagementService;

    @Autowired private MemberUpdateJdbcRepo memberUpdateJdbcRepo;

    @Autowired private EntityManagerFactory entityManagerFactory;

    @Test
    public void rootContextDependencyInjection(){
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(memberManagementService);
        assertNotNull(memberUpdateJdbcRepo);
        assertNotNull(entityManagerFactory);
    }

    @Test
    public void entityManagerTest(){
    }



}
