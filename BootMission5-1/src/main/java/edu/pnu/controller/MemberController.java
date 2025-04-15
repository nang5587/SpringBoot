package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService service;
	
	@GetMapping("/members")
	public List<MemberDTO> getMembers(){
		return service.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberDTO getMember(@PathVariable Integer id) {
		System.out.println(id + "찾음");
		return service.getMember(id);
	}
}
