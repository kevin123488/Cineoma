# 

# 로그인, 회원가입 요청 보낼 때 필요한 부분은 값을 채워주고, 필요없는 부분은 null을 써주도록 하자 (테이블에 정의된 요소를 모두 써주어야 한다는 말. 순서까지 똑같이 지킬 필요는 없음. no는 쓸 필요 없음)

![](공통_요청_정리_assets/2022-07-29-10-51-28-image.png)

ex) login의 경우

{

    "email": null,

    "id": "입력",

    "imagePath": null,

    . . . 

    이런 식으로 필요한 부분은 값 입력, 필요없는 부분은 null

}

# 회원정보 수정 요청의 경우 백 코드는 다음과 같다

![](공통_요청_정리_assets/2022-07-29-12-03-01-image.png)

user.blabla로 접근해야 하는 값이 정해져 있기 때문에, 저기 명시된 id, nickname, intro, password, imagePath 값을 넣어주어야 한다. 수정할 부분은 별도의 수정할 값으로, 그 외의 값은 store에 저장된(로그인시 store에 모든 정보를 넣어둔다) 값을 이용하여 채워주자. 

# 로그인

- /user/login
  
  - method:
    
    - POST
  
  - body:
    
    ```json
    {
        "id": "아이디",
        "password": "비번"
    ```

# 

# 회원가입

- /user/
  
  - method:
    
    - POST
  
  - body:
    
    - ```json
      {    
          "userName": "이"
          "id": "아이디",
          "password": "비번",
          "nickname": "",
          "email": "",
          "myPoint": "",
          "imagePath": "",
      }
      ```

# 마이페이지

- /user/{id}
  
  - method:
    - GET
  - body:
    - key: Authorization
    - value: 로그인, 회원가입시 받은 토큰값

- 리턴형식
  
  - ```json
    {    
    
    }
    ```

# 

# 회원정보 수정

- /user/{id}
  
  - method:
    - PUT
  - header:
    - key: Authorization
    - value: 로그인, 회원가입시 받은 토큰값

- 리턴형식
  
  - ```json
    {    
    
    }
    ```

# 

# 팔로우 요청

- /follow
  
  - method:
    
    - POST
  
  - body
    
    - 팔로우 하는사람, 팔로우 받는 사람 id

# 친구 목록 불러오기

- /follow
  
  - method:
    
    - GET
  
  - body
    
    - 유저 id

# 

# 친구 삭제

- /follow
  
  - method:
    
    - DELETE
  
  - body
    
    - 삭제 요청하는 사람, 삭제 당하는 사람 id

# 

# 유저 검색

- /user/list
  
  - method:
    
    - GET
  
  - body
    
    - 검색어
    
    - 페이지
    
    - 오프셋

# 

# 로비

- /room
  
  - method:
    - GET
  - body

- 리턴형식
  
  - ```json
    {    
    
    }
    ```

# 

# 방생성

- /room
  
  - method:
    - POST

# 방 입, 퇴실

- /room/{roomNum}
  
  - method:
    
    - PUT
  
  - body
    
    - 방참여 여부 보내기

# 방 삭제

- /room/{roomNum}
  
  - method:
    
    - DELETE

- 리턴형식
  
  - ```json
    {    
    
    }
    ```

jpa 물어볼거 있음

# 웹소켓

(stomp 사용)

# 1.대기화면

# 1-1 채팅창

소켓 연결시 사용할 uri

Endpoint(roomSocket)

//프론트 입장에서 데이터를 담아 보내줄 uri
("/receiveChat") 

//프론트 입장에서 구독하다가 데이터를 받을  uri
//send로 메시지를 반환합니다.
("topic/sendChat/{roomNo}")

ex)topic/sendChat/107

- 리턴형식
  
  - ```json
    { 
    ("/receiveChat")
        f->b   
      roomNo: int,
      nickname:String,
      contant:String
    }
    { 
    ("topic/sendChat/{roomNo}")
        b->f   
      nickname:String,
      contant:String
    }
    ```

# 1.대기화면

# 1-2프로필화면

//프론트 입장에서 데이터를 담아 보내줄 uri
("/receiveProfile")

//프론트 입장에서 구독하다가 데이터를 받을 uri
//send로 메시지를 반환합니다.
("topic/sendProfile/{roomNo}")

