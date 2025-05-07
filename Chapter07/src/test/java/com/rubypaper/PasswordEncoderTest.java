package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class PasswordEncoderTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		Member member = new Member();
		member.setId("member");
		member.setPassword(encoder.encode("member123"));
		member.setName("멤버");
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		memberRepo.save(member);
		
		Member manager = new Member();
		manager.setId("manager");
		manager.setPassword(encoder.encode("manager123"));
		manager.setName("매니저");
		manager.setRole(Role.ROLE_MANAGER);
		manager.setEnabled(true);
		memberRepo.save(manager);
		
		Member admin = new Member();
		admin.setId("admin");
		admin.setPassword(encoder.encode("admin123"));
		admin.setName("대장");
		admin.setRole(Role.ROLE_ADMIN);
		admin.setEnabled(true);
		memberRepo.save(admin);
	}
}
