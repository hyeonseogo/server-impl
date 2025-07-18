# 📘 Spring REST API: 회원 + 게시판 프로젝트

이 프로젝트는 Spring Boot, MyBatis, MySQL을 기반으로 한 **회원 관리 및 게시판 기능**을 제공하는 백엔드 RESTful API 서버입니다.  
JWT를 이용한 로그인 인증 기능과 BCrypt를 이용한 비밀번호 암호화가 포함되어 있으며,  
프론트엔드는 HTML + JS(fetch API)를 사용하여 JWT 기반 API와 연동됩니다.

---

## 사용 기술

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring MVC**
- **MyBatis**
- **MySQL**
- **Lombok**
- **JWT (JSON Web Token)**
- **BCrypt**
- **JUnit 5**
- **IntelliJ IDEA**
- **HTML + JavaScript (Vanilla)**

---

## 주요 기능

### [1] 회원 기능

- 회원가입
- 로그인 (JWT 토큰 발급)
- 회원 정보 조회 및 수정
- 로그아웃

### [2] 게시판 기능

- 게시글 작성 (로그인 사용자만 가능)
- 게시글 목록 조회 (최신순 + 10개 단위 페이징)
- 게시글 상세 조회
- 게시글 수정 / 삭제 (작성자 본인만 가능)

---

## 📁 프로젝트 구조

```
📁 backend/
├── src/
│ ├── main/java/com.koreait.restapi/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── service/
│ │ ├── mapper/
│ │ ├── jwt/
│ │ └── RestapiApplication.java
│ └── resources/
│ ├── mapper/
│ └── application.properties
└── database.sql

📁 frontend/
├── register.html # 회원가입
├── login.html # 로그인
├── post-list.html # 게시글 목록 (페이징)
├── post-detail.html # 게시글 상세 + 수정/삭제
├── create-post.html # 게시글 작성
├── update-post.html # 게시글 수정
├── mypage.html # 내 정보 조회 및 수정
```

---

## 실행 방법

### 1. MySQL 설정
프로젝트 루트에 포함된 [`database.sql`](./database.sql) 파일을 실행하면 데이터베이스와 테이블이 자동으로 생성됩니다.

MySQL Workbench 에서 파일을 열어 직접 실행할 수 있습니다.

생성되는 테이블:
- member: 사용자 정보 저장
- post: 게시글 정보 저장 (작성자 외래 키 연동, 삭제 시 게시글 자동 삭제)

---

### 2. 설정 파일
src/main/resources/application.properties
```
spring.application.name=restapi

spring.datasource.url=jdbc:mysql://localhost:3306/springtest?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.koreait.restapi.dto

mybatis.configuration.map-underscore-to-camel-case=true
```
이 파일은 .gitignore에 의해 GitHub에 올라가지 않습니다.

---

### 3. 프로젝트 실행
```
./gradlew bootRun
```
또는 IntelliJ에서 RestapiApplication.java 실행

---
### 프론트엔드 실행 방법
1. frontend/ 폴더 안의 .html 파일을 브라우저에서 열어 실행

2. HTML과 JavaScript는 fetch API를 통해 백엔드에 요청

3. 로그인 시 발급된 JWT 토큰은 localStorage에 저장되어 이후 인증에 사용됩니다.

---

### 인증 방식
- JWT 토큰은 로그인 후 응답으로 발급되며, localStorage에 저장

- 인증이 필요한 요청에는 반드시 Authorization: Bearer <토큰> 헤더 포함

---

### API 테스트 방법
- Postman으로 API 요청을 테스트할 수 있습니다.
- 모든 JWT 인증 요청에는 헤더 포함:
```
Authorization: Bearer <JWT 토큰>
```
---
## API 엔드포인트 요약

### [1] 회원 기능

| 메서드 | URL                         | 설명                         | 인증 필요 |
|--------|------------------------------|------------------------------|-----------|
| POST   | `/api/member/register`       | 회원가입                     | ❌        |
| POST   | `/api/member/login`          | 로그인 (JWT 발급)            | ❌        |
| GET    | `/api/member/info`           | 내 정보 조회                 | ✅ (JWT)  |
| PUT    | `/api/member/update`         | 회원 정보 수정               | ✅ (JWT)  |
| POST   | `/api/member/logout`         | 로그아웃                     | ✅ (JWT)  |

>  JWT가 필요한 API는 `Authorization: Bearer <토큰>` 헤더를 포함해야 합니다.

---

###  [2] 게시판 기능

| 메서드 | URL                   | 설명                                  | 인증 필요 |
|--------|------------------------|---------------------------------------|-----------|
| POST   | `/api/posts`           | 게시글 작성 (로그인 사용자만 가능)    | ✅ (JWT)  |
| GET    | `/api/posts`           | 게시글 목록 조회 (최신순, 페이징)     | ❌        |
| GET    | `/api/posts/{id}`      | 게시글 상세 조회                       | ❌        |
| PUT    | `/api/posts/{id}`      | 게시글 수정 (작성자만 가능)           | ✅ (JWT)  |
| DELETE | `/api/posts/{id}`      | 게시글 삭제 (작성자만 가능)           | ✅ (JWT)  |

---

### 페이징 파라미터 예시

GET /api/posts?page=0&size=10

- `page`: 0부터 시작
- `size`: 페이지당 게시글 수 (기본값: 10)

---

### 만든 사람
고현서
GitHub: https://github.com/hyeonseogo






