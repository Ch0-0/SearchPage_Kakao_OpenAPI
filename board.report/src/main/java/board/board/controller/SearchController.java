package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.board.dto.RankDto;
import board.board.service.RequestOpenApi;
import board.board.service.SearchService;

//스프링 MVC컨트롤러
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private RequestOpenApi requestOpenApi;

	@RequestMapping("/")
	public ModelAndView index(RankDto rank) throws Exception {
		ModelAndView mv = new ModelAndView("/board/com.kakaobank.test.index.html");
		List<RankDto> list = searchService.selectBoardList(rank);
		mv.addObject("list", list); //List
		return mv;
	}
	
	@RequestMapping("/rankList")
	public @ResponseBody String rankList() throws Exception {
		List<RankDto> list = searchService.selectBoardList();
        
        String json = new Gson().toJson(list);
        
        return json;

	}
	
	@RequestMapping("/test")
	public @ResponseBody String test(@RequestParam("blogsearch") String blogsearch, @RequestParam("sort") String sortValue, @RequestParam("page") String pageNumber) throws Exception {
		
		if(blogsearch.length()==0) {
			return null;
		}
		
	    JsonObject jsonObject = requestOpenApi.Kakao_api(blogsearch,sortValue,pageNumber);

	    
	    return jsonObject.toString();
	}

	
//	@RequestMapping("/test")
//	public @ResponseBody JsonObject test(@RequestParam("blogsearch") String blogsearch, @RequestParam("sort") String sortValue) throws Exception {
//		
//	    JsonObject jsonObject = searchApi.Search("미국", "accuracy");
//	    
//	    return jsonObject;
//	}


	//검색단어 랭킹을 조회하는 서비스 호출
	@RequestMapping("/Rank")
	public @ResponseBody ModelAndView method(@RequestParam("blogsearch") String blogsearch) throws Exception {
		
		ModelAndView mv = new ModelAndView("/board/com.kakaobank.test.index.html"); 
		
		if(blogsearch.length()==0) {
			return mv;
		}

		RankDto rank = new RankDto();
		rank.setTitle(blogsearch);
		searchService.saveTitleCnt(rank);
		
//		JsonObject jsonObject = searchApi.Search("미국","accuracy");
//		String JOstr = jsonObject.toString();
//
//		// API호출
//		mv.addObject("jsonObject", jsonObject);
		
		return mv;

	}

}
