package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		for (int i = 1; i <= 10; i++) {
			Insert(emf, i);
		}
//		search(emf);
//		update(emf);
//		delete(emf);
		getAll(emf);
		
		emf.close();
	}

	public static void Insert(EntityManagerFactory emf, int num) {
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();
			Board board = new Board();
			board.setTitle("JPA 제목" + num);
			board.setWriter("관리자" + num);
			board.setContent("JPA 글등록잘되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			// 글등록
			em.persist(board);
			// Transaction commit
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void search(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L); // Id로 검색
			System.out.println("--->" + searchBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void update(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();
			// 수정할게시글조회
			Board board = em.find(Board.class, 1L);
			board.setTitle("검색한게시글의제목수정");
			// Transaction commit
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		// Transaction 생성
		EntityTransaction tx = em.getTransaction();

		try {
			// Transaction 시작
			tx.begin();
			// 삭제할게시글조회
			Board board = em.find(Board.class, 1L);
			em.remove(board);
			// Transaction commit
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void getAll(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
//		// Transaction 생성
//		EntityTransaction tx = em.getTransaction();
		
		try {
			// Transaction 시작
//			tx.begin();
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글등록잘되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);
			
			// Transaction commit
//			tx.commit();
			
			// 글목록조회
			String jpql = "select b from Board b order by b.seq desc";
			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
			for (Board brd : boardList) {
				System.out.println("---> " + brd.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
//			tx.rollback();
		} finally {
			em.close();
		}
	}
}