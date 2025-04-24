package com.rubypapar.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rubypapar.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	@Query("select b from Board b order by b.seq desc")
	Page<Board> querTest1(Pageable paging);
}
