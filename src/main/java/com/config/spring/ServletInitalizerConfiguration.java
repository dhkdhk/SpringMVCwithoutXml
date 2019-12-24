package com.config.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class ServletInitalizerConfiguration implements WebApplicationInitializer {
    private final String DISPATCHER_NAME = "dispatcher";
    private final String DISPATCHER_MAPPING_URL = "/";
    private  final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";
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


        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN);
        FilterRegistration.Dynamic registration = container.addFilter(SPRING_SECURITY_FILTER_CHAIN, springSecurityFilterChain);

        if (registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for '" + SPRING_SECURITY_FILTER_CHAIN
                    + "'. Check to ensure the Filter is only configured once.");
        }

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR);
        registration.addMappingForUrlPatterns(dispatcherTypes, false, "/*");

    }

}
