package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class MemberInit {
	@Autowired
	MemberRepository memberRepo;
	PasswordEncoder encoder = new BCryptPasswordEncoder(); // 이거 자꾸 빼먹음 ㅠㅠ
	
	@Test
	public void memberInit() {
		memberRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd"))
				.enabled(true)
				.role(Role.ROLE_MEMBER)
				.build());
		memberRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.enabled(true)
				.role(Role.ROLE_MANAGER)
				.build());
		memberRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.enabled(true)
				.role(Role.ROLE_ADMIN)
				.build());
	}
}
