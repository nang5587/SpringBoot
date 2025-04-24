package com.rubypapar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.rubypapar.domain.Board;
import com.rubypapar.domain.QBoard;
import com.rubypapar.persistence.DynamicBoardRepository;

import lombok.Data;

@Data
class BoardFilter{
	private String key;
	private String value;
	private Integer pageNum = 0;
	private Integer pageSize = 5;
}

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@GetMapping("/boardlist")
	public Page<Board> getBoardList(BoardFilter bf){
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(bf.getKey() != null && bf.getValue() != null && bf.getPageNum() != null) {
			bf.setPageNum(bf.getPageNum());
			builder.and(qboard.content.contains(bf.getValue()));
		}
		else if(bf.getKey() != null && bf.getValue() != null) {
			builder.and(qboard.title.contains(bf.getValue()));
		}
		else if(bf.getPageNum() != null) {
			bf.setPageNum(bf.getPageNum());
			System.out.println("pageNum: " + bf.getPageNum());
		}
		
		Pageable paging = PageRequest.of(bf.getPageNum(), bf.getPageSize());
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board);
		}
		
		return boardList;
	}
}
