
# Java 설정을 통한 Spring 구조 이해하기

기존에는 xml방식으로 Spring 프로젝트를 만들었다. 익숙한 xml 설정은 대략적인 흐름도에 대한 이해만 있었고, 그 내부에 어떤 식으로 환경들을 구성하고
조립되는지에 대해 더 깊은 고민은 해보지 않았던 것 같았다.  

이번 Java Code로 Spring 설정을 통해 Spring의 아키텍처와 생성방식에 대해 이전보다는 조금 더 이해하기 위하여 해당 실습예제를 준비하였다. 


### 1. RootApplicationContext / WebApplicationContext 

  Java 코드로 Spring을 설정하기 전에 먼저 이해해야하는 부분은 RootApplicationContext와 WebApplicationContext이다. 
 
  처음에는 이 두 개의 용어가 Spring의 특별한 것을 일컫는줄 알았다. 특별한 기술용어를 지칭하는 것이 아닌 Spring의 ApplicationContext를
 계층적으로 분리하여 Root(부모)와 Web(자식)으로 나눈 ApplicationContext를 말하는 것이었다.(상속 구조를 생각하면 된다)

![ScreenShot](contexts.png)

위의 그림처럼 여러 개의 WebApplicationContext들은 단 하나의 RootApplicationContext에서 설정된 Bean들을 공유하여 사용한다. 
Spring에서는 왜 이렇게 하나의 Root와 여러개의 Web ApplicationContext만들 수 있도록 제공하였을까? 
당연히 확장성 때문이다. 각 레이어간의 책임 및 역할을 생각해보면, 왜 그림과 같은 구조로 만들었는지 어느정도 이해할 수 있다. 
 
 일반적으로 Java WebApplication 구조는 Client에 UI 랜더링, 컴포넌트를 처리하는 Presentation Layer, 비즈니스 로직을 처리하는 Business Layer, Database에 접근하는 Data Access Layer로 구성되어 있다. 
 여기서 RootApplicationContext는 Business Layer와 Data Access Layer에 필요한 Bean 생성과 관계설정등에 대한 설정 내용을 담고 있고,
 WebApplicationContext는 Presentation Layer인 SpringMVC와 관련된 설정 내용을 담고 있다. 
 
 데이터의 처리와 정합성, 트랜잭션의 일관성 등을 유지해야하는 Business Layer, Data Access Layer를 굳이 두개를 만들어 중복이 허용되도록 할 필요는 없을 것 같다.
 상대적으로 Client와 가장 밀접한 Presentation Layer는 여러 명의 Client에 필요한 부분을 맞춰야하기 때문에 WebApplicationContext를 여러개를 두지 않았나라고 생각이 들었다.
 
 
### 2. ServletContext 설정 및 Servlet 생성과정
Servlet 3.0 이상부터는 xml대신 Java 코드로 Web과 관련된 설정이 가능하다. Spring에서는 web 설정을 지원 해줄 수 있는 몇 개의 인터페이스를 제공한다.
그 중에서 WebApplicationInitializer를 선택하였다. 개인적으로 선택한 이유는 설정방식이 좀 더 명확하게 보이기 때문이다. 

아래는 web.xml을 대신하여 Java 코드로 구현한 Servlet 설정 방식이다. 
  
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
 WebApplicationInitializer 인터페이스를 구현하면 onStartup 메서드가 만들어진다. onStartup메서드의 인자값을 확인해보면 ServletContext타입의 변수를 파라미터로 받는것을 확인 할 수 있다.
 변수명도 container인걸 보니, Servlet container를 초기화하는 메서드인지를 명확하게 알 수 있다. 
 
 (여기서, ServletContext타입의 변수는 누가 던져 주는 것일까? SpringServletContainerInitializer라는 SPI(Service Provider Interface)인데 이 API가 자동으로 감지하여 컨테이너에 부트스트랩 한다고한다.)
 
 대략 위에서 아래로 훓어만 봐도 Root와 Web Context, 그리고 DispatcherServlet을 설정한다는 것을 알 수 있다. 
 
  **RootApplicationContext**
 위의 코드에서 RootContext와 관련된 부분만 요약하면, 
 - RootAppConfig 클래스에 설정된 Bean 정보를 ApplicationContext에 등록한다.
 - ServletContext container 변수에 RootApplicationContext는 addListener라는 메소드를 통해 ContextLoaderListener 생성자 인자값으로 
   ServletContext에 등록이 된다. 여기서 주의할 점은 Servlet이 아직 생성되지 않은 상태이다. 코드상의 표현 그대로 Context 구성만 하였다.
 
 여기서 ContextLoaderListener가 RootApplicationContext에서 가장 중요한 역할을 한다. 
 Root Context(Bean 생성, 관계설정 등)를 구성하고 이러한 Bean들을 여러 DispatcherServlet이 사용될 수 있도록 공유하는 역할과
 Servlet의 생성과 소멸 시점을 리스닝(?) 혹은 대기하고 있어, 그 시점에 라이프사이클 주기를 맞추는 역할을 해준다. 
 
 ContextLoaderListener 다이어그램
   
 ![Screenshot](contextLoaderListener.png) 
 
 WebApplicationContext는 container 변수에 addServlet 메서드를 통해 DispatcherServlet 생성자 인자값으로 등록된 것을 확인 할 수 있다. 
 
 
 


 
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

 

 

 

