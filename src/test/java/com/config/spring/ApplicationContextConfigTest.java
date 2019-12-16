package com.config.spring;

import com.domain.member.repository.jdbc.MemberManagementJdbcRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppContextConfiguration.class)
public class ApplicationContextConfigTest {

    @Autowired private DataSource dataSource;

    @Autowired private NamedParameterJdbcTemplate jdbcTemplate;


    @Autowired private MemberManagementJdbcRepo memberManagementJdbcRepo;

    @Autowired private EntityManagerFactory entityManagerFactory;

    @Autowired private PlatformTransactionManager jpaTransactionManager;

    @Autowired private PlatformTransactionManager dataSourceTransactionManager;

    @Test
    public void rootContextDependencyInjection(){
        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(memberManagementJdbcRepo);
        assertNotNull(entityManagerFactory);
        Assert.assertEquals(jpaTransactionManager.getClass(), new JpaTransactionManager().getClass());
        Assert.assertEquals(dataSourceTransactionManager.getClass(), new DataSourceTransactionManager().getClass());
    }


}
