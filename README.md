### 개발환경

- 프레임워크:  Springboot 2.7.4
- 언어: Java 17
- 데이터베이스: H2


### 실행 및 테스트 

- **빌드방법 (board.report 폴더 안에서)**

```
./gradlew build
```

- **실행방법 (구글드라이브에서Jar파일 다운로드)** !!!다운로드 오래걸림!!!

URL: https://drive.google.com/file/d/1lxTCom-pjx4_utgFEqqNKUGhatOVXS3I/view?usp=share_link
```
java -jar board.report-0.0.1-SNAPSHOT.jar
```

- **실행URL**
```
http://localhost:9080/
```


- **H2데이터베이스 접근정보 (application.properties에 정의)**

```
http://localhost:9080/h2-console
```


### 설계내용

- 테이블 구성의 경우 RanK 테이블로 구성하여 검색어에 대한 카운트 정보를 담아줬습니다. data.sql과 schema.sql을 이용하여 서버 실행 시 자동으로 테이블 생성과 sample 데이터를 넣어줬습니다.
- HTML - Controller - Service - ServiceImpl - Mapper 형식으로 설계하였으며 보안을 위해 POST방식을 기본으로 하였습니다. 또한 HTML에서 웹 페이지를 빠르게 로드하고 동적으로 사용할 수 있게 Ajax를 사용했습니다. 시작되는 localhost:9080/ 을 기본으로 맵핑을 해주어 추가적으로 호출하는 url을 직접 입력하지 않도록  구성하여 개발과 테스트를 진행했습니다.
- 추후 확장성을 위해 다중상속을 했습니다.  공통 인터페이스(SearchApi.java)를 정의하여 API의 추가,수정이 용이하도록 했습니다.
- HTML에서 페이징 이동의 경우 검색했던 값을 기록하기 위해 메모리에 저장하였으며 일시적으로만 보관하기 위해 localStorage이 아닌 sessionStorage로 저장하였습니다.
- Controller에서 예외처리를 할 경우 CRUD시에 일부만 commit이 될 수 있기 때문에 service에서 예외처리 했습니다.
- 인기검색어의 경우 처음 페이지를 기동 하면서 보이도록 하였고 검색을 할 때 갱신되도록 하였습니다. 기록버튼 하나로 기록경신과 블로그검색을 같이 할 수 있도록 구성했습니다.

- 카카오API의 호출 실패시 네이버API를 호출 하도록 구성하고 사용자에게는 네이버에서 조회했다고 팝업창을 통해 알려줬습니다. (카카오블로그 검색 시스템이라는 가정)



### 문제해결 전략