ex)/sendChat/107

- 리턴형식
  
  - ```json
    { 
    ("/receiveProfile")
        f->b
    progress:String,//in and out 으로 입장인지 퇴장인지 구별하   
      roomNo: int,
      id:String
    }
    { 
    ("topic/sendProfile/{roomNo}")
        b->f  
    progress:String,//in and out 으로 입장인지 퇴장인지 구별하 
      nickname:String,
      id:String,//out일땐 id값만 줄 예
    imagePath:String,
    intro:String
    }
    ```

# 2. 인게임

# 게임 시작

소켓 연결시 사용할 uri

Endpoint(/mafiaSocket)


//프론트 입장에서 데이터를 담아 보내줄 uri
("/receiveMafia")

//프론트 입장에서 구독하다가 데이터를 받을 uri
//send로 메시지를 반환합니다.
("/sendMafia/{roomNo}")

ex)/sendChat/107



- - ```json
    소켓 투표 장점 : 서로 누구를 투표햇는지 실시간으로 볼 수 있음
    실제 마피아 게임 생각하면 누가 누구 뽑앗는지 표기 해주는게 맞을거같음
    {
      f->b
      progress : start
      roomNo: int,
    }
    
    {
      b->f
    
      progress : start,
    
      list {
        id :string,
        nickname : string,
        job : string,
        alive : bool,
        isHost:  bool
      }
    }
    ```
    
    ```json
    {
     f->b
    progress : voteDay,
    
    roomNo: int,
    id : string,
    nickname : string,
    vote : string,//누구를 뽑았는지 id
    alive : bool,
    isHost :  bool,
    //미션 관련으로 승리 했을 경우 알려주는 변수
    isWin : bool
    
    }
    
    {
    
    b->f
    //뽑힌사람이 없을경우 id값이 null
    // gameEnd :0) 안끝남 1) 시민승 2) 마피아승 3)중립승 
    {
    progress : voteDay,
    gameEnd : int,
    id : string,
    nickname : string,
    count : int
    // id, nickname은 죽은사람이 없는경우 "" 로 보내고 죽은사람이 있는경우 죽은 사람의 정보를 보
    
    }
    ```
  
  {
    {
  
      프론트에서 투표 끝내고 백으로 정보 보내주는 부분
      
        f->b
      마피아가 여러명이면
      소켓으로 구현 가능함
      progress : voteNight,
      
      id : string,//누구를 뽑았는지 일반 시민의 경우 "" 로 전달 혹은 그냥 안넣어서 보내도됨
      nickname : string,
      job : string,//내 직업이 뭔지
      vote : string,
      alive : bool,
      isHost :  bool,
      isWin : bool
  
    }
  
    {
  
      b->f
      //뽑힌사람이 없을경우 id값이 null
      // gameEnd :0) 안끝남 1) 시민승 2) 마피아승 3)중립승 
      progress : voteNight,
      gameEnd : int,
      id : string,
      nickname : string
  
    }
  
  ```
  
  ```

- /gameResult
  
  - method:
    
    - POST
  
  - body
    
    - 유저 정보
    
    - 승리여부
    
    - 직업

- 리턴형식
  
  - ```json
    {    
      nickname:
      contant:
    }
    ```

5인 마1 의1 중1 시2

웹소켓도 결국
게임 시작할때 백에서 누가 무슨직업이고 죽고 살았는지 등 게임 내용은 다 가지고 시작

낮->투표->밤->직업 행동

낮->투표
타이머 다됨

+ 다같이 동의해서 투표하러가자

모든 유저한테
타이머 다됨이 도착하거나
넘어가자는 무언가가 모두 도착
uri

기권표 추가
투표 할 uri

밤되면

직업들?
의사, 마피아, 시민, 경찰?

백에서-> 직업별 할 내용을 프론트로 보내줌
프론트에서 투표화면 보여주고직업별

uri

실시간 x
나

- 인게임

- /gameResult
  
  - method:
    
    - POST
  
  - body
    
    - 유저 정보
    
    - 승리여부
    
    - 직업

# 게임 끝

- /gameResult
  
  - method:
    
    - POST
  
  - body
    
    - 유저 정보
    
    - 승리여부
    
    - 직업
