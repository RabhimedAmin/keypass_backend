/**
 * 
 */
package com.example.business;

import java.io.Serializable;

import com.example.model.AccessAccount;


import lombok.Data;

/**
 * @author user
 *
 */
@Data
public class AccountDto implements Serializable
{
	private static final long serialVersionUID = 48052757653224864L;

	private String login;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String description;
	
	public AccessAccount toModel(){
		AccessAccount model = new AccessAccount();
		model.setLogin(login);
		model.setPassword(newPassword);
		model.setDescription(description);

		return model;
	}
}