1. **블로그 검색**

   1-1.  **키워드를 통해 블로그를 검색**

   - 입력된 검색어를 통해 API를 호출하고 응답된 JSON메시지를 화면에 출력했습니다. 값이 없을 경우에는 검색어 입력을 유도하기 위해 '검색어를 입력해 주세요.'의 메시지와 함께 팝업창을 보여주도록 했습니다. 또한 thumbnail의 경우 이미지를 출력해 주기 위해 key값을 체크하여 이미지로 출력했습니다.
   - 예시 Response

   ```
   {"documents":
   	[{"blogname":"ths0078님의블로그","contents":"돌고돈끝에!! 드디어 드디디다더라ㅓㄹ딛라ㅓㅣㄷ라ㅓ밍너;ㅣㅏㅓ리;ㅏ럼ㄴㅇ림ㅇㄴ 이ㅏ머ㅣㅇ;ㄹ나ㅓ이;라ㅓㅊㅁ너ㅏㅇ포마ㅓㄴ초핌ㄴ언미앋돌닫ㄹㄷ<b>랃디</b>덜달디ㅓㄹㄷ 네 맞아요 장갑만 강드고에요 ㅎㅎ.. 갠히 드고떄6강질렀네../ㅅ..ㅠ..ㅂ..ㅏ..ㅁ 그다음 옮긴 돈으로 크릿탈 밴드를 삿습니다 네 티가별로 않나네요...","datetime":"2014-02-26T15:41:00.000+09:00","thumbnail":"https://search2.kakaocdn.net/argon/130x130_85_c/D3gbb7IkHN1","title":"[Elsword]2014.2.26","url":"https://blog.naver.com/ths0078/100206338607"},{"blogname":"아랍어 통역·번역 회사 트라빅","contents":"위바-아 비발랴쉬] 누가 맘대로 행동하고 포기했는지 استغنى: ~ 없이 하다, 맘대로 행동하다 مانستغنش: 괜찮습니다 بِبلاش: 무료로 ​ ردي عليك مبقاش هيفيدك 발음 [<b>랃디</b> 알라이크 마바아-쉬 하예피-다크] 너에게 대답하는 것은 이제 소용없어 مبقاش: 더 이상 ~이 아니다 مبقاش: ما + يبقى: 부정 표현 + ~으로 남다, 지속...","datetime":"2021-07-27T15:53:00.000+09:00","thumbnail":"https://search2.kakaocdn.net/argon/130x130_85_c/IFwcRcCOscV","title":"돌아왔어 (راجع), 아므르 디아브 # 아랍노래시리즈2","url":"https://blog.naver.com/trabicservice/222447406404"},{"blogname":"베리귤잡","contents":"빵떡 인생처음 불꽃놀이 나는 짱구~~~~ 읺의랑 커플티 살앙해 ㅎㅎ 수크림 <b>랃디</b>~~~~에블바리 스쿠림 동네집에 이뿐 선인장 꽃도 폈당 울이 여누 감찌기 어매 기다리는 듕~ 바다 야식ㅎ 엌저라구 오늘도 열심히 살았다. 그려 나는 애미인디..그려..애비나 애미나 매한가지여~ 개싄나는 놀애 ㅎㅎ추억~...벌써 켜줘가ㅜ1년...","datetime":"2019-06-10T14:42:00.000+09:00","thumbnail":"https://search4.kakaocdn.net/argon/130x130_85_c/IhWlfvFAnJ2","title":"14","url":"https://blog.naver.com/akaddalki/221558641013"}],
   	"meta":{"is_end":true,"pageable_count":3,"total_count":3}}
   ```

   

   1-2.  **정렬 기능 지원**

   - 한 가지만 선택할 수 있도록 radio 버튼을 통해 정확도, 최신을 선택하여 accuracy, recency 값을 넘겨줘 API를 호출할 수 있도록 했습니다.

   

   1-3.  **페이징 형태로 제공하기**

   - 동적으로 페이지를 변환하기 위해 Ajex를 이용했습니다. API에서 호출 가능한 page가 1~50이기 때문에 페이지번호를 50번까지만 출력하도록 했습니다. 화면에 보일 블로그만 선택해야 하기 때문에 검색된 문서수(total_count)가 아닌 pageable_count를 10개씩 나누어 페이지 번호를 할당했습니다. 페이지 번호를 선택할 경우 해당 번호를 page={선택된 번호}로 호출하도록 했습니다.
   - 예시 Request

   ```
   https://dapi.kakao.com/v2/search/blog?query=카뱅&page=50
   ```

   

   1-4. **추후 새로운 API 추가 고려**

   - 추후에 새로운 API 추가된다면 Constants.java에 설정된 전역변수의 값을 수정하여 빠르게 적용할 수 있도록 구성했습니다. 또한 공통 인터페이스(SearchApi.java)를 통해 카카오API와 네이버API를 추가했습니다. ApiColler.java에서 타입에 따라 API가 선택되도록 했습니다.

   

   2. **인기 검색어**

   2-1. **인기 검색어 호출 **

- 서버 실행 시 초기에 지정한 데이터를 Read 하여 데이터를 출력하도록 개발하였습니다. 이는 처음에 한 번만 읽어봐서 보일 수 있도록 하였고 Thymeleaf를 이용하였습니다. 이후에는 검색버튼을 누를 때마다 Ajax로 호출하여 갱신하도록 했습니다.

- 예시 Response

  ```
  [{"title":"Korea","existTitle":0,"ranking":1,"searchCount":9},{"title":"Kakao","existTitle":0,"ranking":2,"searchCount":8},{"title":"Bank","existTitle":0,"ranking":3,"searchCount":7},{"title":"Bts","existTitle":0,"ranking":4,"searchCount":6},{"title":"chanyeong","existTitle":0,"ranking":5,"searchCount":5},{"title":"Together","existTitle":0,"ranking":6,"searchCount":4},{"title":"Communication","existTitle":0,"ranking":7,"searchCount":3},{"title":"ㄴㄴ","existTitle":0,"ranking":8,"searchCount":1}]
  ```

- 데이터를 SELECT 할 때, 많은 데이터를 불러온 이후 자르게 되면 비효율 적이기 때문에 SELECT 조건에 LIMIT 조건을 통해 최대 10개만 가져오도록 했습니다.

- SQL

  ```
  	<select id="selectTitleList" resultType="board.board.dto.RankDto">
  		<![CDATA[
  			SELECT ROW_NUMBER() OVER (ORDER BY SEARCH_COUNT DESC, TITLE) AS RANKING
  				 , TITLE
  				 , SEARCH_COUNT 
  			  FROM RANK 
  			 ORDER BY SEARCH_COUNT DESC
  			 	 , TITLE
  			 LIMIT 10
  		]]>
  	</select>
  ```



   	2-2. **인기 검색어 등록 및 조회수 증가**

