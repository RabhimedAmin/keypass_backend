package com.example.Service;

import java.util.List;
import java.util.Set;

import com.example.model.AccessAccount;
import com.example.model.Resource;
public interface ResourceService {
	public Resource createRessource(Resource resource);

	public List<Resource> getAllRessources();

	public Resource modifyRessource(Resource resource);

	Resource getRessource(long id);

	void deleteRessource(long id);

	//boolean testAccount(Ressource ressource, Account account);

	Set<AccessAccount> addAccountToRessource(Resource resource, AccessAccount accessAccount);
}
