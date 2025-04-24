package com.rubypapar;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypapar.domain.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@SpringBootTest
public class JPADynamicQueryTest {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "title";
		String searchKeyword = "테스터 제목 1";
		
		StringBuilder sb = new StringBuilder(
				"select b from Board b where 1=1");
		if(searchCondition.equals("title")) {
			sb.append(" and b.title like '%" + searchKeyword + "%'");
		}
		else if(searchCondition.equals("content")) {
			sb.append(" and b.content like '%" + searchKeyword + "%'");
		}
		
		sb.append(" order by b.id asc");
		
		TypedQuery<Board> query = 
				entityManager.createQuery(sb.toString(), Board.class);
		
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Board> list = query.getResultList();
		
		for(Board b : list) {
			System.out.println(b);
		}
	}
}
