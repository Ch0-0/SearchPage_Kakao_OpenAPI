package board.board.service;

import com.google.gson.JsonObject;

public interface RequestOpenApi {

	
	public JsonObject Kakao_api(String blogsearch,String sortValue, String pageNumber) throws Exception;
	
	public JsonObject naver_api() throws Exception;

	
}
