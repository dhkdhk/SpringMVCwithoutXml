package com.config.spring;

import com.domain.member.controller.MemberInformationUpdateController;
import com.domain.member.validation.MemberValidatior;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {MemberInformationUpdateController.class, MemberValidatior.class})
public class WebAppContextConfiguration implements WebMvcConfigurer {


}
