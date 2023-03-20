package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import board.board.dto.RankDto;
import board.board.service.SearchApi;
import board.board.service.SearchService;

//스프링 MVC컨트롤러
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SearchApi searchApi;

	@RequestMapping("/")
	public ModelAndView index(RankDto rank) throws Exception {
		ModelAndView mv = new ModelAndView("/board/com.kakaobank.test.index.html");
		List<RankDto> list = searchService.selectBoardList(rank);
		mv.addObject("list", list); //List
		
		return mv;
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test(@RequestParam("blogsearch") String blogsearch, @RequestParam("sort") String sortValue, @RequestParam("page") String pageNumber) throws Exception {
		
		if(blogsearch.length()==0) {
			return null;
		}
		
	    JsonObject jsonObject = searchApi.Search(blogsearch,sortValue,pageNumber);
	    
	    return jsonObject.toString();
	}

	
//	@RequestMapping("/test")
//	public @ResponseBody JsonObject test(@RequestParam("blogsearch") String blogsearch, @RequestParam("sort") String sortValue) throws Exception {
//		
//	    JsonObject jsonObject = searchApi.Search("미국", "accuracy");
//	    
//	    return jsonObject;
//	}


	@RequestMapping("/Rank")
	public @ResponseBody ModelAndView method(@RequestParam("blogsearch") String blogsearch) throws Exception {
		
		ModelAndView mv = new ModelAndView("/board/com.kakaobank.test.index.html"); 
		
		if(blogsearch.length()==0) {
			return mv;
		}
		
		//검색단어 랭킹을 조회하는 서비스 호출 (파라미터 값으로 검색명을 던져줘야됨)
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
