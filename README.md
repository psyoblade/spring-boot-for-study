# spring-boot-for-study
spring boot project for study

## 1. 프로젝트 초기화 

### 1-1. Git 프로젝트 클론
```bash
git clone https://github.com/psyoblade/spring-boot-for-study.git
```

### 1-2. 스프링 초기화
* [spring initializr](https://start.spring.io) 에 접속하여 프로젝트를 생성합니다
  - spring web, spring data jpa, lombok, h2 database
  - ex_ group: me.spring, name: junit
  - 생성된 junit.zip 파일을 생성된 경로에 압축해제 (root dir 없이 압축해제 -j 옵션) 
```bash
unzip -j junit.zip
```

### 1-3. `.gitignore` 생성
* [gitignore.io](https://www.toptal.com/developers/gitignore) 통해서 초기화
  - gradle, intellij+all, java, macos, windows

### 1-4. 프로젝트 구조생성
> 게시판에 글을 작성하는 예제를 통해서 JUnit5 를 학습합니다

* 프로젝트 패키지 생성, 객체 클래스 및 서비스 인터페이스 생성
```bash
Root: src/main/java/me/spring/junit
Packages:
  - JunitApplication
    - domain
      - Member : id, name, email (Lombok)
      - Post : id, title, contents
      - PostStatus = { CREATED, UPDATED, DELETED }
    - member
      - MemberService : Optional<Member> findById(Long memberId)
      - MemberRepository extends JpaRepository<Member, Long>
    - post
      - PostService : createNewPost, validate
```

* 테스트 환경을 위한 단위 테스트 템플릿 생성
  - 인텔리제이 환경설정(Preferences) 의 `File and Code Tempaltes` 항목에서 `JUnit5 Test Method`항목을 아래와 같이 수정합니다 
```java
@org.junit.jupiter.api.Test
void ${NAME}() {
  // Given

  // When

  // Then
  ${BODY}
}
```


## 2. JUnit5 와 Mockito 를 통한 단위 테스트 실습
> [JUnit5 + Mockito](https://github.com/psyoblade/junit-for-dummies) 페이지에 접속하여 실습을 수행합니다 

* 단위 테스트는 가장 간단한 `MemberService`를 이용해서 시작합니다 (Shift+Command+T)
  - 단위 테스트 생성은 아까 만들어 두었던 템플릿을 이용합니다  (Commnd+N)


