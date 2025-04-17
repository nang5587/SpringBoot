package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;


//객체를 만들어줌
@RestController
public class BoardController {
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getWriter() {
		return BoardVO.builder().seq(1).title("title").writer("테스터").build();
	}
	
}
