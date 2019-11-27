package com.config.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.config.datasource", "com.domain"},
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value= Controller.class))
public class RootAppConfig {

    public RootAppConfig(){
        System.out.println(this.getClass().getName());
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
