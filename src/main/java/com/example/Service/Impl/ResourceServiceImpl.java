package com.example.Service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Service.ResourceService;

import com.example.model.AccessAccount;
import com.example.model.Resource;

import com.example.repository.AccessAccountRepository;
import com.example.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService
{
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private AccessAccountRepository accessAccountRepository;
	
	@Autowired
	public void setRessourceRepository(ResourceRepository resourceRepository)
	{
		this.resourceRepository = resourceRepository;
	}
	
	@Override
	public Resource createRessource(Resource resource)
	{
		// TODO Auto-generated method stub
		return resourceRepository.save(resource);
	}
	
	@Override
	public List<Resource> getAllRessources()
	{
		// TODO Auto-generated method stub
		return resourceRepository.findAll();
	}
	
	@Override
	public Resource modifyRessource(Resource resource)
	{
		return resourceRepository.save(resource);
	}
	
	@Override
	public Resource getRessource(long id)
	{
		// TODO Auto-generated method stub
		return resourceRepository.findOne(id);
	}
	
	@Override
	public void deleteRessource(long id)
	{
		// TODO Auto-generated method stub
		resourceRepository.delete(id);
	}
	
	// @Override
	// public boolean testAccount(Ressource ressource, Account account)
	// {
	// if (ressource.getAccountsRessource().contains(account))
	// return true;
	// return false;
	// }
	
	@Override
	public Set<AccessAccount> addAccountToRessource(Resource resource,
			AccessAccount accessAccount)
	{
//		String pwd = accessAccount.getPassword();
//		accessAccount.setPassword(password);
		accessAccount.setResource(resource);
		accessAccountRepository.save(accessAccount);
//		resource.getAccountsRessource().add(accessAccount);
//		resourceRepository.save(resource);
		return resource.getAccountsRessource();
		
	}
}
