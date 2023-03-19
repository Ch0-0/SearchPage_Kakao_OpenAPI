package board.board.dto;


import java.util.List;

import lombok.Data;


@Data
public class RankDto {
	
	private List<RankDto> rankList;

	private String title;

	private int existTitle;
	
	private int ranking;
	
	private int searchCount;

}