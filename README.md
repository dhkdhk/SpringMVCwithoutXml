

# RootApplicationContext와 WebApplicationContext의 이해


1. Spring Context 계층구조

![mvc-contexts]("./image/mvc_contexts.png")



1. RootApplicationContext / WebApplicationContext 살짝 훓어보기
 
  처음에는 이 두 개의 용어가 Spring의 특별한 것을 일컫는줄 알았다. 딱히, 특별한 기술용어를 지칭하는 것이 아닌 Spring의 ApplicationContext를
 계층적으로 분리하여 Root(부모)와 Web(자식)으로 나눈 ApplicationContext를 말하는 것이었다. (상속 구조를 생각하면 된다)
 
 그렇다면 왜 Spring은 ApplicationContext를 RootApplicationContext와 WebApplicationContext로 계층을 나누어 두 개 이상이 만들어질 수 있도록  ApplicationContext를 구성하였을까?
  
 이를 이해 하기 위해 ApplicationContext의 역할을 정리하면, Bean 생성과 관계설정, 후처리 등의 핵심적인 Spring 내에서 핵심적인
 역할을 하고 있다. 
 
 그러면, Bean에 대한 의존성 주입을 관리하는 ApplicationContext가 두 개 이상이 만들어지는 건데, 상속구조처럼 생각한다면 확장성과
 다형성을 위해 두 개이상을 만들 수 있다고 생각은 들지만, 관리하는건 더 어렵지 않을까? 라는 다시 미궁속의 궁금증만 남게 되었다.
  

 

 

 

