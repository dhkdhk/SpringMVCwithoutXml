package com.config.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.nio.charset.StandardCharsets;

public class ServletInitConfig implements WebApplicationInitializer {
    private final String DISPATCHER_NAME = "dispatcher";
    private final String DISPATCHER_MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext container) {

        //① RootApplicationContext 생성 및 설정정보 등록
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootAppConfig.class);

        //② RootApplicationContext 라이프사이클 설정
        container.addListener(new ContextLoaderListener(rootContext));

        //③ WebApplicationContext 생성 및 설정정보 등록
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebAppConfig.class);

        //④ DispatcherServlet 생성 및 기타 옵션정보 설정
        ServletRegistration.Dynamic dispatcher = container.addServlet(DISPATCHER_NAME, new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_MAPPING_URL);


        //필터 등록
        FilterRegistration charEncodingFilterReg = container.addFilter(FilterType.CHARACTER_ENCODING.name(), createdFilter(FilterType.CHARACTER_ENCODING));
        charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*");
    }

    private CharacterEncodingFilter createdFilter(FilterType type){

        switch (type){
            case CHARACTER_ENCODING:
                CharacterEncodingFilter filter = new CharacterEncodingFilter();
                filter.setEncoding(StandardCharsets.UTF_8.name());
                filter.setForceEncoding(true);
                return filter;

            case SPRING_SECURITY:
                //TODO Spring Security Filter Setting

            default:
                throw new IllegalArgumentException("해당되는 FilterType이 없습니다");
        }

    }
}
