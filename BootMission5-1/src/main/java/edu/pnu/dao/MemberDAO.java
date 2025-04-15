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
	private Map<String, Object> map = new HashMap<>();
	private List<MemberDTO> list = new ArrayList<>();
	
	public Map<String, Object> getMembers(){
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
			map.put("list", list);
			map.put("success", true);
			map.put("sqlstring", query.toString().split(":")[1].trim());
		}
		catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}
	
	public Map<String, Object> getMember(Integer id){
		String query = "select * from member where id =?";
		map.put("method", "post");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			MemberDTO dto = new MemberDTO();
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
			}
			System.out.println(rs);
			map.put("member", dto);
			map.put("success", true);
			map.put("sqlstring", query.toString().split(":")[1].trim());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
