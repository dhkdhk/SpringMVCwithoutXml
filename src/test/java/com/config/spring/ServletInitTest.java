package com.config.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ServletInit.class)
public class ServletInitTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void rootConfigTest(){
//        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");

        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource)context.getBean("getDataSource");
        assertThat(driverManagerDataSource.getUsername(), equals("study"));
    }
}
