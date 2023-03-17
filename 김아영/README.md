
김아영asdfa

!!!
!!!!
!!
## What I Did

---

- 인프라 공부하기
    - [ ]  spring cloud 개념 공부
    - [ ]  도커, 젠킨스 인프라에 필요한 부분 개념 공부
    - [ ]  시스템 아키텍쳐 설계
    - [ ]  파이프라인 공부
    - [ ]  Nginx 설정 공부 ****
    - [ ]  Dockerfile 공부
    

## Note (기록, 괜찮은 부분)

---

[Termius - SSH platform for Mobile and Desktop](https://termius.com/)

[terminus 깔기]

## Spring Cloud

- MSA 구현을 위한 도구 모음
- MSA의 개발, 배포, 운영에 필요한 아키텍쳐를 쉽게 구성할 수 있도록 지원하는 Spring Boot 기반 프레임워크
- 분산 시스템 상에 필요한 여러 패턴들을 표준 패턴화 시켜 손쉽게 개발할 수 있도록 지원함

cloud Native application

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5d4b7b07-58cd-4883-a590-fdc261e259a3/Untitled.png)

## 명령어 모음집

```sql
# **etstat - 네트워크 접속, 라우팅 테이블, 네트워크 인터페이스의 통계 정보를 보여주는 도구**
netstat -ntlp

# 방화벽 상태 확인
ufw status

# 방화벽 열어줌
ufw allow 80/tcp

# 지금 실행중인 컨테이너 보기
sudo docker ps -a #-a는 멈춘것도 보여준다.
```

## 수동 배포 흐름

[쿠버네티스 알아보기 1편: 쿠버네티스와 컨테이너, 도커에 대한 기본 개념 | 인사이트리포트 | 삼성SDS](https://www.samsungsds.com/kr/insights/220222_kubernetes1.html)

[도커, 쿠버네티스 정리 - 컨테이너]

컨테이너: 앱이 구동되는 환경까지 감싸서 실행할 수 있도록 하는 격리 기술

ex) 도커에 젠킨스를 컨테이너로 올린다.

컨테이너 이미지 : 실행시 컨테이너가 되며

도커 : 컨테이너를 다루는 도구 중 가장 유명한 것

도커 이미지 : 서비스 운영에 필요한 서버 프로그램, 소스코드 및 라이브러리, 컴파일된 실행 파일을 묶는 형태

도커 컨테이너 : 이미지를 실행한 상태

ex)우분투 이미지 : 우분투를 실행하기 위한 모든 파일을 가지고 있는 것

쿠버네티스 : 컨테이너 런타임을 통해 컨테이너를 오케스트레이션 하는 도구

오케스트레이션 : 여러 서버에 걸친 컨테이너 및 사용하는 환경 설정을 관리하는 행위

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0501a2ff-d11c-43d1-a380-998304465227/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8703d68e-6cac-47d9-8981-aa8a1e822203/Untitled.png)

### 도커를 사용하는 이유?

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4833dec8-ec9d-4b1a-a12f-6aeacfffdca4/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2c9851cb-f890-45d4-a28a-8a9d8012705b/Untitled.png)

### 도커 명령어

```sql
# 도커 이미지를 빌드할때 사용하는 명령어 -t 옵션을 사용해 태그를 붙여서 이미지를 생성할 수 있다.
docker build
# .은 현재 디렉토리, 현재 디렉토리의 dockerfile을 빌드
ex) docker build -t myimage:latest.

# 빌드한 도커 이미지들을 확인할 수 있다. 이미지들의 태그명, ID, 생성 시간등을 확인할 수 있다.
docker images

docker run
```

## Issue (발생한 문제)

---

```sql
CREATE USER 'vita'@'%' identified with mysql_native_password by 'Vita500!
```

## Effort (노력)

---

## Solution

---

## What I Learned
