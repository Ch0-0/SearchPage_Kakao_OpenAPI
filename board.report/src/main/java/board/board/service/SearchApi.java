package board.board.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@Service
public class SearchApi {
	

    public JsonObject Search(String blogsearch,String sortValue) {
    	if(blogsearch.length()==0) {
    		System.out.println("아직 입력안했어!!");
			return null;
		}
        String clientSecret = "e81a5d0a03b6e4d20e128b3f8960dbd9";
        JsonObject jsonObject = null;
        try {
            String keyword = URLEncoder.encode(blogsearch, "UTF-8");
            String apiURL = "https://dapi.kakao.com/v2/search/blog?query=" + keyword + "&sort=" + sortValue;

            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + clientSecret);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            Gson gson = new Gson();
             jsonObject = gson.fromJson(response.toString(), JsonObject.class);

            System.out.println(jsonObject);
//            JsonArray documents = jsonObject.getAsJsonArray("documents");
//
//            for (JsonElement document : documents) {
//                String title = document.getAsJsonObject().get("blogname").getAsString();
//                System.out.println(title);
//            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return jsonObject;
    }
}