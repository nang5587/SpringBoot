package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	private MemberService service = new MemberService();
	
	@GetMapping("/members")
	public List<MemberVO> GetAllMember(){
		return service.GetAllMember();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO GetMember(@PathVariable Integer id) {
		return service.GetMember(id);
	}
	
	@PostMapping("/member")
	public int PostMember(MemberVO member) {
		return service.PostMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public int DeleteMember(@PathVariable Integer id) {
		return service.DeleteMember(id);
	}
	
	@PutMapping("/member")
	public int PutMember(MemberVO member) {
		return service.PutMember(member);
	}
}
