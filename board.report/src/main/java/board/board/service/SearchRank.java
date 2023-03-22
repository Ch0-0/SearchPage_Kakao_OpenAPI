package board.board.service;

import java.util.List;

import board.board.dto.RankDto;


public interface SearchRank {
	
	public List<RankDto> selectBoardList() throws Exception;
	public void saveTitleCnt(RankDto rank) throws Exception;
	
	
}
