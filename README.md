# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 실습

## Chapter2 스프링 부트에서 테스트 코드를 작성하자.
<br>

### [ Day - 07/19(월) ]
 
<br>

1. 테스트 코드를 작성하여 얻는 이점
  - 단위 테스트는 개발 단계 초기에 문제를 발견하게 도와준다.
  - 단위 테스트는 개발자가 나중에 코드를 리팩토링하거나 라이브러리 업그레읻 등에서 기존 기능이 올바르게 작동하는지 확인할 수 있다.
  - 단위 테스트는 기능에 대한 불확실성을 감소시킬 수 있다.
   - 단위 테스트는 시스템에 대한 실제 문서를 제공한다. 즉, 단위 테스트 자체가 문서로 사용할 수 있다.

<br>

2. @SpringBootApplication
- 스프링 부트의 자동 설정
- 스프링 Bean 읽기와 생성을 모두 자동으로 설정
 - @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 해당 어노테이션이 있는 클래스는 프로젝트 최상단에 위치해야 한다.

<br>

3. 내장 WAS
 - @SpringBootApplicaion이 선언된 클래스의 main 메소드에서 SpringApplication.run으로 인해 내장 WAS가 실행된다.
  - 내장 WAS로 인하여 애플리케이션 실행 시 내부에서 WAS가 실행된다.
- 내장 WAS에 의하여 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행만 하면 된다.
 - 항상 내장 WAS를 사용할 필요는 없지만, 내장 WAS를 사용할 것을 권장한다.
  - 내장 WAS를 사용하여 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있다.

<br>

4. 테스트 코드
- 일반적으로 테스트 클래스는 대상 클래스 이름에 Test를 붙인다.(Hello클래스인 경우 HelloTest)
- @RunWith(SpringRunner.class) : 테스트를 진행할 때 JUnit에 내장된 실행자 외 다른 실행자를 실행시킨다.(즉, SpringRunner라는 스프링 실행자를 사용한다.)
 - @RunWith는 스프링 부트 테스트와 JUnit사이에 연결자 역할을 한다.
 - @WebMvcTest : 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션. 선언 시 @Controller, @ControllerAdvice등 사용 가능하다. (@Service, @Component, @Repository등은 사용 불가)
  - MockMvc : 웹 API 테스트 시 사용. 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
   - mvc.perform(get("/URI")) : MockMvc를 통해 해당 주소로 HTTP 메소드(GET 등)를 요청한다. 체이닝이 지원되어 여러 검증 기능을 이어서 선언할 수 있다.
    - ~.andExpect(~~) : mvc.perform의 결과를 검증한다. HTTP Header의 Status, 응답 본문의 내용 등을 검증한다.

<br>

5. 롬복
- 자바 개발할 때 자주 사용하는 코드 Getter, Setter, 기본 생성자, toString 등을 어노테이션으로 자동 생성해 준다.
 - 인텔리제이에서는 플러그인으로 쉽게 설치가 가능하다.
  - build.gradle의 dependencies에 롬복 추가 -> 플러그인 설치 -> Annotation Processor의 Enable annotation processing 체크
   - @Getter : 선언된 모든 필드의 get메소드를 생성
   - @RequiredArgsConstructor : 선언된 모든 final 필드가 포함된 생성자를 생성한다. final이 없는 필드는 생성자에 포함되지 않는다.

<br>

<hr>

## Chapter3 스프링 부트에서 JPA로 데이터베이스 다뤄보자.
<br>

### [ Day - 07/21(수) ]

<br>

1. JPA
 - 기존의 MyBatis(iBatis)와 같은 SQL 매퍼를 이용했을 때의 단점을 보완할 수 있다.
 - SQL 매퍼를 사용하면 SQL을 통해야만 데이터베이스에 저장하고 조회할 수 있다. 즉, 반복적인 SQL을 굉장히 많이 작성해야 하는 단점이 존재한다.
 - 또한, 패러다임 불일치 문제가 발생한다.
 - => RDB는 어떻게 데이터를 저장할 지 초점이 맞춰진 기술이고, 객체지향 프로그래밍 언어는 메시지를 기반으로 기능과 속성을 한 곳에 관리하는 기술이다.
 - JPA는 이러한 2개의 다른 영역을 중간에서 패러다임 일치를 시켜주는 기술이다.
 - 개발자는 객체지향으로 프로그래밍 하고, JPA가 이를 RDB에 맞게 SQL을 대신 생성하여 실행시켜준다. 그래서 SQL에 종석적인 개발을 하지 않아도 된다.
 - 객체 중심으로 개발하다 보니, 생산성 향사과 유지 보수가 편해져서 JPA는 점점 표준 기술로 자리 잡고 있다.

<br>

 2. Spring Data JPA
 - JPA는 인터페이스로서 자바 표준 명세서이다.
 - Hibernate, Eclipse Link등을 이용하여 JPA를 사용하기 위해 구현한다.
 - 이러한 구현체들을 좀 더 쉽게 사용하기 위해 추상화 시킨 것이 Sping Data JPA라는 모듈이다.
 - JPA <- Hibernate <- Spring Data JPA
