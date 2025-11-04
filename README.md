# MobbileApp-practice
- [GitHub 링크](https://github.com/FRONETW/MobbileApp-practice/blob/main/README.md)

---

## 처음 시작

---

## w03: 화면 구성
**배운 내용**
- **Text**: 적성한 문장을 화면에 출력
- **Image**: res 파일의 이미지로 화면에 이미지 출력
- **Column**: 이미지와 문장을 세로로 배치

**화면**
<p>
  <img src="imges/img/w03.png" width="200">
  <img src="imges/code_img/w03.png" width="200">
</p>

---

## w04: 프로필 카드 & 메시지 카드

### 프로필 카드
**배운 내용**
- **Row**: 옆으로 나란히 배열 가능
- **Spacer**: 빈 간격(여백)을 사용하여 보기 좋게 구성
- **.size**: 이미지나 박스의 크기 조절
- **padding**: 요소 안쪽 여백
- **margin**: 요소 바깥 여백

<p>
  <img src="imges/img/w04(프로필카드).png" width="200">
  <img src="imges/code_img/w04(프로필카드).png" width="200">
  <img src="imges/code_img/w04(프로필카드 출력).png" width="200">
</p>

### 메시지 카드
<p>
  <img src="imges/img/w04(메시지카드).png" width="200">
  <img src="imges/code_img/w04(메시지카드).png" width="200">
  <img src="imges/code_img/w04(이미지카드 출력).png" width="200">
</p>

### 화이트 모드 / 다크 모드
**미리보기**
- 다크 모드와 일반 모드 차이를 동시에 확인

<p>
  <img src="imges/img/w04(white).png" width="200">
  <img src="imges/img/w04(black).png" width="200">
  <img src="imges/code_img/w04(모드 전환).png" width="200">
</p>

---

## w05: 이벤트 처리

### 클릭
- Button의 `onClick`으로 클릭할 때마다 값 1씩 증가

<p>
  <img src="imges/img/w05(클릭).png" width="200">
  <img src="imges/code_img/w05(클릭함수).png" width="200">
  <img src="imges/code_img/w05(클릭화면).png" width="200">
</p>

### 타이머
- `LaunchedEffect`로 `isRunning`이 `true`일 때마다 `delay`를 주어  
  10ms마다 `timeInMillis`에 값을 추가 → 시간 흐름 구현

<p>
  <img src="imges/img/w05(타이머).png" width="200">
  <img src="imges/code_img/w05(타이머함수).png" width="200">
  <img src="imges/code_img/w05.png" width="200">
</p>

---

## w06: 버블 게임

### 게임화면
<p>
  <img src="imges/img/w06.png" width="200">
</p>

### UI
<p>
  <img src="imges/code_img/w06(UI).png" width="200">
  <img src="imges/code_img/w06(UI, 다시실행).png" width="200">
</p>

### 버블
<p>
  <img src="imges/code_img/w06(버블이동).png" width="200">
  <img src="imges/code_img/w06(버블 갯수).png" width="200">
</p>

### 이벤트
<p>
  <img src="imges/code_img/w06(버튼 이벤트).png" width="200">
  <img src="imges/code_img/w06(타이머).png" width="200">
</p>

---

## 스네이크 게임

### 게임화면
<p>
  <img src="imges/img/snakgame(1).png" width="200">
  <img src="imges/img/snakgame(2).png" width="200">
</p>

### 코드

#### 변수
<p>
  <img src="imges/code_img/snakgame(변수).png" width="200">
</p>
- 게임에 필요한: 스네이크, 방향, 음식, gameOver 상태, 게임 사이즈 설정

#### 조작
<p>
  <img src="imges/code_img/snakgame(조작키).png" width="200">
  <img src="imges/code_img/snakgame(조작키 이미지).png" width="200">
</p>

**게임 로직**
<ol>
  <li>딜레이 동안 조작 버튼 클릭 시 스네이크 방향 변경 → head에 저장</li>
  <li>newHead의 x, y가 gridSize 범위 밖이면 gameOver = true → 게임 종료</li>
  <li>딜레이 동안 이동 거리와 방향 기반으로 newSnake 생성, 이전 snake 삭제</li>
  <li>food를 먹으면 gridSize 범위 내 랜덤 x, y 위치에 새로운 food 생성</li>
</ol>

#### 오브젝트
<p>
  <img src="imges/code_img/snakgame(뱀,음식,화면).png" width="200">
</p>

#### 게임 종료
<p>
  <img src="imges/code_img/snakgame(게임종료).png" width="200">
</p>
- gameOver = true일 때 버튼 클릭 → gameOver = false로 변경 → 게임 재시작
