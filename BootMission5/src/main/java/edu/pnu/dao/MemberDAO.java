package edu.pnu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.common.JDBConnect;
import edu.pnu.domain.MemberDTO;
@Repository
public class MemberDAO extends JDBConnect {

	public Map<String, Object> getMembers(){
		Map<String, Object> map = new HashMap<>();
		List<MemberDTO> list = new ArrayList<>();
		String query = "select * from member";
		map.put("method", "getMembers");
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id")); 
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name")); 
				dto.setRegidate(rs.getDate("regidate"));
				list.add(dto);
			}
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			map.put("list", list);
			map.put("success", true);
		}
		catch(Exception e) {
			map.put("success", false);
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	public Map<String, Object> getMember(int id) {
		Map<String, Object> map = new HashMap<>();
		String query = "select * from member where id = ?" ;
		map.put("method", "getMember");
		MemberDTO dto = new MemberDTO();
		
		try {
			psmt = con.prepareStatement(query);
			
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getInt("id")); 
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name")); 
				dto.setRegidate(rs.getDate("regidate"));
				map.put("success", true);
			} else {
				map.put("success", false);
			}
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			map.put("member", dto);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
			System.out.println("id가 "+id+"인 member");
		}

		return map;
	}
	
	public Map<String, Object> postMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<>();
		String query = "INSERT INTO member (pass, name) VALUES (?,?) ";
		map.put("method", "postMember");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			map.put("num", 1);
		}
		catch(Exception e){
			map.put("num", 0);
			map.put("success", false);
			e.printStackTrace();
			System.out.println("member를 추가하였습니다.");
		}
		return map;
	}
	
	public Map<String, Object> deleteMember(int id) {
		Map<String, Object> map = new HashMap<>();
		String query = "DELETE FROM member WHERE id=?";
		map.put("method", "delete");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			if(((MemberDTO) getMember(id).get("member")).getId() != null) {
				map.put("success", true);
				map.put("num", 1);
			}
			else {
				map.put("success", false);
				map.put("num", 0);
			}
		}
		catch(Exception e) {
			map.put("success", false);
			e.printStackTrace();
			System.out.println("id가 "+id+"인 member 삭제 실패");
		}
		return map;
	}
	
	public Map<String, Object> putMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<>();
		String pass = " pass = ? ,";
		String name = " name = ? ,";
		String regidate = " regidate = ? ";
		if(member.getName() == null && member.getName() == null) {
			pass = "";
			name = "";
		}
		else if(member.getPass() == null) {
			pass = "";
		}
		else if(member.getName() == null) {
			name = "";
		}
		else if(member.getName() != null && member.getRegidate() == null) {
			name = " name = ? ";
			regidate = "";
		}
		else if(member.getPass() != null && member.getRegidate() == null) {
			pass = " pass = ? ";
			regidate = "";
		}
		else {
			regidate = "";
		}
		
		String query = "UPDATE member SET " + pass + name + regidate + " WHERE id = "+ member.getId();
		map.put("method", "putMember");
		
		try {
			psmt = con.prepareStatement(query);
			int n = 1;
			
			if(pass != "") {
				psmt.setString(n++, member.getPass());
			}
			if(name != "") {
				psmt.setString(n++, member.getName());				
			}
			if(regidate != "") {
				psmt.setDate(n++, member.getRegidate());				
			}
			psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			if(((MemberDTO) getMember(member.getId()).get("member")).getId() != null) {
				map.put("success", true);
				map.put("num", 1);
			}
			else {
				map.put("success", false);
				map.put("num", 0);
			}
		}
		catch(Exception e) {
			map.put("success", false);
			e.printStackTrace();
			System.out.println("수정 실패");
		}

		return map;
	}
	
}
