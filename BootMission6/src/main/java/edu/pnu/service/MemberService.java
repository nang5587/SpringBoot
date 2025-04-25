package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public List<Member> getAllData(){
		return memberRepo.findAll();
	}
	
	public Member getData(@PathVariable Integer id) {
		Member member = memberRepo.findById(id).get();
		return member;
	}
	
	public Member postData(Member member) {
		Member postMember = new Member();
		
		postMember.setName(member.getName());
		postMember.setPass(member.getPass());
		postMember.setRegidate(new Date());
		memberRepo.save(postMember);
		
		return postMember;
	}
	
	public Member putData(Member member) {
		Member putMember = memberRepo.findById(member.getId()).get();
		
		if(member.getName() != null) {
			putMember.setName(member.getName());
		}
		if(member.getPass() != null) {
			putMember.setPass(member.getPass());
		}
		memberRepo.save(putMember);
		
		return putMember;
	}
	
	public Member deleteData(@PathVariable Integer id) {
		Member deleteMember = memberRepo.findById(id).get();
		memberRepo.deleteById(id);
		
		return deleteMember;
	}
}
