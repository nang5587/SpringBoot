package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	private MemberService service = new MemberService();

	
	@GetMapping("/members")
	public List<MemberDTO> getAllMember(){
		return service.getAllMember();
	}
	
	@GetMapping("/member/{id}")
	public MemberDTO getMember(@PathVariable Integer id) {
		return service.getMember(id);
	}
	
	@DeleteMapping("/member/{id}")
	public int deleteMember(@PathVariable Integer id) {
		return service.deleteMember(id);
	}
	
	@PostMapping("/member")
	public int postMember(MemberDTO member) {
		return service.postMember(member);
	}
	
	@PutMapping("/member")
	public int putMember(MemberDTO member) {
		return service.putMember(member);
	}
}
