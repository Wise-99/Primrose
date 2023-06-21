# 💐 Primrose

## 📖 프로젝트 소개
| 소개 화면(메인 화면) | 꽃집 위치 검색 화면 | 꽃말 검색 | 꽃말 검색 후 |
|--|--|--|--|
| ![](https://file.notion.so/f/s/a5758721-a9d2-48a9-a82d-c636e9f48354/Untitled.png?id=ea1946c9-583d-4a1c-be63-0ec171f1aad6&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421855887&signature=TwFoTijWmb4d_Jc2BT_NvWPgGYVyOF8lJqOnNpwXsTg&downloadName=Untitled.png) | ![](https://file.notion.so/f/s/3ca7c773-80ab-4b9e-bf82-9f63964108fd/Untitled.png?id=51dc20e8-7456-480a-94d1-a67279d5edd5&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421864015&signature=lVRgwjqHzAZxhdHn2Bh0pjkQKS9COYjbfBrU0tAHIiU&downloadName=Untitled.png) | ![](https://file.notion.so/f/s/ba7e0319-d91d-4064-a848-a6df31aab4a0/Untitled.png?id=2d58da25-f4ec-428c-8043-9582a8837e48&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421869935&signature=9FDWg9YExXR2sgxpJWeHUEKk_QczZ6lRG8YkvIbXlv8&downloadName=Untitled.png) | ![](https://file.notion.so/f/s/c760e582-74a0-4717-bc10-c6b3ca26d75b/Untitled.png?id=e9187e22-e6c1-41be-b82a-9401b949facc&table=block&spaceId=4fee607c-9fab-47df-96d0-8ba12808c88d&expirationTimestamp=1687421876888&signature=YIJaGgcCldhMC0WOWDaIvq4J8e4bnGDIzJ7XA_qh3jw&downloadName=Untitled.png) |


> 💐 꽃을 선물하는 사람들에게 도움을 주기 위해 만든 앱으로 꽃의 이름이나 꽃말을 **검색하여 확인**할 수 있습니다.
> 
> -   **썸네일**을 통해 꽃의 사진도 확인 할 수 있습니다.
> -   지도를 통해 사용자의 위치에서 가까운 **꽃집의 위치**를 찾아볼 수 있습니다.
> -   해당 앱은 로그인을 해야 이용할 수 있으며 **reCAPTCHA**를 통해 인증을 해야 합니다.
> -   아이디와 비밀번호를 잃어버렸을 경우 개인 정보를 통해 찾을 수 있습니다.

## 📄 담당 업무

-   Java를 이용하여 스크롤 뷰, 리사이클러 뷰 등 앱 화면 구성
-   데이터베이스 꽃 이름 및 꽃말 검색 기능 구현
-   사용자의 위치에서 꽃집 위치 확인 구현

## 💡 깨달은 점

-   Firebase에서 포함되는 단어를 찾고자 할 경우 **contains()**를 이용함
-   검색 버튼 클릭 후 키보드 제어를 위해 **InputMethodManager**를 사용함
    -   **hideSoftInputFromWindow()**를 이용하여 키보드를 검색과 동시에 내림
-   **Google Places API와 위치 정보**를 이용하여 여러 장소에 대한 정보를 알려줄 수 있음
    -   Geocoder를 이용해 GPS 정보를 주소로 변환할 수 있음
