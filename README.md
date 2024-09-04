# RecorDream-Aos [다운받으러가기](https://play.google.com/store/apps/details?id=com.team.recordream)
<img src="https://github.com/user-attachments/assets/7999e4a9-0bf5-46da-a1fb-8959d978f671" alt="1242_2208" width="600" height="600" />

## 💟 Contributors

|                                                    [김세훈](https://github.com/s9hn)                                                     |                                                 [최윤정](https://github.com/cbj0010)                                                  |                                                  [유지민](https://github.com/urjimyu)                                                  |
|:-------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://avatars.githubusercontent.com/u/81347125?v=4" width="200px" height="320dp">| <img src="https://avatars.githubusercontent.com/u/66460447?v=4" width="200px" height="320dp"> | <img src="https://avatars.githubusercontent.com/u/92876819?v=4" width="200px" height="320dp"> |
|                                                                 `홈뷰`<br>`기록하기`<br>`카드 뷰`<br>`음성녹음`<br>`검색필터` <br>`소셜로그인`                                                                 |                                                             <br>`푸시알림`<br>`보관함 뷰`<br>`코스상세`<br>`마이페이지`<br>`로그아웃`<br>`로딩 뷰`                                                              |                                                            `인스타 공유`<br>                                


## 👋 커밋 컨벤션
[Git Convention & Branch Strategy](https://www.notion.so/Git-Github-Convention-c4815fb2ce384be18826a83aec45d85a)


## 👋 코드 컨벤션
[Android Coding Convention](https://kotlinlang.org/docs/coding-conventions.html)


## 👋 브랜치전략
**브랜치 유형**
- **main** : 완성된 버전의 코드를 저장하는 브랜치
- **develop** : 개발이 진행되는 동안 완성된 코드를 저장하는 브랜치
- **feature** : 작은 단위의 작업이 진행되는 브랜치
- **hotfix** : 긴급한 오류를 해결하는 브랜치
- **refactor** : 유지보수 및 코드의 수정이 이뤄지는 곳의 브랜치
- 해당 작업을 위한 브랜치를 파서 작업합니다.
- 작업 완료 후 PR을 날리고 팀원들에게 크로스체크 후 머지합니다.

예시)

- dev/feature/이슈번호-작업하는 파일명
- dev/refactor/이슈번호-작업하는 파일명

## 📁 *****Foldering*****
```
📂 app
┣ 📂 manifests
┃ ┣ 📜 AndroidManifest.xml
┣ 📂 kotlin+java
┃ ┣ 📂 org.sopt.recordream
┃ ┃ ┣ 📂 data
┃ ┃ ┃ ┣ 📂 datasource
┃ ┃ ┃ ┃ ┣ 📂 local
┃ ┃ ┃ ┃ ┣ 📂 remote
┃ ┃ ┃ ┣ 📂 entity
┃ ┃ ┃ ┃ ┣ 📂 local
┃ ┃ ┃ ┃ ┣ 📂 remote
┃ ┃ ┃ ┃ ┃ ┣ 📂 request
┃ ┃ ┃ ┃ ┃ ┣ 📂 response
┃ ┃ ┃ ┣ 📂 repository
┃ ┃ ┣ 📂 di
┃ ┃ ┣ 📂 domain
┃ ┃ ┃ ┣ 📂 model
┃ ┃ ┃ ┣ 📂 repository
┃ ┃ ┃ ┣ 📂 usecase
┃ ┃ ┃ ┣ 📂 util
┃ ┃ ┣ 📂 mapper
┃ ┃ ┣ 📂 presentation
┃ ┃ ┃ ┣ 📂 common
┃ ┃ ┃ ┣ 📂 detail
┃ ┃ ┃ ┣ 📂 edit
┃ ┃ ┃ ┣ 📂 home
┃ ┃ ┃ ┣ 📂 login
┃ ┃ ┃ ┣ 📂 mypage
┃ ┃ ┃ ┣ 📂 record
┃ ┃ ┃ ┣ 📂 search
┃ ┃ ┃ ┣ 📂 splash
┃ ┃ ┃ ┣ 📂 storagy
┃ ┃  ┣ 📂 util
┃ ┃ ┃ ┣ 📂 customview
┃ ┃ ┃ ┣ 📂 extension
┃ ┃ ┃ ┣ 📂 interceptor
┃ ┃ ┃ ┣ 📂 manager
┃ ┃ ┃ ┣ 📂 recorder
┃ ┣ 📂 ui.theme

```

## 📝 프로젝트 설명

---

- 쉽고 빠른 꿈 아카이빙 서비스를 제공한다.
- 검색과 보관함 기능을 통해 꿈 기록을 관리할 수 있다.

## 📝 문제상황 정의

---
- **꿈 내용이 또렷하게 떠오르지 않는다.**
    - 여러 개의 꿈이 겹쳐서 떠오른다.
    - 희미한 이미지만 떠오른다.
- **꿈 내용을 쉽게 까먹는다.**
    - 기상 직후 꿈을 기록해야 한다는 사실 자체를 지각하지 못하거나 일어나자마자 부랴부랴 준비를 하다 보니 꿈 내용을 까먹게 된다.
- **자다 깨서 꿈 내용을 기록하는 것이 귀찮다.**
    - 잠자리 주변에서 핸드폰을 찾고, 메모장까지 들어가는 과정이 귀찮다.
    - 꿈 내용을 텍스트로 하나하나 입력하는 것이 귀찮다.
      
## 🎯 핵심 타겟

---

- 디지털 환경에서 간편하게 꿈을 아카이빙하고 공유하고 싶은 사람들

## 📍 주요 기능

---

<p align="center">
  <img src="https://github.com/user-attachments/assets/ac28aa5c-f378-48e0-84c3-34123f5f9539" align="center" width="40%">  
  <img src="https://github.com/user-attachments/assets/f797997e-07c0-45a8-95c7-851f31b0b701" align="center" width="40%">  
   <img src="https://github.com/user-attachments/assets/33414843-2b59-434c-b7df-26ead1821296" align="center" width="40%">  
   <img src="https://github.com/user-attachments/assets/33d64ca1-c071-4348-ac40-87a6d2b238ce" align="center" width="40%">  
</p>





