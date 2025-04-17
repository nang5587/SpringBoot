package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberDTO;
import com.rubypaper.service.MemberService;

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
		return service.getMember(id);
	}
	
	@PostMapping("/member")
	public String postMember(MemberDTO member) {
		return service.postMember(member);
	}
	
	@PutMapping("/member")
	public String putMember(MemberDTO member) {
		return service.putMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public String deleteMember(@PathVariable Integer id) {
		return service.deleteMember(id);
	}
}
