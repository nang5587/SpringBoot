package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public List<MemberDTO> getAllMember() {
		return dao.getAllMember();
	}
	
	public MemberDTO getMember(Integer id) {
		return dao.getMember(id);
	}
	
	public int deleteMember(Integer id) {
		return dao.deleteMember(id);
	}
	
	public int postMember(MemberDTO member) {
		return dao.postMember(member);
	}
	
	public int putMember(MemberDTO member) {
		return dao.putMember(member);
	}
}
