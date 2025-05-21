package com.rubypaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@Component
public class UserDataInit implements ApplicationRunner {

	@Autowired
	private MemberRepository memRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		{
			Member m = new Member();
			m.setId("member");
			m.setPassword(encoder.encode("abcd"));
//			m.setPassword("abcd");
			m.setName("Member");
			m.setRole(Role.ROLE_MEMBER);
			m.setEnabled(true);
			memRepo.save(m);
		}
		{
			Member m = new Member();
			m.setId("manager");
			m.setPassword(encoder.encode("abcd"));
//			m.setPassword("abcd");
			m.setName("Manager");
			m.setRole(Role.ROLE_MANAGER);
			m.setEnabled(true);
			memRepo.save(m);
		}
		{
			Member m = new Member();
			m.setId("admin");
			m.setPassword(encoder.encode("abcd"));
//			m.setPassword("abcd");
			m.setName("Admin");
			m.setRole(Role.ROLE_ADMIN);
			m.setEnabled(true);
			memRepo.save(m);
		}
		
	}

}
