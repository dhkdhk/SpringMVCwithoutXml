package com.config.spring;

import com.config.datasource.JdbcTemplateConfig;
import com.config.datasource.JpaConfig;
import com.domain.member.repository.MemberManagementJdbcRepo;
import com.domain.member.service.MemberManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
@ComponentScan(basePackageClasses = {JpaConfig.class, JdbcTemplateConfig.class, MemberManagementJdbcRepo.class, MemberManagementService.class})
public class RootAppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverclass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }




}
