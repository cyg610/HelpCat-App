1. 프로젝트 개요
여러 방송을 통해 고양이, 강아지 공장이 관심을 받았고 그로 인해 반려동물을 구매하지 말고 분양하자는 의견을 관심 있게 본 적이 있습니다.
그래서 관심이 있던 유기 고양이, 강아지 분양을 주제로 작품을 만들었습니다.

![그림1](https://user-images.githubusercontent.com/39265738/113573181-923fb500-9654-11eb-9f2c-a2461fe0e9e3.png)

2. 화면 구성 - 인트로화면

![그림4](https://user-images.githubusercontent.com/39265738/113573192-979cff80-9654-11eb-85e1-234acb4c2087.png)

Relative Layout으로 고양이 GIF를 가운데에 배치하고 GIF를 기준으로 위와 아래에 TEXTVIEW를 배치했습니다.

3.화면 구성 - 메인(리스트뷰)

![그림3](https://user-images.githubusercontent.com/39265738/113573190-966bd280-9654-11eb-82bc-69351c787138.png)

메인화면은 리스트뷰로 이미지뷰 3개와 Textview 4개 버튼 하나로 구성했습니다.
또한 이미지 위에 텍스트뷰가 뜨도록 구성하고 분양을 모집하고 있다면 텍스트뷰를 숨기고 분양이 완료됐다면 텍스트 뷰가 보이도록 구현헀습니다.

4. 화면 구성 -상세페이지

![그림9](https://user-images.githubusercontent.com/39265738/113573663-87395480-9655-11eb-92ba-b996e6fb90fc.png)

상세 페이지는 Relative 레이아웃으로 하단에 버튼을 두고 그 안을 LinearLayout으로 이미지뷰부터 나열했습니다. 


5. DataClass

![그림5](https://user-images.githubusercontent.com/39265738/113573194-98359600-9654-11eb-8e88-f68d76a7251e.png)

DataClass입니다. ListView Item에 출력될 데이터를 위한 클래스 정의했습니다.

6. API 관련


![캡처](https://user-images.githubusercontent.com/39265738/113573174-910e8800-9654-11eb-8b2a-a37b7387f150.JPG)
![캡처2](https://user-images.githubusercontent.com/39265738/113573177-91a71e80-9654-11eb-9527-86d539d17a6e.JPG)
![캡처3](https://user-images.githubusercontent.com/39265738/113573179-91a71e80-9654-11eb-9cbc-81da1a7e2cbc.JPG)

OPEN API를 받아오고 Adapter 객체 생성 및 데이터 바인딩을 했습니다.

7. 세부 페이지

![캡처5](https://user-images.githubusercontent.com/39265738/113574392-f82d3c00-9656-11eb-9cbf-04472e9235da.JPG)

intent를 통해 문자열로 데이터를 전달받아 데이터를 출력하고 이미지는 bitmap으로 해당 주소값의 이미지를 출력하도록 했습니다.

8. 화면 설계

![그림8](https://user-images.githubusercontent.com/39265738/113573157-86ec8980-9654-11eb-9f6f-f97d2621c34e.png)

인트로는 3초 후 메인화면, 리스트뷰로 이동하게 하였습니다.
메인페이지에서는 이미지뷰를 누르면 상세페이지로 전화버튼을 누르면 전화 다이어그램으로 이동해 해당 번호로 연결되도록 했습니다.
상세페이지의 전화 연결 버튼 또한 해당 연락처로 전화 연결을 하도록 했습니다.

9. 이미지 시연 영상

https://user-images.githubusercontent.com/39265738/113574631-738eed80-9657-11eb-8809-5d050ea619a5.mp4






