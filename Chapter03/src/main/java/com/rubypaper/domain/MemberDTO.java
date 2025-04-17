package com.rubypaper.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDTO {
	private int id;
	private String pass;
	private String name;
	private Date regidate;
}
