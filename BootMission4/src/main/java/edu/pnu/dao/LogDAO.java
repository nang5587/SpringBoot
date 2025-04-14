package edu.pnu.dao;

import java.util.Map;

import edu.pnu.common.JDBConnect;

public class LogDAO extends JDBConnect {
	
	public void addLog(Map<String, Object> map) {
		String query = "INSERT INTO dblog (method, sqlstring, success) VALUES (?,?,?)" ;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, (String)map.get("method"));
			psmt.setString(2, (String)map.get("sqlstring"));
			psmt.setBoolean(3, (Boolean)map.get("success"));
			psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
