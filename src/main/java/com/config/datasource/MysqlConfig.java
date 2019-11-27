package com.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {

    private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final String DRIVER_URL = "jdbc:mysql://localhost:3306/base?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
    private final String USER_NAME = "study";
    private final String PASSWORD = "hard";

    public MysqlConfig(){
        System.out.println(this.getClass().getName());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(DRIVER_URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }


}
