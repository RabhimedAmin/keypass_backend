package com.example.business;

import java.io.Serializable;

import com.example.model.Member;

import lombok.Data;


@Data
public class MemberDto implements Serializable
{
	private static final long serialVersionUID=1L;
	
	private String name;
	
	private String function;
	
	private String password;
	
	private String email;
	
	private boolean isAdmin;
	
	private Long profileAutoritiesId;
	
	private Long teamId;
	
	public MemberDto()
	{
		super();
	}
	
	public Member toModel()
	{
		Member model = new Member();
		model.setName(this.name);
		model.setFunction(this.function);
		model.setPassword(this.password);
		model.setEmail(this.email);
		model.setIsAdmin(this.isAdmin);
		
		return model;
	}
	
	
}
