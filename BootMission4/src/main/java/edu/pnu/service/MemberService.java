package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	private LogDAO logDao = new LogDAO();
	
	@SuppressWarnings("unchecked")
	public List<MemberDTO> getMembers(){
		Map<String, Object> map = dao.getMembers();
		
		logDao.addLog(map);
		
		return (List<MemberDTO>)map.get("list");
	}
	
	public MemberDTO getMember(int id) {
		Map<String, Object> map = dao.getMember(id);
		logDao.addLog(map);
		
		return (MemberDTO)map.get("member");
	}
	
	public int postMember(MemberDTO member) {
		Map<String, Object> map = dao.postMember(member);
		logDao.addLog(map);
		
		return (int)map.get("num");
	}
	
	public int deleteMember(int id) {
		Map<String, Object> map = dao.deleteMember(id);
		logDao.addLog(map);
		
		return (int)map.get("num");
	}
	
	public int putMember(MemberDTO member) {
		Map<String, Object> map = dao.putMember(member);
		logDao.addLog(map);
		
		return (int)map.get("num");
	}
}
