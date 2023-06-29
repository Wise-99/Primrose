# 💐 Primrose

## 📖 프로젝트 소개
| 소개 화면(메인 화면) | 꽃집 위치 검색 화면 | 꽃말 검색 | 꽃말 검색 후 |
|--|--|--|--|
| ![image](https://github.com/Wise-99/Primrose/assets/90273263/4e3fb34e-e328-41b3-98f2-6f7278ee2f8b) | ![](https://file.notion.so/f/s/3ca7c773-80ab-4b9e-bf82-9f63964108fd/Untitled.png?id=51dc20e8-7456-480a-94d1-a67279d5edd5&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421864015&signature=lVRgwjqHzAZxhdHn2Bh0pjkQKS9COYjbfBrU0tAHIiU&downloadName=Untitled.png) | ![](https://file.notion.so/f/s/ba7e0319-d91d-4064-a848-a6df31aab4a0/Untitled.png?id=2d58da25-f4ec-428c-8043-9582a8837e48&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421869935&signature=9FDWg9YExXR2sgxpJWeHUEKk_QczZ6lRG8YkvIbXlv8&downloadName=Untitled.png) | ![](https://file.notion.so/f/s/c760e582-74a0-4717-bc10-c6b3ca26d75b/Untitled.png?id=e9187e22-e6c1-41be-b82a-9401b949facc&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421876888&signature=YIJaGgcCldhMC0WOWDaIvq4J8e4bnGDIzJ7XA_qh3jw&downloadName=Untitled.png) |


> 💐 꽃을 선물하는 사람들에게 도움을 주기 위해 만든 앱으로 꽃의 이름이나 꽃말을 **검색하여 확인**할 수 있습니다.
> 
> -   **썸네일**을 통해 꽃의 사진도 확인 할 수 있습니다.
> -   지도를 통해 사용자의 위치에서 가까운 **꽃집의 위치**를 찾아볼 수 있습니다.
> -   해당 앱은 로그인을 해야 이용할 수 있으며 **reCAPTCHA**를 통해 인증을 해야 합니다.
> -   아이디와 비밀번호를 잃어버렸을 경우 개인 정보를 통해 찾을 수 있습니다.

## 📄 담당 업무

- 회원가입 / 로그인 기능 구현
    - **reCAPTCHA** 기능을 넣어 자동 회원가입 기능 방지
    - 이메일과 비밀번호를 받아 저장하여 **Firebase**에서 회원 관리를 할 수 있도록 함
    - 로그인 시 이메일과 비밀번호를 찾을 수 있는 기능 구현
        - 회원 가입시 입력 받은 실명과 전화번호를 **contains**로 검색하여 해당 사용자의 이메일 확인, 비밀번호는 아이디를 추가로 검색하여 확인
- 데이터베이스 꽃 이름 및 꽃말 검색 기능 구현
    - 입력한 단어를 받아 검색 후 미리 만들어놓은 객체(Flower)로 반환받아 **RecyclerView**에 표시
    - 검색 기능 이용 시 키보드를 제어하기 위해 **InputMethodManager** 사용
- **Google Map API**를 이용하여 사용자의 위치에서 꽃집 위치 확인 구현
    - 위치 사용 권한 확인 후 사용자의 현재 위치 표시
    - **Google Places API**를 이용하여 사용자의 위치로부터 1000m 이내의 꽃집 위치 마커로 표시
    - **Geocoder**를 통해 꽃집의 위치 좌표를 주소로 변환(**역 지오코딩**)하여 주소를 마커에 추가 표시

## 💡 문제 해결 과정

-   Firebase에서 포함되는 단어를 찾고자 할 경우 기능 구현에 대한 고민
    -   Firebase 공식 문서를 통해 **getChildren()과 contains**를 이용하여 해결
-   검색 후 키보드가 내려가지 않아 검색 결과를 보기 불편함
    -   키보드 제어를 위해 **InputMethodManager**를 사용함
    -   **hideSoftInputFromWindow**를 이용하여 키보드를 검색과 동시에 내림
