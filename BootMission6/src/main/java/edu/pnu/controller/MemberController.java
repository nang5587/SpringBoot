package edu.pnu.controller;

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

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/test")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@GetMapping("/members")
	public List<Member> getAllData(){
		return service.getAllData();
	}
	
	@GetMapping("/member/{id}")
	public Member getData(@PathVariable Integer id) {
		return service.getData(id);
	}
	
	@PostMapping("/member")
	public Member postData(Member member) {
		return service.postData(member);
	}
	
	@PutMapping("/member")
	public Member putData(Member member) {
		return service.putData(member);
	}
	
	@DeleteMapping("/member/{id}")
	public Member deleteData(@PathVariable Integer id) {
		return service.deleteData(id);
	}
}
