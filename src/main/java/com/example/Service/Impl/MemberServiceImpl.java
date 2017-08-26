package com.example.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.Service.MemberService;
import com.example.model.AccessAccount;
import com.example.model.Member;
import com.example.model.ProfileAuthorities;
import com.example.model.Request;
import com.example.model.Resource;
import com.example.model.Team;
import com.example.repository.MemberRepository;
import com.example.repository.ProfileAuthoritiesRepository;
import com.example.repository.RequestRepository;
import com.example.repository.ResourceRepository;
import com.example.repository.TeamRepository;

@Service("agentService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	private ProfileAuthoritiesRepository profileAuthoritiesRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired 
	RequestRepository requestRepository;

	

	@Override
	public void deleteAgent(long id) {
		// TODO Auto-generated method stub
		memberRepository.delete(id);
	}

	@Override
	public Member getAgent(long id) {
		// TODO Auto-generated method stub
		return memberRepository.findOne(id);

	}

	@Override
	public List<Member> getAllAgents() {
		// TODO Auto-generated method stub
		return memberRepository.findAll();
	}

	@Override
	public Member modifyAgent(Member member) {
		// TODO Auto-generated method stub
		return memberRepository.save(member);
	}

	@Override
	public Member getAgentByEmail(String email) {
	 Member m= memberRepository.findFirstByEmail(email);
	 return m;
	}

	@Override
	public Member addResourceManager(Member member, Resource resource) {

		Assert.isTrue(member.isManager(), "member(id="+member.getId()+") doesn't have manager privileges");
		if (!(resource.getManagers().contains(member))) {
			resource.getManagers().add(member);
			resourceRepository.save(resource);
			memberRepository.save(member);
		}
		return member;
	}

	@Override
	public void deleteResourceManager(Member member, Resource resource) {
		if (resource.getManagers().contains(member)) {
			resource.getManagers().remove(member);
			resourceRepository.save(resource);
			memberRepository.save(member);
		}
	}
@Override
	public Set<Resource> resourceManaged(Member member) {

		return member.getResource();

	}

	@Override
	@Transactional
	public Member createNewMember(Member model, Long profileAutoritiesId, Long teamId) {
		ProfileAuthorities pa = profileAuthoritiesRepository.findOne(profileAutoritiesId);
		Team t=teamRepository.findOne(teamId);
		Assert.notNull(pa, "profile cannot be null");
		model.setProfile(pa);
		model.setTeam(t);
		return memberRepository.save(model);

	}

	@Override
	public int numberOfResources(Long id)

	{
		return memberRepository.getOne(id).getResource().size();
	}

	@Override
	public Member modifyAuthoritie(Member member, ProfileAuthorities profile) {
		member.setProfile(profile);
		memberRepository.save(member);
		profileAuthoritiesRepository.save(profile);
		return member;

	}
	@Override
	public Set<AccessAccount> getTeamAccounts(Member member) {
		
		return member.getTeam().getAccounts();
}
	public List <Resource> accessToResource(Member m){
		List <Resource> allResources=  resourceRepository.findAll();
		List <Resource> accessedResources = new ArrayList<>();
		for (Resource r:allResources){
			for (AccessAccount acess:r.getAccountsRessource())
				if (r.getAccountsRessource().equals(acess)){
			if (m.getTeam().getAccounts().contains(r.getAccountsRessource()))
				
		accessedResources.add(r);
			
	}}
		return accessedResources;
}
	@Override
	public List <Resource> AccessedResources(Member member){
;
 String a= "Approved";
 List <Resource> accessedResources =new ArrayList<>();

 for (Request r: requestRepository.findAll())
 if ((member.getId()==r.getRequester().getId()) && r.getStatu().equals(a))
 
accessedResources.add(r.getRessource());
 return accessedResources;



}}