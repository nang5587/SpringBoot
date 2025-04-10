package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {
	private List<MemberVO> list = new ArrayList<>();
	
	public MemberService(){
		for(int i =1; i<=10; i++) {
			list.add(MemberVO
					.builder()
					.id(i)
					.pass("pass" + i)
					.name("name" + i)
					.regidate(new Date())
					.build());
		}
	}

	public List<MemberVO> GetAllMember() {
		return list;
	}
	
	public MemberVO GetMember(int id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	public int DeleteMember(int id) {
		if(GetMember(id) != null) {
			list.remove(GetMember(id));
			return 1;
		}
		return 0;
	}
	
	public int PostMember(MemberVO member) {
		if(GetMember(member.getId()) != null) {
			return 0;
		}
		list.add(MemberVO.builder()
				.name(member.getName())
				.pass(member.getPass())
				.regidate(new Date())
				.build());
		return 1;
	}
	
	public int PutMember(MemberVO member) {
		if(GetMember(member.getId()) != null) {
			GetMember(member.getId()).setName(member.getName());
			GetMember(member.getId()).setPass(member.getPass());
			return 1;
		}
		return 0;
	}
}