- Spring Data JPA가 등장한 이유 두 가지
    - 1. 구현체 교체의 용이성

        Hibernate 외에 다른 구현체로 쉽게 교체할 수 있다.

    - 2. 저장소 교체의 용이성

        RDB 외 다른 저장소로 쉽게 교체할 수 있다.

<br>

3. 프로젝트에 Spring Data JPA 적용하기

- spring-boot-starter-data-jpa
    - 스프링 부트용 Spring Data JPA 추상화 라이브러리
    - 스프링 부트 버전에 맞춰 자동으로 JPA 관련 라이브러리들의 버전을 관리해 준다.
- h2
    - 인메모리 관리형 데이터베이스
    - 별도의 설치가 필요 없이 프로젝트 의존성만으로 관리할 수 있다.
    - 메모리에서 실행되기 때문에 애플리케이션을 재시작할 때마다 초기화된다는 점을 이용하여 테이스 용도로 많이 사용된다.
    - 이 책에서는 JPA의 테스트, 로컬 환경에서의 구동에서 사용한다.
- domain
    - 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제 영역
    - MyBatis에서 xml에 쿼리를 담고, 클래스는 오로지 결과만 담던 일들이 모두 도메인 클래스에서 해결된다.
- @Entity
    - 테이블과 링크될 클래스임을 나타낸다.
    - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매핑한다.
    - ex) SalesManager.java → sales_manager table
- @id
    - 해당 테이블의 PK 필드를 나타낸다.
- @GeneratedValue
    - PK 생성 규칙을 나타낸다.
    - 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야한 auto_increment가 된다.
- @Column
    - 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을 때 사용한다.
- Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.
- Setter가 없기 때문에 생성자를 통해 최종값을 채운 후 DB에 삽입한다.
- @Builder를 통해 제공되는 빌더 클래스를 이용하여 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 있다.
  
<br>

### [ Day - 07/21(수) ]

<br>

- MyBatis에서 Dao라고 불리는 것인 JPA에서는 Repository라고 부르며, 인터페이스로 생성된다.
  - 인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입>를 상송하면 기본 CRUD 메소드가 자동으로 생성된다.
  - @Repository도 추가할 필요가 없다.
  - Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
- JpaRepository의 save 메소드
  - 테이블에 insert/update 쿼리를 실행한다.
  - id 값이 있다면 update, 없다면 insert 쿼리가 실행된다.
- JpaRepository의 finaALL 메소드
  - 테이블에 있는 모든 데이터를 조회해오는 메소드.
- JPA에서 실행된 쿼리 보는 방법
  - application.properties에서 spring.jpa.show_sql=true 를 입력한다.
  - MySQL 버전으로 사용하기 위해서는 spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect 를 입력한다.

### [ Day - 07/29(목) ]

<br>

- Spring 웹 계층 에는 Web Layer, Service Layer, Repository Layer, DTOs, Domain Model이 있다.
- Web Layer
  - 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemaker 등의 뷰 템플릿 영역
  - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부 요청과 응답에 대한 전반적인 영역을 이야기한다.
- Service Layer
  - @Service에 사용되는 서비스 영억.
  - 일반적으로 Controller와 Dao의 중간 영역에서 사용된다.
  - @Transactional이 사용되어야 하는 영역이기도 한다.
- Repository Layer
  - Database와 같이 데이터 저장소에 접근하는 영역이다.
  - 기존에 개발했다면, Dao(Data Access Object) 영역으로 이해하면 된다.
- Dtos
  - Dto(Data Transfer Object)는 계층 간에 데이터 교환을 위한 객체를 이야기하며, Dtos는 이들의 영역을 이야기한다.
  - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기한다.
- Domain Model
  - 도메인이라 불리는 개발 대상을 만든 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것을 도메인 모델이라고 한다.
  - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있다.
  - @Entity를 사용해봤다면, @Entity가 사용된 영역 역시 도메인 모델이라 이해하면 된다.
  - 다만, 무조건 데이터베이스의 테이블 관계가 있어야만 하는 것은 아니다.
  - VO 처럼 값 객체들도 이 영역에 해당하기 때문이다.

<br>

- Bean 주입
  - Bean 주입 방법 : @Autowired, setter, 생성자
  - 가장 권장하는 방식은 생성자로 주입 받는 방식이다.
  - 롬복의 @RequiredArgsContructor를 이용하면 final이 선언된 필드를 인자값으로 하는 생성자 주입을 간단하게 처리할 수 있다.
  - 생성자를 직접 안 쓰고 롬복 어노테이션을 사용하는 이유는 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있기 때문이다.
- 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
  - Entity 클래스는 데이터베이스와 맞닿는 핵심 클래스이다.
- JPA Auditing으로 생성시간/수정시간 자동화
  - java8부터는 Date가 아닌 LocalDate, LocalDateTime을 사용하는 것이 좋다.
  - @MappedSuperclass : JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
  - @EntityListners(AudingEntityListner.class) : BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
  - @CreateDate : Entity가 생성되어 저장될 때 시간이 자동 저장된다.
  - @LastModifiedDate : 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
  - 자동화가 필요한 Entity는 BaseTimeEntity를 상속 받는다.
  - JPA Auditing 어노테이션들을 모두 활성화 하기 위해서는 Aplication 클래스에 @EnableJpaAuditing 어노테이션을 추가해야 한다.