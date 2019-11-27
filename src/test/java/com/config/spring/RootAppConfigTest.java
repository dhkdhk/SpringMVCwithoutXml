package com.config.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.sql.DataSource;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootAppConfig.class)
public class RootAppConfigTest {


    private AnnotationConfigWebApplicationContext context;


    @Before
    public void setUp(){
        context = new AnnotationConfigWebApplicationContext();
        context.register(RootAppConfig.class);
        context.refresh();
    }

    @Test
    public void rootConfigSingleRegistryTest(){

        //Given & When
        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource)context.getBean("getDataSource");
        DriverManagerDataSource driverManagerDataSource1Second = (DriverManagerDataSource)context.getBean("getDataSource");

        NamedParameterJdbcTemplate jdbcTemplate = (NamedParameterJdbcTemplate)context.getBean("jdbcTemplate");
        NamedParameterJdbcTemplate jdbcTemplateSecond = (NamedParameterJdbcTemplate)context.getBean("jdbcTemplate");


        //Then
        assertSame(driverManagerDataSource, driverManagerDataSource1Second);
        assertSame(jdbcTemplate, jdbcTemplateSecond);
    }

    @Test
    public void jpaEntityManagerTest(){

        //Given
        LocalContainerEntityManagerFactoryBean lem = (LocalContainerEntityManagerFactoryBean)context.getBean("entityManagerFactory");
        DataSource dataSource = lem.getDataSource();
        assertNotNull(lem);
    }

}
