package edu.pnu.dao;


import java.util.ArrayList;
import java.util.List;

import edu.pnu.common.JDBConnect;
import edu.pnu.domain.MemberDTO;

public class MemberDAO extends JDBConnect {
	
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		
		//GetAllMember() test
//		List<MemberDTO> lst = dao.getAllMember();
//		System.out.println(lst.toString());
		
		//GetMember() test
//		MemberDTO dto = dao.getMember(1);
//		System.out.println(dto);
		
		//DeleteMember() test
//		int test = dao.deleteMember(1);
//		if(test == 1) {
//			System.out.println("id가 " + test + "인 member를 삭제함");
//		}
//		else {
//			System.out.println("삭제 실패");
//		}
		
		//PostMember() test
//		MemberDTO dto = new MemberDTO();
//		dto.setName("kang");
//		dto.setPass("passpass");
//		int test = dao.postMember(dto);
//		if(test == 1) {
//			System.out.println("member 삽입 성공");
//		}
//		else {
//			System.out.println("삽입 실패");
//		}
		
		//PutMember() test
		MemberDTO dto = new MemberDTO();
		dto.setId(2);
		dto.setName("nangnang");
		dto.setPass("pass123");
		int test = dao.putMember(dto);
		if(test == 1) {
			System.out.println("member 수정 성공");
		}
		else {
			System.out.println("삽입 실패");
		}
	}
	
	public List<MemberDTO> getAllMember() {
		List<MemberDTO> list = new ArrayList<>();
		
		String query = "select * from member" ;
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRegidate(rs.getDate("regidate"));
				list.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MemberDTO getMember(Integer id) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRegidate(rs.getDate("regidate"));
			}
			if(dto.getName() == null) {
				System.out.println("찾는 아이디가 없습니다.");
				return null;
			}
			return dto;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int deleteMember(Integer id) {
		String query = "DELETE FROM member WHERE id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			if(psmt.executeUpdate() == 0) {
				System.out.println("삭제할 아이디가 없습니다.");
				return 0;
			}
			return psmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int postMember(MemberDTO member) {
		String query = "INSERT INTO member "
				+ "(pass, name) "
				+ "VALUES (?,?) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			
			if(psmt.executeUpdate() == 0) {
				System.out.println("member를 추가할 수 없습니다.");
				return 0;
			}
			return psmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int putMember(MemberDTO member) {
		String query = "UPDATE member "
				+ "SET "
				+ "id = ?, "
				+ "pass = ?, "
				+ "name = ? "
				+ "WHERE id = " + member.getId();
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, member.getId());
			psmt.setString(2, member.getPass());
			psmt.setString(3, member.getName());
			
			if(psmt.executeUpdate() == 0) {
				System.out.println("수정할 아이디가 없습니다.");
				return 0;
			}
			return psmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
