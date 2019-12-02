package com.config.spring;

import com.config.datasource.MysqlConfig;
import com.domain.repository.MemberRepository;
import com.domain.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = {MysqlConfig.class, MemberRepository.class, MemberService.class},
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value= Controller.class))
public class RootAppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
