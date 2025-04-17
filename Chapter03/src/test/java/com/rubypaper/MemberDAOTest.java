package com.rubypaper;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberDTO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class MemberDAOTest {

	@Autowired
	private MemberDAO dao;
	
	@Order(1)
	@Test
	@DisplayName("Member select All 테스트")
	public void selectAllTest() {
		System.out.println("selectAllTest");
		Map<String, Object> map = dao.getMembers();
		List<MemberDTO> list = (List<MemberDTO>) map.get("list");
		
		for(MemberDTO m : list) {
			System.out.println(m);
		}
	}
	
	@Order(2)
	@Test
	@DisplayName("Member select 테스트")
	public void selectTest() {
		System.out.println("selectTest");
		Map<String, Object> map = dao.getMember(5);
		System.out.println((MemberDTO) map.get("member")); 
		System.out.println(map.get("message"));
	}
	
	@Order(3)
	@Test
	@DisplayName("Member update 테스트")
	public void updateTest() {
		System.out.println("updateTest");
		MemberDTO member = new MemberDTO();
		member.setId(5);
		member.setName("이건test");
		Map<String, Object> map = dao.putMember(member);
		System.out.println(map.get("message"));
	}
	
	@Order(4)
	@Test
	@DisplayName("Member insert 테스트")
	public void insertTest() {
		System.out.println("insertTest");
		MemberDTO member = new MemberDTO();
		member.setPass("test0417");
		member.setName("이건test");
		Map<String, Object> map = dao.postMember(member);
		System.out.println(map.get("message"));
	}

	@Order(5)
	@Test
	@DisplayName("Member insert 테스트")
	public void deleteTest() {
		System.out.println("deleteTest");
		Map<String, Object> map = dao.deleteMember(5);
		System.out.println(map.get("message"));
	}
}
