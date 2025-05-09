package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private LogDAO logDAO;
	
	@SuppressWarnings("unchecked")
	public List<MemberDTO> getMembers(){
		Map<String, Object> map = dao.getMembers();
		logDAO.addLog(map);
		
		return (List<MemberDTO>) map.get("list");
	}
	
	public MemberDTO getMember(Integer id) {
		Map<String, Object> map = dao.getMember(id);
		logDAO.addLog(map);
		
		return (MemberDTO) map.get("member");
	}
	
	public String postMember(MemberDTO member) {
		Map<String, Object> map = dao.postMember(member);
		logDAO.addLog(map);
		
		return (String) map.get("message");
	}
	
	public String putMember(MemberDTO member) {
		Map<String, Object> map = dao.putMember(member);
		logDAO.addLog(map);
		
		return (String) map.get("message");
	}
	
	public String deleteMember(Integer id) {
		Map<String, Object> map = dao.deleteMember(id);
		logDAO.addLog(map);
		
		return (String) map.get("message");
	}
}
