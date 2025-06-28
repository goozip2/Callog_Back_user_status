# 🧍 User Status Service

### 📌 서비스 개요  
사용자의 `키`, `몸무게`, `성별`, `나이` 등 **건강 관련 신체 정보**를 관리하는 서비스입니다.  
회원가입 시 등록한 건강 정보를 저장하고, 로그인 시 해당 정보를 조회하여 사용자 맞춤 기능 제공에 활용합니다.

---

## ✅ 주요 기능

- 사용자 신체 정보 등록 (`POST: /userStatus`)
- 사용자 신체 정보 수정 (`POST: /userStatus/update`)
- 사용자 신체 정보 조회 (`GET: /userStatus`)

---

## 🧱 도메인 모델

| 필드명 | 설명 |
|--------|------|
| `statusId` | 신체 정보 ID (PK) |
| `userId` | 사용자 ID |
| `height` | 키 (cm) |
| `weight` | 몸무게 (kg) |
| `gender` | 성별 (MALE / FEMALE) |
| `age` | 나이 |

---

## 🔗 외부 서비스 연동

- `User Service`에서 **회원가입 시** 전달된 신체 정보를 등록
- `User Service`에서 **로그인 시** 사용자 ID 기반으로 신체 정보를 조회하여 함께 응답

---

## 🛠️ 기술 스택

| 분류 | 기술 |
|------|------|
| Backend | Spring Boot, JPA |
| 통신 | OpenFeign |
| DB | MySQL |
| 테스트 | Postman |

---

## 🔗 API 문서
### [Postman API Document - 건강정보 (User Status)](https://documenter.getpostman.com/view/20776466/2sB2xEBU7J)

---

## 🧩 디렉토리 구조
```
└─📦callog_user_status
    ├─📂advice
    │  └─📂parameter
    ├─📂common
    │  ├─📂dto
    │  └─📂exception
    ├─📂controller
    ├─📂domain
    ├─📂dto
    │  ├─📂request
    │  └─📂response
    ├─📂event
    │  ├─📂domain
    │  ├─📂producer
    │  └─📂scheduler
    ├─📂repository
    └─📂service
```

---
## ✍️ 개발자
| 이름 | 역할 | GitHub|
|------|------|--------|
| 정수연 | BackEnd |https://github.com/syjungsuyeon|

