# REST API 정리 📝

> **REST (Representational State Transfer)**란 웹 상의 자원(문서, 이미지 등)을 자원의 이름(URI)으로 구분하여, 해당 자원의 상태(State)를 주고받는 아키텍처 원칙을 의미.

- **핵심 원칙 ✨**: `웹 상의 자원을 URI 자체만으로도 식별 가능하게 만들자!`
- 별도의 공식 표준안이 없는, 일종의 잘 만들어진 "가이드라인".
- 이 원칙을 따르는 API를 **RESTful API**라고 부름.

<br>

## URI 설계: 기존 방식 vs REST 방식

### 1. 기존 방식의 문제점

- **URI에 행위(동사)를 포함**함. (`list`, `add`, `edit` 등)
- HTTP 메서드는 `GET`과 `POST`만으로 대부분의 작업을 처리하여, URI만으로는 정확한 동작을 예측하기 어려움.
- 기능이 추가될 때마다 새로운 URI가 필요해 관리가 복잡해짐.

| 메서드 | URI | 설명 |
| :---- | :--- | :--- |
| `GET` | `/member/list.do` | 회원 목록 보기 |
| `POST` | `/member/add.do` | 회원 추가하기 |
| `POST` | `/member/edit.do?seq=1` | 1번 회원 수정하기 |
| `POST` | `/member/del.do?seq=1` | 1번 회원 삭제하기 |

<br>

### 2. REST 방식의 설계

- **URI는 자원(명사)만을 식별**함.
- **행위는 HTTP 메서드**(`GET`, `POST`, `PUT`, `DELETE` 등)로 표현함.
- URI가 직관적이고 일관성 있게 구성되어 예측이 가능함.

| 메서드 | URI | 설명 |
| :---- | :--- | :--- |
| `GET` | `/member` | 회원 목록 보기 |
| `POST` | `/member` | 회원 추가하기 |
| `PUT` | `/member/1` | 1번 회원 수정하기 |
| `DELETE` | `/member/1` | 1번 회원 삭제하기 |
| `GET` | `/member/{검색어}` | 특정 회원 검색하기 |

<br>

---

## REST API 설계 규칙 📜

### 1. URI는 자원을 표현 (명사, 동사 X)

URI는 리소스를 식별하는 데 중점을 둠. `getMember`나 `deletePost`처럼 행위를 나타내는 동사를 URI에 포함하지 않음.

- ✅ **Good**: `/member/1`, `/posts`
- ❌ **Bad**: `/getMember/1`, `/deletePost`

<br>

### 2. 행위는 HTTP 메서드로 표현 (동사)

리소스에 대한 행위(CRUD)는 HTTP Method로 명확하게 표현함.

| 메서드 | 역할 | 설명 |
| :---- | :--- | :--- |
| `GET` | **조회** (Read) | 서버의 리소스를 요청. |
| `POST` | **생성** (Create) | 서버에 새로운 리소스를 생성. |
| `PUT` | **전체 수정** (Update) | 리소스의 **전체**를 교체. |
| `PATCH` | **부분 수정** (Update) | 리소스의 **일부**만 수정. |
| `DELETE` | **삭제** (Delete) | 리소스를 삭제. |

💡 **`PUT` vs `PATCH` 예시**

`id`가 `hong`인 회원의 이름과 나이가 (`홍길동`, `20`)일 때, 나이만 `25`로 바꾸고 싶은 경우

- **PUT `/member/hong`**
  - 요청 바디에 `{ "name": "홍길동", "age": 25 }` 와 같이 **모든 필드**를 보내야 함.
  - SQL: `UPDATE tblMember SET name = '홍길동', age = 25 WHERE id = 'hong';`

- **PATCH `/member/hong`**
  - 요청 바디에 `{ "age": 25 }` 와 같이 **변경할 필드**만 보냄.
  - SQL: `UPDATE tblMember SET age = 25 WHERE id = 'hong';`

<br>

### 3. URI 스타일 가이드

- **구분자 (/)**: 계층 구조를 나타낼 때는 슬래시(`/`)를 사용. (예: `/member/1/posts`)
- **마지막 문자**: URI 마지막에 슬래시(`/`)를 포함하지 않음.
  - `.../member` (O) : `member`라는 자원을 의미.
  - `.../member/` (X) : `member`라는 폴더(디렉터리)로 오해될 수 있음.
- **가독성**: 하이픈(`-`)은 사용 가능, 언더바(`_`)는 비권장. (가독성 문제)
- **확장자**: URI에 파일 확장자(`.json`, `.xml` 등)를 포함하지 않음.

<br>

### 4. 응답 (Response) - 순수 데이터 반환

- REST API는 요청에 대한 응답으로 **순수 데이터**만 반환해야 함. (Ajax용 서버)
- **`JSON`**, **`XML`**, **`Text`** 형식만 반환.
- **`HTML`** 코드(웹 페이지)를 반환하지 않음 (X).
- 이는 클라이언트가 웹 브라우저, 모바일 앱 등 모든 기기/환경일 수 있다고 가정하기 때문.
