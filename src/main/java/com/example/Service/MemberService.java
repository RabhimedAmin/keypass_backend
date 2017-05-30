package com.example.Service;

import java.util.List;
import java.util.Set;

import com.example.model.Member;
import com.example.model.ProfileAuthorities;
import com.example.model.Resource;

public interface MemberService
{
	
	void deleteAgent(long id);
	
	Member modifyAgent(Member member);
	
	List<Member> getAllAgents();
	
	Member getAgent(long id);
	
	Member getAgentByName(String name, String password);
	
	Member addResourceManager(Member member, Resource resource);
	
	void deleteResourceManager(Member member, Resource resource);
	
	//public Member createNewMember(Member model, Long profileAutoritiesId);

	int numberOfResources(Long id);

	Member modifyAuthoritie(Member member, ProfileAuthorities profile);

	Set<Resource> resourceManaged(Member member);

	Member createNewMember(Member model, Long profileAutoritiesId, Long teamId);





	
}
