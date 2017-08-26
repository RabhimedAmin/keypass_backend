package com.example.Service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Service.AccessAccountService;
import com.example.model.AccessAccount;
import com.example.model.Resource;
import com.example.repository.AccessAccountRepository;

@Service("accountService")
public class AccessAccountServiceImpl implements AccessAccountService {

	@Autowired
	public void setAccountRepository(AccessAccountRepository accessAccountRepository) {
		this.accessAccountRepository = accessAccountRepository;
	}

	@Autowired
	private AccessAccountRepository accessAccountRepository;

	@Override
	public List<AccessAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return accessAccountRepository.findAll();
	}
    @Override 
    public  Set<AccessAccount> getResourceAccounts(Resource r){
    	return r.getAccountsRessource();
    }

	@Override
	public AccessAccount modifyAccount(AccessAccount accessAccount) {
		// TODO Auto-generated method stub
		return accessAccountRepository.save(accessAccount);
	}

	@Override
	public AccessAccount getAccount(long id) {
		// TODO Auto-generated method stub
		return accessAccountRepository.findOne(id);
	}

	@Override
	
	public void deleteAccount(long id) {
		// TODO Auto-generated method stub
		accessAccountRepository.delete(id);
	}

	@Override
	public AccessAccount createAccount(AccessAccount accessAccount) {
		// TODO Auto-generated method stub
		return accessAccountRepository.save(accessAccount);
	}

}