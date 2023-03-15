seoyoon's readme!!!!

# 개발일지

**[ 2023-03-14 ]**

### What I Did
---
- 회원가입 시 추가정보 입력받기
    - [x]  회원가입 완료 시 추가정보 입력받을 수 있게 response
    - [x]  추가 정보 입력받는 화면 구현
    - [ ]  화면에서 버튼 클릭 시 POST 요청 보내 저장
    - [ ]  프로젝트에 코드 옮기기

### Issue (발생한 문제, 기록할 가치있는 내용)
---

- 원래는 DB에 저장되어 있지 않은 유저가 소셜 로그인을 시도하면 → 전에 유저에게 추가정보 (닉네임, 성별, 나이, 키, 몸무게 등)를 입력받는 페이지로 Redirect시키고 → 그 후에 추가정보까지 입력 완료가 되면 DB에 저장해 회원가입 시키려고 했음
    
    `[CustomOAuth2UserService.java](http://CustomOAuth2UserService.java)` 의 process() 메소드 부분에서 User의 추가정보 값이 null이면 메소드의 리턴값인 response에 code와 message를 사용해 클라이언트 단에 올리고 추가정보 입력 페이지로 redirect 시키려고 했음. 
    
    하지만 Spring Security를 사용하려고 하니 소셜 로그인을 요청하는 순간부터 filter chaining 형태로 로직이 쭉 자동으로 이어져 ‘회원가입 완료 시점에 추가정보가 등록되어있지 않으면 추가정보 입력 페이지로 Redirect 시키는 로직’을 추가할 수 없었음


### Effort
---
- 구현 방법을 개선
    - 기존 : 유저에게 추가 정보까지 입력 받아야만 회원가입 처리가 되어 DB에 저장하려고 했음
    - 바꾼 방식 : 우선 소셜 로그인을 한 번이라도 하면 회원가입 처리 시키고, 추가 정보를 입력 받을 수 있는 플래그(ex. 사용자 나이) 등으로 추가정보 입력 여부 확인.
        
        회원가입 완료 시에는 추가정보 입력 페이지로 자동으로 넘기고, 혹시나 중도 이탈하거나 추가정보를 입력하지 않은 유저를 가려내기 위해 AccessToken 값을 header에 달고 들어오는 요청에서 추가정보 입력 여부 판단


### Result
---
성공!


# 개발일지

**[ 2023-03-15 ]**

### What I Did
---
- 주피터 노트북으로 파이스파크 코드 테스트

### Issue (발생한 문제, 기록할 가치있는 내용)
---


### Effort
---
### 1. 로컬에 Jupyter Notebook 설치

```bash
pip install notebook
```

### 2. Jupyter Notebook 실행

- [http://localhost:8888](http://localhost:8888) 연결

```bash
jupyter notebook
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8b6f55a7-5b92-40e0-9d6a-5666be9a6913/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0c60e1dd-3ff0-401b-aee5-708e6a584a9e/Untitled.png)

Jupyter Notebook 페이지로 자동 이동

### 3. Jupyter Notebook에 pyspark 설치

- pyspark 설치

```bash
!pip install pyspark
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ea9ce217-d336-431e-8317-cc6c0f402acd/Untitled.png)

- pyspark 버전 확인

```bash
from pyspark import SparkContext, SparkConf

sc = SparkContext("local[*]")
sc.version
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/abda54cb-01b5-4a3b-bfe3-3cfabd58beef/Untitled.png)


### Result
---