- 조회버튼을 눌러 인기검색어를 호출할 경우에 해당 데이터가 DB에 있는지 확인합니다. 이미 있다면 조회수를 1 증가시켜주고 없다면 DB에 insert 시켜주고 조회수 1로 저장합니다.

- DB의 검색어의 데이터형이 varchar(50) 이기 때문에 50보다 클경우 50까지만 잘라서 보내도록 했습니다.

- DB에 데이터가 존재하는지 확인

  ```
  SQL:
      <select id="selectTitle" resultType="board.board.dto.RankDto">
              <![CDATA[
                  SELECT COUNT(*) AS EXIST_TITLE
                    FROM RANK
                   WHERE TITLE = #{title}
              ]]>
      </select>
  
  Java:
      try {
                  int existTitle = searchMapper.selectTitle(rank).getExistTitle();
  
                  if (existTitle > 0) {
                      searchMapper.updateTitleCount(rank);
                  } else {
                      searchMapper.insertTitle(rank);
                  }
              } catch (Exception e) {
                  throw new Exception("Error occurred while check title existence: " + e.getMessage());
              }
  ```

- 데이터가 존재할 경우

  ```
  	<update id="updateTitleCount" parameterType="string">
  		<![CDATA[
  			UPDATE RANK 
  			   SET SEARCH_COUNT = SEARCH_COUNT + 1 
  			 WHERE TITLE = #{title}
  		]]>
  	</update>
  ```
  
- 데이터가 없을 경우

  ```
  	<insert id="insertTitle" parameterType="string">
  		<![CDATA[
  		INSERT INTO RANK
  			(
  			 TITLE
  		   , SEARCH_COUNT
  			)
  			 VALUES
  			(
  			 #{title}
  		   , 1
  			)
  		]]>
  	</insert>
  ```

​	3. **카카오API 호출 실패**

- 카카오API 호출 실패시 컨트롤에서 타입에 naver를 담아 apiCaller를 호출했습니다. 키값의 명칭이 상이하여 네이버API호출 서비스단에서 명칭을 변경하여 호출하고 JSON 메시지를 return했습니다.
- 응답된 Key값의 명칭도 다르기 때문에 화면에 보여주기전 응답받은 ajax에서 메시지의 앞부분에 따라 카카오API의 응답메시지인지 네이버API의 응답메시지인지 구분해주었습니다.
- 카카오API 호출 실패하여 네이버API에서 정보를 보여준 후 page선택시에도 계속해서 카카오API를 호출하도록 했습니다. 또 실패할 경우 해당 페이지를 네이버API에서 호출했습니다.

- 예시 Test

  Constants.java

  ```
  public static final String Kakao_CLIENT_SECRET = "KakaoAK e81a5d0a03b6e4d20e128b3f8960dbd9";
  ->
  public static final String Kakao_CLIENT_SECRET = "KakaoAK for fail";
  ```

- Response

  ```
  {"lastBuildDate":"Wed, 22 Mar 2023 22:08:52 +0900","total":4,"start":1,"display":4,"items":[{"title":"<b>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</b>","link":"https://blog.naver.com/jiyonii/130050266903","description":"","bloggername":"jiyonii님의 블로그","bloggerlink":"blog.naver.com/jiyonii","postdate":"20060412"},{"title":"제주도여행 셋째날 / 공천포 게스트하우스 / 공천포 앞바다... ","link":"https://blog.naver.com/subin0718/10180263411","description":"두준두준 설리서리 +.+ <b>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</b> 저긴 또 뭘주는거야 입술빨대를 이용해 흡입중인 미숙씨. // 안묵는다며~ 가기 싫다며~~ 뭔 회냐며~~~~ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ... ","bloggername":"Hi! there :)","bloggerlink":"blog.naver.com/subin0718","postdate":"20131120"},{"title":"(AC) 사무라이 쇼다운 4 공략","link":"https://jkimgametips.tistory.com/377","description":"AB <b>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</b> 아마쿠사 아마쿠사 - 수라 오우마가토키 전 ↓↘→ + D 오우마가토키 후 ↓↙← + D 사령인 ↓↘→ + 베기 그대, 암전입멸하라 →↓↘ + 베기(상승), 베기... ","bloggername":"G.A.M.E == GAME","bloggerlink":"https://jkimgametips.tistory.com/","postdate":"20180521"},{"title":"[박지성 골 해외-현지팬반응] 리그 2호/시즌 4호 골 실황반응","link":"https://blog.naver.com/dokdo_han/100115990098","description":"Mainoldo Thank fuck for PARK Ole's_toe_poke Parrrrrrrrrrrrrrrkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk <b>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</b> <b>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk</b>... ","bloggername":"나만의 블로그.","bloggerlink":"blog.naver.com/dokdo_han","postdate":"20101107"}]}
  ```

  
