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

