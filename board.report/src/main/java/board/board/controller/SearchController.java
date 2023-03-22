package board.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.board.dto.RankDto;
import board.board.service.ApiCaller;
import board.board.service.SearchRank;

//스프링 MVC컨트롤러
@Controller
public class SearchController {

	@Autowired
	private SearchRank searchService;


	@Autowired
	private ApiCaller apiCaller;

	@RequestMapping("/")
	public ModelAndView index() throws Exception {
		ModelAndView mv = new ModelAndView("./board/com.kakaobank.test.index.html");
		List<RankDto> list = searchService.selectBoardList();
		mv.addObject("list", list); // List
		return mv;
	}

	@RequestMapping("/rankList")
	public @ResponseBody String rankList() throws Exception {
		List<RankDto> list = searchService.selectBoardList();

		String json = new Gson().toJson(list);

		return json;

	}

	//As-Is 확장성 고려하지 않고 kakao만 호출하도록함
	/*
	 * @RequestMapping("/requestApi") public @ResponseBody String
	 * test(@RequestParam("blogsearch") String blogsearch, @RequestParam("sort")
	 * String sortValue, @RequestParam("page") String pageNumber) throws Exception {
	 * 
	 * if(blogsearch.length()==0) { return null; }
	 * 
	 * JsonObject jsonObject =
	 * requestOpenApi.Kakao_api(blogsearch,sortValue,pageNumber);
	 * 
	 * return jsonObject.toString(); }
	 */

	// To-Be 확장성을 고려하여 다시 개발
	@RequestMapping("/requestApi")
	public @ResponseBody String test(@RequestParam("blogsearch") String blogsearch,
			@RequestParam("sort") String sortValue, @RequestParam("page") String pageNumber) throws Exception {

		if (blogsearch.length() == 0) {
			return null;
		}

		String apiType = "";
		Map<String, String> params = new HashMap<>();

		apiType = "kakao";  //추후 다른 API의 호출을 원하게 될 경우 해당 부분 변경

		params.put("blogsearch", blogsearch);
		params.put("sortValue", sortValue);
		params.put("pageNumber", pageNumber);

		JsonObject jsonObject = apiCaller.callApi(apiType, params);
		
		//실패했을 경우 네이버 호출
		if(jsonObject==null) {
			apiType = "naver";
			jsonObject = apiCaller.callApi(apiType, params);
		}
		System.out.println("jsonObjjsonObjectjsonObjectjsonObjectect"+jsonObject);
		return jsonObject.toString();
	}

	// 검색단어 랭킹을 조회하는 서비스 호출
	@RequestMapping("/Rank")
	public @ResponseBody ModelAndView method(@RequestParam("blogsearch") String blogsearch) throws Exception {

		ModelAndView mv = new ModelAndView("./board/com.kakaobank.test.index.html");

		System.out.println(blogsearch.length());
		if (blogsearch.length() == 0) {
			return mv;
		}else if(blogsearch.length()>=50) {
			blogsearch=blogsearch.substring(0, 50);
		}

		RankDto rank = new RankDto();
		rank.setTitle(blogsearch);
		searchService.saveTitleCnt(rank);

		return mv;

	}

}
