package com.rubypapar.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypapar.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String searchKeyword);
	
	List<Board> findByTitleContaining(String searchKeyword);
	
	List<Board> findByContentContaining(String searchKeyword);
	
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	List<Board> findByTitleContainingAndCntGreaterThan(String title, long num);
	
	List<Board> findByCntBetweenOrderBySeqAsc(long left, long right);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
}
