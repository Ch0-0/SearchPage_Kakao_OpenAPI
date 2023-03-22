package board.board.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.common.Constants;

@Service("kakaoApiClient")//Component
public class KakaoApiClient implements SearchApi{
	

	@Override
	public JsonObject callApi(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonObject jsonObject = null;
		
		String blogsearch = params.get("blogsearch");
		String sortValue = params.get("sortValue");
		String pageNumber = params.get("pageNumber");
		
		try {

			
			String titleword = URLEncoder.encode(blogsearch, "UTF-8");
			System.out.println(blogsearch+"titlewordtitlewordtitlewordtitleword"+titleword);
			String sortword = URLEncoder.encode(sortValue, "UTF-8");
			String pageword = URLEncoder.encode(pageNumber, "UTF-8");
			String apiURL = Constants.Kakao_URL_INFO + "query=" + titleword + Constants.Kakao_URL_CONDITION_1 + sortword + Constants.Kakao_URL_CONDITION_2 + pageword;

			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", Constants.Kakao_CLIENT_SECRET);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 200) { // 정상 호출
                System.out.println("Success KakaoApi connect");
            } else { // 에러 발생
            	System.out.println("Fail KakaoApi connect");
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
			System.out.println("Request Fail KakaoApi: " + e.getMessage());
			System.out.println("***************************************************");
			System.out.println("****************Reuqest To NaverApi****************");
			System.out.println("***************************************************");
			
			
		}

		return jsonObject;
	}

}
