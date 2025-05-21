package com.rubypaper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public void save(Member member) {
		memberRepo.save(member);
	}
}
