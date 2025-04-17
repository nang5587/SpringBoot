package com.rubypaper.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberDTO;


@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	@SuppressWarnings("unchecked")
	public List<MemberDTO> getMembers(){
		Map<String, Object> map = dao.getMembers();
		
		return (List<MemberDTO>) map.get("list");
	}
	
	public MemberDTO getMember(Integer id) {
		Map<String, Object> map = dao.getMember(id);
		
		return (MemberDTO) map.get("member");
	}
	
	public String postMember(MemberDTO member) {
		Map<String, Object> map = dao.postMember(member);
		
		return (String) map.get("message");
	}
	
	public String putMember(MemberDTO member) {
		Map<String, Object> map = dao.putMember(member);
		
		return (String) map.get("message");
	}
	
	public String deleteMember(Integer id) {
		Map<String, Object> map = dao.deleteMember(id);
		
		return (String) map.get("message");
	}
}
