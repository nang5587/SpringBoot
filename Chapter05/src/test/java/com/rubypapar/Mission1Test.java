package com.rubypapar;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypapar.domain.Board;
import com.rubypapar.persistence.BoardRepository;

@SpringBootTest
public class Mission1Test {
	@Autowired
	private BoardRepository boardRepo;
	
//	@BeforeAll
	static void dataPrepare(
			@Autowired BoardRepository boardRepo) {
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스터 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt((long)(Math.random()*101));
			boardRepo.save(board);
		}
	}
	
	//title에 "1"이 포함되는 데이터 출력
//	@Test
	public void testFindByTitleContaining() {
		List<Board> list = boardRepo.findByTitleContaining("1");
		
		System.out.println("==== 결과 ====");
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	//title에 "1"이 포함되면서 cnt가 50보다 큰 데이터 출력
//	@Test
	public void testFindByTitleContainingAndCntGreaterThan() {
		List<Board> list = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50);
		
		System.out.println("==== 결과 ====");
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	//Cnt가 10~50 사이인 데이터를 seq 오름차순으로 출력
//	@Test
	public void testFindByCntBetweenOrderBySeqAsc() {
		List<Board> list = boardRepo.findByCntBetweenOrderBySeqAsc(10, 50);
		
		System.out.println("==== 결과 ====");
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	//title에 "10"이 포함되거나 content에 "2"가 포함되는 데이터를 seq 내림차순으로 출력
	@Test
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> list = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		
		System.out.println("==== 결과 ====");
		for(Board board : list) {
			System.out.println(board);
		}
	}
}
