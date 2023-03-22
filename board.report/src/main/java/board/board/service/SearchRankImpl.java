package board.board.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.RankDto;
import board.board.mapper.SearchMapper;

@Service
public class SearchRankImpl implements SearchRank {

	@Autowired
	private SearchMapper searchMapper;

	

	// 검색어 순위 조회(10위까지)
	@Override
	public List<RankDto> selectBoardList() throws Exception {
		List<RankDto> outList= new ArrayList<>(); // 쿼리로 select
		try {
		    outList = searchMapper.selectTitleList(); // execute the select query
		} catch (Exception e) {
		    // handle the exception
		    throw new Exception("Error occurred while selecting the board rank list: " + e.getMessage());
		}

		return outList;
	}

	// 검색어 등록 여부 확인
	public void saveTitleCnt(RankDto rank) throws Exception {

		try {
		    int existTitle = searchMapper.selectTitle(rank).getExistTitle();

		    if (existTitle > 0) {
		        searchMapper.updateTitleCount(rank);
		    } else {
		        searchMapper.insertTitle(rank);
		    }
		} catch (Exception e) {
		    throw new Exception("Error occurred while check title existence: " + e.getMessage());
		}
		
	}

}
