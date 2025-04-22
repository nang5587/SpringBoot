package com.rubypapar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypapar.domain.Board;
import com.rubypapar.persistence.BoardRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards(){
		List<Board> list = boardRepo.findAll();
		return list;
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard (@PathVariable Long seq) {
		Board board = boardRepo.findById(seq).get();
		return board;
	}
	
	@PostMapping("/board")
	public Board postBoard(Board board) {
		Board postBoard = new Board();
		postBoard.setTitle(board.getTitle());
		postBoard.setWriter(board.getWriter());
		postBoard.setContent(board.getContent());
		postBoard.setCreateDate(new Date());
		postBoard.setCnt(0L);
		boardRepo.save(postBoard);
		return postBoard;
	}
	
	@PutMapping("/board")
	public Board putBoard(Board board) {
		Board putBoard = getBoard(board.getSeq());
		if(board.getTitle() != null) {
			putBoard.setTitle(board.getTitle());
		}
		if(board.getContent() != null) {
			putBoard.setContent(board.getContent());
		}
		if(board.getWriter() != null) {
			putBoard.setWriter(board.getWriter());
		}
		boardRepo.save(putBoard);
		return putBoard;
	}
	
	@DeleteMapping("/board/{seq}")
	public String deleteBoard(@PathVariable Long seq) {
		boardRepo.deleteById(seq);
		return "한 개가 삭제되었당.";
	}
}
