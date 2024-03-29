## REST API PROJECT - AnonCom

# 🖥️ 프로젝트 소개

<hr>

많은 커뮤니티가 존재하지만 대부분은 각 목적에 맞는 카테고리에 따라 커뮤니티가 세분화됩니다.<br>
하지만 지역에 따라 세분화되는 커뮤니티들이 없어서 각 지역만의 정보들을 얻을 수 있는 커뮤니티가 없었습니다.<br>
이러한 단점을 보완할 수 있는 커뮤니티를 만들기 위해 이 프로젝트를 진행하였습니다.<br>
지역간의 익명 커뮤니티, Anonymous Community를 줄인 AnonCom의 탄생 배경입니다.

## 🕰️ 개발 기간

<hr>

* 2023.12.27 ~ 2024.02.07

## 🧑‍🤝‍🧑 맴버 구성

<hr>

* BE (1명) : 조종원


* FE (2명) : 함승완, 계동환

## ⚙️ 개발 환경

<hr>

* JAVA : 17
* IDE : IntelliJ
* Spring Boot : 3.1
* DataBase : MySQL, H2

## 📌 주요 핵심 기능

<hr>

### 일반 사용자 기준

### 회원

* #### 가입

    * 회원가입을 할 때 닉네임, 이메일 등이 겹쳐지지 않는지 검증
    * 이메일을 입력한 후, 작성한 이메일로 전송된 인증코드를 정확하게 입력한 후 인증까지 해야지 회원가입 되도록 구현
    * 회원 가입 시 자신의 원하는 프로필 사진 하나를 지정할 수 있도록 구현

* #### 로그인

    * 로그인 시 accessToken과 refreshToken은을 생성, accessToken은 localStorage에 저장, refreshToken은 db에 저장 및 HTTP Header에 담음
    * accessToken의 만료시간 지나면 HTTP Header의 refreshToken값을 서버에 보내 db와 검증시켜 accessToken을 재발급

### 게시글

* #### 좋아요&싫어요(반응)

    * 각 게시글마다 모든 유저는 좋아요, 혹은 싫어요를 달 수 있고 한 번 반응한 게시글에는 더 이상 반응할 수 없도록 구현

* #### 수정&삭제

    * 게시글의 수정과 삭제는 게시글을 작성한 본인이 아니면 접근할 수 없도록 설정한다.
    * 게시글 삭제같은 경우에는 실제로 삭제되어 db에도 사라지고 게시글 불러오기를 해봐도 보이지 않는다.

* #### 게시글 불러오기

    * 기본적으로는 시간순으로 작성된 모든 게시글들을 무한 가져올 수 있지만, 사용자가 특정 조건들을 지정하면 해당 조건과 일치하는 게시글들만을 가져온다.

      특정 조건 => 지역, 카테고리, 제목, 게시글 내용, 추천순, 생성날짜 순

### 댓글&대댓글

* #### 좋아요&싫어요(반응)

    * 댓글은 게시글과 마찬가지로 좋아요, 혹은 싫어요를 달 수 있고 한 번 반응한 댓글에는 더 이상 반응할 수 없도록 구현

* #### 수정&삭제

    * 댓글 및 대댓글의 수정과 삭제는 해당 글을 작성한 본인이 아니면 접근할 수 없도록 설정한다.
    * 댓글과 대댓글의 삭제같은 경우에는 삭제하면 해당 글의 내용 대신 삭제된 댓글이라는 안내 문구가 출력된다.

### 쪽지

* #### 작성

    * 쪽지를 보내려는 대상들을 선택해서 보내고싶은 유저들한테 쪽지를 보낼 수 있다. 만일 쪽지를 보내는데 실패한 대상은 리스트에 담에 따로 출력해준다.

* #### 관리

    * 자신의 원하는 쪽지들은 따로 보관할 수 있다
    * 자신이 원하는 쪽지들은 스팸처리하고, 스팸처리한 쪽지를 보낸 사용자한테서 더 이상 쪽지가 오지 않게 설정되고, 스펨 목록에 해당 사용자를 추가한다.
    * 부적절한 내용이 포함되었다고 판단할 시, 해당 쪽지를 신고할 수 있다

### 알림

* #### 알림

    * 자신이 작성한 게시글에 댓글, 혹은 댓글 등에 반응이 올 시, 그리고 쪽지가 왔다면 추가적인 알림이 온다.

<hr>

### 관리자 기준

### 공지

* #### 작성 및 삭제

    * 관리자의 권한을 가진 계정만이 공지를 작성하고 삭제할 수 있다.

### 사용자 차단

* #### 차단 기능

    * 일반 사용자들이 신고를 한 것들 중에서 관리자들이 임의로 판단해 정도를 넘어섰다고 판단한 사용자들은 관리자들이 선고한 해당 시간 기준 7일간 사용자가 로그인을 못 하게 막는다.

