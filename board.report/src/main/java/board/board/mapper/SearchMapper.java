package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.RankDto;

@Mapper
public interface SearchMapper {
	
	//블로그 랭크 조회
	List<RankDto> selectTitleList() throws Exception;
	
	//블로그 랭크 유무 확인 
	RankDto selectTitle(RankDto rank) throws Exception;
	
	//검색 횟수 update
	void updateTitleCount(RankDto rank) throws Exception;
	
	//블로그 제목 insert
	void insertTitle(RankDto rank) throws Exception;


	

}
