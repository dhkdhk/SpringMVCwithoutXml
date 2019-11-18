package com.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class MysqlConfig {

    public MysqlConfig() {
        System.out.println(MysqlConfig.class.getPackage().getName());
    }

    private final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private final String DRIVER_URL = "jdbc:mysql://localhost:3306/base?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
    private final String USER_NAME = "study";
    private final String PASSWORD = "hard";

    @Bean
    public DriverManagerDataSource getDataSource() throws ClassNotFoundException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(DRIVER_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
