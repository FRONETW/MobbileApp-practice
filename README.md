# MobbileApp-practice
-https://github.com/FRONETW/MobbileApp-practice/blob/main/README.md

### 처음 시작 ###

## w03  ( 화면구성 )
<p>
  <img src="imges/img/w03.png" width="200">
  <img src="imges/code_img/w03.png" width="200">
</p>
- **배운 내용:**  
  Text: 적성한 문장을 화면에 출력
  Image: res파일의 이미지 파일을 이용해서 화면에 이미지 출력
  Column: 이미지와 문장을 쓰기 위해서 사용

## w04 ( 프로킬 카드, 메시지 카드 )
**프로킬_카드**
<p>
  <img src="imges/img/w04(프로필카드).png" width="200">
  <img src="imges/code_img/w04(프로필카드).png" width="200">
  <img src="imges/code_img/w04(프로필카드 출력).png" width="200">
</p>
- **배운 내용:**  
  Row: 옆으로 나란히  배열 가능하게됨
  Spacer: 빈 간격(여백)을 사용자가 좀더 보기 편하게 만듬
  .size: 이미지나 박스의 크기 조절
  padding: 요소 안 테두리의 여백
  margin: 요소 밖 다른 요소와의 간격

**메시지_카드**
<p>
  <img src="imges/img/w04(메시지카드).png" width="200">
  <img src="imges/code_img/w04(메시지카드).png" width="200">
  <img src="imges/code_img/w04(이미지카드 출력).png" width="200">
</p>

**_화이트 모드, 다크 모드_**
**미리보기**
-다크 모드와 일반 모드의 차이을 알기 위해서 두개를 동시에 사용함

<p>
  <img src="imges/img/w04(white).png" width="200">
  <img src="imges/img/w04(black).png" width="200">
  <img src="imges/code_img/w04(모드 전환).png" width="200">
</p>

## w05 ( 이벤트 처리 )
**클릭**
-Button의 onClick으로 클릭할때마다 처음 값에서 클릭마다 1씩 증가

<p>
  <img src="imges/img/w05(클릭).png" width="200">
  <img src="imges/code_img/w05(클릭함수).png" width="200">
  <img src="imges/code_img/w05(클릭화면).png" width="200">
</p>

**타이머**
-LaunchedEffect으로 isRunning이 true일때 마다 delay를 주어서 10밀리초마다 10밀리초 만큼 timeInMillis에 추가하여서 시간이 지남을 구현

<p>
  <img src="imges/img/w05(타이머).png" width="200">
  <img src="imges/code_img/w05(타이머함수).png" width="200">
  <img src="imges/code_img/w05.png" width="200">
</p>

## w06 ( 버블 게임 )
## 게임화면
<p>
  <img src="imges/img/w06.png" width="200">
</p>

**UI**
<p>
  <img src="imges/code_img/w06(UI).png" width="200">
  <img src="imges/code_img/w06(UI, 다시실행).png" width="200">
</p>

**버블**
<p>
  <img src="imges/code_img/w06(버블이동).png" width="200">
  <img src="imges/code_img/w06(버블 갯수).png" width="200">
</p>

**이벤트**
<p>
  <img src="imges/code_img/w06(버튼 이벤트).png" width="200">
  <img src="imges/code_img/w06(타이머).png" width="200">
</p>

## 스네이크 게임
**게임화면**
<p>
  <img src="imges/img/snakgame(1).png" width="200">
  <img src="imges/img/snakgame(2).png" width="200">
</p>

**코드**

## 변수
<p><img src="imges/code_img/snakgame(변수).png" widith="200"></p>
-게임에 필요한 스네이크, 방향, 음식, 게임오버(true 인지 false인지 인지하기 위해서), 게임 사이즈 설정 

## 조작
<p><img src="imges/code_img/snakgame(조작키).png" widith="200"><img src="imges/code_img/snakgame(조작키 이미지).png" widith="200"></p>
-딜레이 동안 조작 버튼을 클릭하였을 경우에 스네이크와 방향을 변화시켜서 head에 저장하여서 이동하는 방향을 변화
-newhead의 x,y가 gridsize의 값보다 크거나 값으면은 gameover이 true로 변화해서 게임 종료
-딜레이로 인해서 변한 시간동안 이동한 거리와 방향에 newsnake를 만들고 전에 있던 snake는 삭제됨
-만약 food를 먹었을 경우에는 food가 랜던한 gridsize(맵 사이즈)에 x,y 값이 새롭게 만들어짐

## 오브젝트
<p><img src="imges/code_img/snakgame(뱀,음식,화면).png" widith="200"></p>

## 게임종료
<p><img src="imges/code_img/snakgame(게임종료).png" widith="200"></p>
-gameOver이 true였을 경우에 작동되어서 버튼을 눌렀을 경우에 다시 gameover이 false로 변화되어서 게임이 다시 시작됨
