package board.board.service;

import java.util.List;

import board.board.dto.RankDto;


public interface SearchService {
	
	public List<RankDto> selectBoardList(RankDto rank) throws Exception;
	public void saveTitleCnt(RankDto rank) throws Exception;
}
