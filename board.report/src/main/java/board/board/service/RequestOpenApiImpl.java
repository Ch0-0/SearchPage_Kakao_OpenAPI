package board.board.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Service
public class RequestOpenApiImpl implements RequestOpenApi{
	

    public JsonObject Kakao_api(String blogsearch,String sortValue, String pageNumber) throws Exception {
        String clientSecret = "e81a5d0a03b6e4d20e128b3f8960dbd9";
        JsonObject jsonObject = null;
        try {
        	String titleword = URLEncoder.encode(blogsearch, "UTF-8");
        	String sortword = URLEncoder.encode(sortValue, "UTF-8");
        	String pageword = URLEncoder.encode(pageNumber, "UTF-8");
            String apiURL = "https://dapi.kakao.com/v2/search/blog?query=" + titleword + "&sort=" + sortword +"&page=" + pageword;

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

//           jsonObject = jObject.getJSONObject("post1")  
//           for (JsonElement document : documents) {
//               String title = document.getAsJsonObject().get("blogname").getAsString();
//               System.out.println(title);
//           }

//             JsonObject metaObject = jsonObject.getAsJsonObject("meta");
//             String pageableCount = metaObject.get("pageable_count").getAsString();
//             System.out.println(pageableCount);

            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return jsonObject;
    }


	@Override
	public JsonObject naver_api() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}