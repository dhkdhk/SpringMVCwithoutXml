
# Java 설정을 통한 Spring 구조 이해하기

기존에는 xml방식으로 Spring 프로젝트를 만들었다. 익숙한 xml 설정은 대략적인 흐름도에 대한 이해만 있었고, 그 내부에 어떤 식으로 환경들을 구성하고
조립되는지에 대해 더 깊은 고민은 해보지 않았던 것 같았다.  

이번 Java Code로 Spring 설정을 통해 Spring의 아키텍처와 생성방식에 대해 이전보다는 조금 더 이해하기 위하여 해당 프로젝트를 준비하였다. 


### 1. RootApplicationContext / WebApplicationContext 살짝 훓어보기

  Java 코드로 Spring을 설정하기 전에 먼저 이해해야하는 부분은 RootApplicationContext와 WebApplicationContext이다. 
 
  처음에는 이 두 개의 용어가 Spring의 특별한 것을 일컫는줄 알았다. 특별한 기술용어를 지칭하는 것이 아닌 Spring의 ApplicationContext를
 계층적으로 분리하여 Root(부모)와 Web(자식)으로 나눈 ApplicationContext를 말하는 것이었다. (상속 구조를 생각하면 된다)

[이미지파일]

 
 일반적으로 Java WebApplication 구조는 Client에 UI 랜더링을 처리하는 Presentation Layer, 비즈니스 로직을 처리하는 Business Layer, Database에 접근하는 Data Access Layer로 구성되어 있다. 
 여기서 RootApplicationContext는 Business Layer와 Data Access Layer에 필요한 Bean 생성과 관계설정등에 대한 설정 내용을 담고 있고,
 WebApplicationContext는 UI 랜더링을 처리하는 PresentationLayer와 관련된 설정 내용을 담고 있다. 
 
 그렇게해서, 여러 WebApplicationContext들은 하나의 RootApplicationContext를 두고 Service Layer와 Business Layer에 필요한 Bean들을 공유하여 사용한다. 
 
 그렇다면 왜 Spring은 ApplicationContext를 RootApplicationContext와 WebApplicationContext로 계층을 나누어 만들어질 수 있도록 ApplicationContext를 구성하였을까?
  
 Bean에 대한 의존성 주입을 관리하는 ApplicationContext가 두 개 이상이 만들어지는 건데, 상속구조처럼 생각한다면 확장성과
 다형성을 위해 두 개이상을 만들 수 있다고 생각은 들지만, 관리하는건 더 어렵지 않을까? 라는 이상한 궁금증이 생겼다.
 
 Java 코드로 Spring 설정하여, Spring의 Context를 이해해보자. 
 
 
### 2. ServletContext 생성과정
Servlet 3.0 이상부터는 xml대신 Java 코드로 Web과 관련된 설정이 가능하다. Spring에서는 web 설정을 지원 해줄 수 있는 몇 개의 인터페이스를 제공한다.
그 중에서 WebApplicationInitializer를 선택하였다. 개인적으로 선택한 이유는 설정방식이 좀 더 명확하게 보이기 때문이다. 

아래는 web.xml을 대신하여 Java 코드로 구현한 ServletContext 설정 방식이다. 
  
~~~JAVA
 public class ServletInitConfig implements WebApplicationInitializer {
 
     @Override
     public void onStartup(ServletContext container) {
 
         // RootApplicationContext 생성
         AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
         rootContext.register(RootAppConfig.class);
 
         // RootApplicationContext 라이프사이클 설정
         container.addListener(new ContextLoaderListener(rootContext));
 
         // DispatcherServlet의 WebApplicationContext 생성
         AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
         dispatcherContext.register(WebAppConfig.class);
 
         ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("/");
        
     }
}
 ~~~
 WebApplicationInitializer 인터페이스를 구현하면 onStartUp 메서드가 만들어진다. onStartUp메서드의 인자값을 확인해보면 ServletContext타입의 변수를 파라미터로 받는것을 확인 할 수 있다.
 변수명도 container인걸 보니, Servlet container를 초기화하는 메서드인지를 명확하게 알 수  있다. 
 
 (여기서, ServletContext타입의 변수는 누가 던져 주는 것일까? SpringServletContainerInitializer라는 SPI(Service Provider Interface)인데 이 API가 자동으로 감지하여 컨테이너에 부트스트랩 한다고한다.)
 
 대략 위에서 아래로 훓어만 봐도 Root와 Web Context, 그리고 DispatcherServlet을 설정한다. 
 
 ApplicationContext에 대한 설정이 Annotation방식으로 설정하였기에 부모, 자식 ApplicationContext는 AnnotationCofigWebApplicationContext타입으로 각 각 하나씩 생성하였다.
 여기서 먼저, rootContext에 resgister 메서드를 사용하여 Bean을 설정한 RootAppConfig 클래스타입을 등록하였다. 그리고 
 
 
 
 
 
 **RootApplicationContext**
 
 ~~~JAVA
@Configuration
@ComponentScan(basePackageClasses = {MysqlConfig.class, MemberRepository.class, MemberService.class},
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value= Controller.class))
public class RootAppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
 ~~~
 ~~~JAVA
 @Configuration
 public class MysqlConfig {
    
     private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
     private final String DRIVER_URL = "jdbc:mysql://localhost:3306/base?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
     private final String USER_NAME = "*****";
     private final String PASSWORD = "*****";
 
     @Bean
     public DataSource getDataSource() {
         DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName(DRIVER_CLASS_NAME);
         dataSource.setUrl(DRIVER_URL);
         dataSource.setUsername(USER_NAME);
         dataSource.setPassword(PASSWORD);
         return dataSource;
     }
 
     @Bean
     public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource){
         NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
         return jdbcTemplate;
     }
 
 }
 ~~~
 (Service와 Repository는 예제는 생략하겠다. Github 예제 참고)

 

 

 

