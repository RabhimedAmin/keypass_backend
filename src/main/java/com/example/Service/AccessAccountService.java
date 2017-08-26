package com.example.Service;

import java.util.List;
import java.util.Set;

import com.example.model.AccessAccount;
import com.example.model.Resource;
public interface AccessAccountService {
	
	public AccessAccount createAccount(AccessAccount accessAccount);

	public List<AccessAccount> getAllAccounts();

	public AccessAccount modifyAccount(AccessAccount accessAccount);

	AccessAccount getAccount(long id);

	void deleteAccount(long id);

	Set<AccessAccount> getResourceAccounts(Resource r);
}
