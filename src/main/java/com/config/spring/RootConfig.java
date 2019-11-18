package com.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.config.datasource"} )
public class RootConfig {

    public RootConfig(){
        System.out.println(RootConfig.class.getPackage().getName());
    }
}
