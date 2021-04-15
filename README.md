# HappyHouse

## Vue와 Spring Boot를 이용한 부동산 검색 웹 사이트 제작프로젝트

## 팀 구성원

조석준 : 팀장

조현철 : 팀원

## 개인 별 업무 분담

조석준 : 프론트엔드

조현철 : 백엔드

## Install

### Backend

- 위치로 이동후 실행

```bash
cd backend

#window에서 실행 시
.\mvnw spring-boot:run
```

- [http://localhost:8000/swagger-ui.html을](http://localhost:8000/swagger-ui.html%EC%9D%84) 통해 swagger 확인 가능

### Frontend

- 위치로 이동후 실행

```bash
cd frontend
npm install
npm run serve
```

- [http://localhost:8080/](http://localhost:8080/) 에서 확인 가능

## 1. 요구사항 정의서(Usecase Diagram

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled.png)

## 2. 테이블 구조도(ERD)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%201.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%201.png)

## 3. 클래스 다이어그램

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%202.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%202.png)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%203.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%203.png)

## 4. 화면설계서

### 4.1 메인화면

메인 화면으로서 각종 기능에 접근이 가능.

- 메인화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%204.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%204.png)

- 로그인화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%205.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%205.png)

- 회원가입 화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%206.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%206.png)

### 4.2 아파트 및 주택 정보 검색

자신이 원하는 지역의 아파트와 주택 매물을 확인 할 수 있으며, 구글 지도로 해당 위치를 확인하고 상세 정보를 알수있다

- 지역 선택 후 지도 화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%207.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%207.png)

- 아파트 정보 보기 선택시 화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%208.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%208.png)

- 주택 정보 보기 선택시 화면

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%209.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%209.png)

### 4.3 공지사항

공지사항을 확인하고 관리자인 경우 글쓰기, 수정 및 삭제가 가능.

- 공지사항 목록

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2010.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2010.png)

- 공지사항 글작성

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2011.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2011.png)

- 공지사항 글수정

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2012.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2012.png)

- 공지사항 확인(사용자)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2013.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2013.png)

- 공지사항 확인 (관리자)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2014.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2014.png)

### 4.4 코로나 진료소

코로나 선별 진료소 및 국가 지정 안심 병원을 확인하고 검색을 통해 자신이 원하는 진료소와 병원을 찾기가 가능

- 코로나 선별 진료소

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2015.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2015.png)

- 국가 지정 안심 병원

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2016.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2016.png)

### 4.5 동네정보

자신이 즐겨찾기 지정한 지역의 주변 동네의 상권이나 편의 시설 등을 확인가능. 다른 지역의 편의 시설과 상권 등도 파악이 가능하다.

- 동네 업종 정보

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2017.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2017.png)

### 4.6 회원관리

관리자인 경우 회원에 대한 정보와 탈퇴 등의 관리가 가능

- 회원 관리 페이지

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2018.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2018.png)

### 4.7 관심지역

자신이 즐겨찾기 등록한 지역의 아파트 매물과 주택 매물을 빠르게 확인이 가능.

- 관심 지역

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2019.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2019.png)

- 관심 지역(아파트)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2020.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2020.png)

- 관심 지역 (주택)

![HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2021.png](HappyHouse%203825340127e94920bac0949c01dce7ef/Untitled%2021.png)