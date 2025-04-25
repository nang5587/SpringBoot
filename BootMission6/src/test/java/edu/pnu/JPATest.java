package edu.pnu;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class JPATest {
	@Autowired
	private MemberRepository memberRepo;
	
//	@Test
	public void insertData() {
		for(int i=1; i<=10; i++) {
			Member member = new Member();
			
			try {
				member.setName("강나현 " + i);
				member.setPass("pass " + i);
				member.setRegidate(new Date());
				memberRepo.save(member);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
