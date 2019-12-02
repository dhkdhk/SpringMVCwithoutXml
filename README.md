
# Java 설정을 통한 Spring 구조 이해하기


1. RootApplicationContext / WebApplicationContext 살짝 훓어보기
 
  처음에는 이 두 개의 용어가 Spring의 특별한 것을 일컫는줄 알았다. 특별한 기술용어를 지칭하는 것이 아닌 Spring의 ApplicationContext를
 계층적으로 분리하여 Root(부모)와 Web(자식)으로 나눈 ApplicationContext를 말하는 것이었다. (상속 구조를 생각하면 된다)

[이미지파일]

 
 일반적으로 Java WebApplication 구조는 Client에 UI 랜더링을 처리하는 Presentation Layer, 비즈니스 로직을 처리하는 Business Layer, Database에 접근하는 Data Access Layer로 구성되어 있다. 
 여기서 RootApplicationContext는 Business Layer와 Data Access Layer에 필요한 Bean 생성과 관계설정등에 대한 설정 내용을 담고 있고,
 WebApplicationContext는 UI 랜더링을 처리하는 PresentationLayer와 관련된 설정 내용을 담고 있다. 
 
 그렇다면 왜 Spring은 ApplicationContext를 RootApplicationContext와 WebApplicationContext로 계층을 나누어 두 개 이상이 만들어질 수 있도록  ApplicationContext를 구성하였을까?
  
 Bean에 대한 의존성 주입을 관리하는 ApplicationContext가 두 개 이상이 만들어지는 건데, 상속구조처럼 생각한다면 확장성과
 다형성을 위해 두 개이상을 만들 수 있다고 생각은 들지만, 관리하는건 더 어렵지 않을까? 라는 이상한 궁금증이 생겼다.
 
 Java 코드로 Spring 설정하여, Spring의 Context를 이해해보자. 
 
 
 2. Servlet Container 생성과정
  
~~~JAVA
 public class ServletInitConfig implements WebApplicationInitializer {
 
     @Override
     public void onStartup(ServletContext container) {
 
         // Create the 'root' Spring application context
         AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
         rootContext.register(RootAppConfig.class);
 
         // Manage the lifecycle of the root application context
         container.addListener(new ContextLoaderListener(rootContext));
 
         // Create the dispatcher servlet's Spring application context
         AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
         dispatcherContext.register(WebAppConfig.class);
 
         ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
 
         // Add cuatom filters to servletContext
         FilterRegistration charEncodingFilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
         charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
         charEncodingFilterReg.setInitParameter("forceEncoding", "true");
         charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*");
 
     }
}
 ~~~

 

 

 

