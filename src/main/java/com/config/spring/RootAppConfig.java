package com.config.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableTransactionManagement
@ComponentScan(basePackages = { "com.config.datasource", "com.domain"},
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value= Controller.class))
public class RootAppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

//    @Bean
//    public PlatformTransactionManager txManager() {
//         return new DataSourceTransactionManager(dataSource());
//    }
}
