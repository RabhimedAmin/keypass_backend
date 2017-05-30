package com.example.Service;

import java.util.List;

import com.example.model.AccessAccount;
public interface AccessAccountService {
	
	public AccessAccount createAccount(AccessAccount accessAccount);

	public List<AccessAccount> getAllAccounts();

	public AccessAccount modifyAccount(AccessAccount accessAccount);

	AccessAccount getAccount(long id);

	void deleteAccount(long id);
}
