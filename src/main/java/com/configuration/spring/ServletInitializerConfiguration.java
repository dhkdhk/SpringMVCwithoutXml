package com.configuration.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ServletInitializerConfiguration implements WebApplicationInitializer {

    private final String DISPATCHER_NAME = "dispatcher";
    private final String DISPATCHER_MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext container) {

        //① RootApplicationContext 생성 및 설정정보 등록
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootAppContextConfiguration.class);

        //② RootApplicationContext 라이프사이클 설정
        container.addListener(new ContextLoaderListener(rootContext));

        //③ WebApplicationContext 생성 및 설정정보 등록
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebAppContextConfiguration.class);

        //④ DispatcherServlet 생성 및 기타 옵션정보 설정
        ServletRegistration.Dynamic dispatcher = container.addServlet(DISPATCHER_NAME, new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_MAPPING_URL);

    }

}
