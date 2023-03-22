package board.board.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class ApiCaller {
	
	@Autowired
	@Qualifier("kakaoApiClient")
	private SearchApi kakaoApiClient;
	
	@Autowired
	@Qualifier("naverApiClient")
	private SearchApi naverApiClient;
	
	@Async
	public JsonObject callApi(String apiType, Map<String, String> params) {
		JsonObject JsonObject;
		if (apiType.equals("kakao")) {
			JsonObject = kakaoApiClient.callApi(params);
		} else if (apiType.equals("naver")) {
			JsonObject = naverApiClient.callApi(params);
		} else {
			throw new IllegalArgumentException("Invalid apiType: " + apiType);
		}
		return JsonObject;
	}
}
//스프링에서는 하나의 인터페이스나 클래스에 대해 여러 개의 구현체를 등록하여 사용할 수 있습니다. 이런 경우에는 @Autowired 어노테이션만으로는 어떤 구현체를 주입받을지 스프링이 판단하지 못하므로, @Qualifier어노테이션을 사용하여 명시적으로 어떤 빈을 주입받을지 선택할 수 있습니다.