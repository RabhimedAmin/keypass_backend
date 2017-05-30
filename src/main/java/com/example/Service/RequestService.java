package com.example.Service;

import java.util.List;

import com.example.model.Request;

public interface RequestService
{
	
	void deleteDemande(Long id);
	
	Request getDemande(Long id);
	
	List<Request> getAllDemandes();
	
	Request modifydemande(Request request);
	
	Request createdemande(Request request);

	Request createNewDemande( Long id_Approver, Long id_resource,
			Request model);
}
