package com.config.spring;

import com.domain.member.controller.MemberController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = MemberController.class)
public class WebAppConfig implements WebMvcConfigurer {

}
