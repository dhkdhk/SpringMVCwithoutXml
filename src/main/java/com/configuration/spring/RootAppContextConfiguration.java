package com.configuration.spring;

import com.configuration.datasource.PersistenceConfiguration;
import com.domain.member.repository.MemberRepositoryConfiguration;
import com.domain.member.service.MemberServiceConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interfaces.exception.handler.RestResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
@Import(value = RestResponseEntityExceptionHandler.class)
@ComponentScan(basePackageClasses = {PersistenceConfiguration.class, MemberServiceConfiguration.class, MemberRepositoryConfiguration.class})
public class RootAppContextConfiguration {

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
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
