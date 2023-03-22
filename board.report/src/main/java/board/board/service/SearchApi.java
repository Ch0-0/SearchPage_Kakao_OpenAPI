package board.board.service;

import java.util.Map;

import com.google.gson.JsonObject;

//먼저 공통 인터페이스를 정의하여 각각의 API에 대한 구현 추후 나중에 다른 API를 추가하거나 교체하는 것도 쉬워지기 떄문에
public interface SearchApi {
	JsonObject callApi(Map<String, String> params);
}
