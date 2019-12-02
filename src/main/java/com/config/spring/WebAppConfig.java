package com.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.domain",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value = {Repository.class, Service.class}))
public class WebAppConfig implements WebMvcConfigurer {

}
