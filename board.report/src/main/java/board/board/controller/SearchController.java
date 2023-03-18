package board.board.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import board.board.service.SearchApi;



//스프링 MVC컨트롤러
@Controller
public class SearchController {

	@Autowired
	private SearchApi search;
	



	@RequestMapping("/")
	public ModelAndView index() throws Exception{
		ModelAndView mv = new ModelAndView("/board/com.kakaobank.test.index.html");

	return mv;
	}
	

	  @PostMapping("/Rank")
	  public @ResponseBody void method(@RequestParam("blogsearch") String blogsearch) throws Exception {
	    search.Search(blogsearch);
	  }


	
	
}
