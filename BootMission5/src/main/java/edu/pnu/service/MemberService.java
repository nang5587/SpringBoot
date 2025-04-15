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
	private LogDAO logDao;
	
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
