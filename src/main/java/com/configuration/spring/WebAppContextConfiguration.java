package com.configuration.spring;

import com.configuration.security.SpringSecurityConfiguration;
import com.interfaces.controller.MemberController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Import(value = SpringSecurityConfiguration.class)
@ComponentScan(basePackageClasses = {MemberController.class})
public class WebAppContextConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
    }
}
