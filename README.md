# 실시간 쿠폰 발급 프로젝트

<br/>

## 프로젝트 정보

#### 프로젝트 목적 : 동시성 제어와 이벤트로 발생하는 트래픽을 어떻게 처리할지 학습 및 구현하기 위해
#### 프로젝트 인원 : 1인
#### 기술 스택

<br/>

## 고민 과정
- 프로젝트를 최대한 간소화하기 위해 회원 인증 및 인가 기능을 쿠키를 통해 진행
- 우선은 Rdb로 진행한 다음, In-memory DB를 통해 성능 차이를 확인해보기
- 10만명의 회원이 10,000개의 쿠폰을 선착순으로 발급 받는 상황 가정

<br/>

#### 쿠폰 발급 수량은 어떻게 관리해야 될까? <br/>
    1. 수량 관련 DB 테이블을 하나 더 생성한다.
    2. 쿠폰 테이블의 전체 수량을 count하는 로직을 사용한다.

<br/>

#### 작업 큐를 활용 하지 않을 경우

<br/>

## 참고 자료
[redis 활용 관련 자료 링크](https://techblog.gccompany.co.kr/redis-kafka%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EA%B0%9C%EB%B0%9C%EA%B8%B0-feat-%EB%84%A4%EA%B3%A0%EC%99%95-ec6682e39731)



