package com.rubypaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		for(int i=1; i<=20; i++) {
//			boardRepo.save(Board.builder().title("title " + i).writer("writer").content("content "+ i).build());
//		}
		Member member1 = new Member();
		member1.setId("member1");
		member1.setName("둘리");
		member1.setPassword("member111");
		member1.setRole("ROLE_USER");
		memberRepo.save(member1);
		Member member2 = new Member();
		member2.setId("member2");
		member2.setName("도우너");
		member2.setPassword("member222");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("둘리");
			board.setTitle("둘리가등록한게시글" + i);
			board.setContent("둘리가등록한게시글내용" + i);
			boardRepo.save(board);
		}
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("도우너");
			board.setTitle("도우너가등록한게시글" + i);
			board.setContent("도우너가등록한게시글내용" + i);
			boardRepo.save(board);
		}
	}
}
