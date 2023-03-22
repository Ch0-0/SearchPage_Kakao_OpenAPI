package board.board.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.common.Constants;

@Service("naverApiClient") //어떤 서비스인지 명시하고 쓸 때  @Qualifier("naverApiClient") <- 어떤 구체적인 빈을 사용할지 명시하는 용도로 사용되는 어노테이션입
public class NaverApiClient implements SearchApi{

	@Override
	public JsonObject callApi(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonObject jsonObject = null;
		
		String blogsearch = params.get("blogsearch");
		String sortValue = params.get("sortValue");
		String pageNumber = params.get("pageNumber");
		
		try {
			
			if(sortValue.equals("accuracy")) {
				sortValue="sim";
			}else if(sortValue.equals("recency")) {
				sortValue="date";
			}else {
				System.out.println("Check for KakaoController sortValue: " + sortValue);
			}
			
			if(pageNumber.equals("")||pageNumber==null) {
				pageNumber="1";
			}

			
			String titleword = URLEncoder.encode(blogsearch, "UTF-8");
			String sortword = URLEncoder.encode(sortValue, "UTF-8");
			String pageword = URLEncoder.encode(pageNumber, "UTF-8");
			String apiURL = Constants.Naver_URL_INFO + "query=" + titleword + Constants.Naver_URL_CONDITION_1 + sortword + Constants.Naver_URL_CONDITION_2 + pageword;

			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", Constants.Naver_CLIENT_ID);
			conn.setRequestProperty("X-Naver-Client-Secret", Constants.Naver_CLIENT_SECRET);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 200) { // 정상 호출
                System.out.println("Success NaverApi connect");
            } else { // 에러 발생
            	System.out.println("Fail NaverApi connect");
            }

			String line;
			StringBuilder response = new StringBuilder();
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			Gson gson = new Gson();
			jsonObject = gson.fromJson(response.toString(), JsonObject.class);

		} catch (Exception e) {
			System.out.println("Request Fail NaverApi: " + e.getMessage());
			
			
		}
		return jsonObject;
	}
}

