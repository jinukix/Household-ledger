# 가계부 관리 시스템.

Docker Compose

~~~shell
./gradlew clean build
docker-compose up
~~~

서비스 레이어에 대한 테스트 코드를 작성했습니다.

ddl 파일은 ./init.sql 에 작성되어있습니다.

# API 명세서

## 회원가입
POST /users

~~~json
{
  "email": "이메일",
  "password": "비밀번호"
}
~~~

RESPONSE
201 CREATED

## 로그인
POST /users/login

~~~json
{
  "email": "이메일",
  "password": "비밀번호"
}
~~~

RESPONSE
200 OK
~~~json
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKSU5VSyIsImlhdCI6MTY0MjE1MTU2MCwiZXhwIjoxNj...
~~~

---

로그인을 한 유저만 가계부 관련 기능에 접근할 수 있습니다. <br>
Request 헤더에 key: "Authorization" | value: "Bearer `토큰`" 을 담아 보내야합니다. 

## 가계부 목록 조회
GET /household_ledgers?page=1

param: page | 페이지네이션 적용. (default page=1, pageSize = 10)

RESPONSE
~~~json
{
    "totalCount": 20,
    "list": [
        {
            "id": 1,
            "price": 1000,
            "description": "메모"
        },
        {
            "id": 2,
            "price": 2000,
            "description": "메모2"
        },
        ...
    ]
}
~~~

## 가계부 작성하기
POST /household_ledgers

~~~json
{
  "price": 1000,
  "description": "메모"
}
~~~

RESPONSE
201 CREATED

## 가계부 상세 조회
GET /household_ledgers/{householdLedgerId}

RESPONSE
200 OK
~~~json
{
    "id": 1,
    "price": 1000,
    "description": "메모"
}
~~~

## 가계부 수정하기
PUT /household_ledgers/{householdLedgerId}

~~~json
{
  "price": 1000,
  "description": "메모"
}
~~~

RESPONSE
200 OK

## 가계부 삭제하기

실제 레코드를 삭제하는 것이 아닌 is_deleted 칼럼으로 관리.

DELETE /household_ledgers/{householdLedgerId}

RESPONSE
204 NO_CONTENT
