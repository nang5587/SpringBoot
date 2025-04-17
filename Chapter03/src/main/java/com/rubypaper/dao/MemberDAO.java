package com.rubypaper.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rubypaper.common.JDBConnect;
import com.rubypaper.domain.MemberDTO;

@Repository
public class MemberDAO extends JDBConnect {

	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		List<MemberDTO> list = new ArrayList<>();
		
		String query = "select * from member";
		map.put("method", "getAll");
		map.put("success", false);

		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				list.add(dto);
			}
			map.put("list", list);
			map.put("success", true);
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		return map;
	}

	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		String query = "select * from member where id =?";
		map.put("method", "get");
		map.put("success", false);

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			MemberDTO dto = new MemberDTO();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				map.put("success", true);
				map.put("sqlstring", psmt.toString().split(":")[1].trim());
				map.put("member", dto);
				map.put("message", "해당 정보를 불러왔습니다.");
			}
			else {
				map.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public Map<String, Object> postMember (MemberDTO member){
		Map<String, Object> map = new HashMap<>();
		map.put("method", "post");
		map.put("success", false);
		
		String query = "INSERT INTO bootmisstion.member (pass, name) "
				+ " VALUES (?,?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			int num = psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			
			if(num > 0) {
				map.put("success", true);
				map.put("message", "해당 정보를 DB에 추가하였습니다.");
			}
			else {
				map.put("message", "해당 정보를 DB에 추가하지 못했습니다.");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	public Map<String, Object> putMember(MemberDTO member) {
		Map<String, Object> map = new HashMap<>();
		map.put("method", "put");
		map.put("success", false);

		if (getMember(member.getId()) == null) {
			map.put("message", "해당하는 ID가 없습니다.");
			return map;
		}

		List<String> setStr = new ArrayList<>();
		List<Object> obj = new ArrayList<>();

		if (member.getPass() != null) {
			setStr.add("pass = ?");
			obj.add(member.getPass());
		}
		if (member.getName() != null) {
			setStr.add("name = ?");
			obj.add(member.getName());
		}
		if (member.getRegidate() != null) {
			setStr.add("regidate = ?");
			obj.add(member.getRegidate());
		}

		if (setStr.isEmpty()) {
			map.put("message", "업데이트 할 정보가 없음");
			return map;
		}

		obj.add(member.getId());

		String setSqlStr = "";
		for (int i = 0; i < setStr.size(); i++) {
			setSqlStr += setStr.get(i);
			if (i < setStr.size() - 1) {
				setSqlStr += ", ";
			}
		}

		String query = "UPDATE member SET " + setSqlStr + " WHERE id = ?";

		try {
			psmt = con.prepareStatement(query);
			
			int index = 1;
			for (Object o : obj) {
				if (o instanceof String) {
					psmt.setString(index++, (String) o);
				} else if (o instanceof java.sql.Date) {
					psmt.setDate(index++, (java.sql.Date) o);
				} else if (o instanceof Integer) {
					psmt.setInt(index++, (Integer) o);
				}
			}

			int rows = psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());

			if (rows > 0) {
				map.put("success", true);
				map.put("message", "업데이트 성공");
			} else {
				map.put("success", false);
				map.put("message", "업데이트 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
	
	public Map<String, Object> deleteMember(Integer id){
		Map<String, Object> map = new HashMap<>();
		map.put("method", "delete");
		map.put("success", false);
		
		String query = "DELETE FROM member "
				+ "WHERE id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			int rows = psmt.executeUpdate();
			map.put("sqlstring", psmt.toString().split(":")[1].trim());
			if (rows > 0) {
				map.put("success", true);
				map.put("message", "삭제 성공");
			} else {
				map.put("success", false);
				map.put("message", "삭제 실패");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
}
