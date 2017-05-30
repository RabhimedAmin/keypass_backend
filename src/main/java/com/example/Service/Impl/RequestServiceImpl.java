package com.example.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.Service.RequestService;
import com.example.model.Member;
import com.example.model.Request;
import com.example.model.Resource;
import com.example.repository.MemberRepository;
import com.example.repository.RequestRepository;
import com.example.repository.ResourceRepository;

@Service("demandeService")
public class RequestServiceImpl implements RequestService
{
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Override
	public void deleteDemande(Long id)
	{
		// TODO Auto-generated method stub
		requestRepository.delete(id);
	}
	
	@Override
	public Request getDemande(Long id)
	{
		// TODO Auto-generated method stub
		return requestRepository.findOne(id);
	}
	
	@Override
	public List<Request> getAllDemandes()
	{
		// TODO Auto-generated method stub
		return requestRepository.findAll();
	}
	
	@Override
	public Request modifydemande(Request request)
	{
		// TODO Auto-generated method stub
		return requestRepository.save(request);
	}
	
	@Override
	public Request createdemande(Request request)
	{
		// TODO Auto-generated method stub
		return requestRepository.save(request);
	}
	
	@Override
	public Request createNewDemande(Long id_Requester, Long id_resource,
			Request model)
	{
		Member req = memberRepository.getOne(id_Requester);
		
		Resource resource = resourceRepository.getOne(id_resource);
		
		Assert.notNull(resource, "Ressource demandée ne peut pas être null");
		Assert.notNull(req,
				"Demandeur de partage de ressource ne peut pas être null");
		
		model.setRessource(resource);
		model.setRequester(req);
		
		return requestRepository.save(model);
	}
	
}