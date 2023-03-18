package board.board.service;
import org.springframework.stereotype.Service;


@Service
public class SearchApi {
	

    public void Search(String str) {
    	System.out.println("서비스까지 받았습니다"+str);
    }
}