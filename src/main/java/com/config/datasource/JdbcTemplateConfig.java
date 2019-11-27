package com.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    public JdbcTemplateConfig(){
        System.out.println(this.getClass().getName());
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
