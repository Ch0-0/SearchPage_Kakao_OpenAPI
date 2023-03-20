package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.RankDto;
import board.board.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper searchMapper;

	
	/**/
	@Override
	public List<RankDto> selectBoardList(RankDto rank) throws Exception {

		/*
		 * 1. searchWord - count(*) 검색단어의 값 유효성을 확인함 2. wordCnt >0 UPDATE/ else insert
		 * 3. selectList
		 */
		List<RankDto> outList = searchMapper.selectTitleList(); // 쿼리로 select

		// saveTitleCnt(rank.getTitle());

//		for(int i=0; i<list.size(); i++) {
//		    RankDto rankDto = list.get(i);
//		    System.out.println(rankDto.toString());
//		}

		return outList;
	}

	// 검색어 순위 조회(10위까지)
	@Override
	public List<RankDto> selectBoardList() throws Exception {
		List<RankDto> outList = searchMapper.selectTitleList(); // 쿼리로 select

		return outList;
	}

	// 검색어 등록 횟수 저장
	public void saveTitleCnt(RankDto rank) throws Exception {

		int existTitle = searchMapper.selectTitle(rank).getExistTitle();

		if (existTitle > 0) {
			searchMapper.updateTitleCount(rank);
		} else {
			searchMapper.insertTitle(rank);
		}
	}

}
