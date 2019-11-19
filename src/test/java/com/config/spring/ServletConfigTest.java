package com.config.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ServletConfig.class)
public class ServletConfigTest {


    protected AnnotationConfigWebApplicationContext context;

    @Before
    public void setUp(){
        context = new AnnotationConfigWebApplicationContext();
        context.register(RootConfig.class);
        context.refresh();
    }

    @Test
    public void rootConfigTest(){
//        JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");

        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource)context.getBean("getDataSource");
        assertThat(driverManagerDataSource.getUsername(), equals("study"));
    }
}
