package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.MemberService;
import com.example.Service.TeamService;
import com.example.business.MemberDto;
import com.example.model.AccessAccount;
import com.example.model.Member;
import com.example.model.ProfileAuthorities;
import com.example.model.Resource;
import com.example.model.Team;

@RestController("/members")
@RequestMapping("/members")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TeamService teamService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Member> getAgentById(@PathVariable("id") final Long agentId) {
		Member searchedAgent = memberService.getAgent(agentId);
		return new ResponseEntity<>(searchedAgent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Member>> getAllAgent() {
		List<Member> searchedAgent = memberService.getAllAgents();
		return new ResponseEntity<>(searchedAgent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createAgent(@RequestBody MemberDto member) {
		Member model = memberService.createNewMember(member.toModel(), member.getProfileAutoritiesId(),
				member.getTeamId());
		return "redirect:/member/" + model.getId();
	}

	@RequestMapping(value = "/member/{id}/profile/{id_profile}/team/{id_team}", method = RequestMethod.PUT)
	public String update(@PathVariable("id") Member member, @RequestBody MemberDto model,
			@PathVariable("id_profile") ProfileAuthorities profile, @PathVariable ("id_team") Team team) {
		member.setName(model.getName());
		member.setEmail(model.getEmail());
		member.setFunction(model.getFunction());
		member.setPassword(model.getPassword());
		member.setIsAdmin(model.isAdmin());
		memberService.modifyAgent(member);
		memberService.modifyAuthoritie(member, profile);
		teamService.memberToTeam(team, member);

		return "redirect:/member/" + member.getId();
	}

	@RequestMapping(value = "/agent/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		memberService.deleteAgent(id);
		return "redirect:/members";
	}


	@RequestMapping(value = "/byemail", method = RequestMethod.GET)
	public ResponseEntity<Member> getAgentName(String email) {
		Member searchedAgent = memberService.getAgentByEmail(email);
		if(searchedAgent!=null){
			return new ResponseEntity<>(searchedAgent, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(searchedAgent, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/numberofresources/{id}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getNumberOfResources(@PathVariable("id") final Long agentId) {
		int searchedAgent = memberService.numberOfResources(agentId);
		return new ResponseEntity<>(searchedAgent, HttpStatus.OK);
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// public String update(@PathVariable("id") Member model, @RequestBody
	// MemberDto member) {
	// Assert.notNull(model, "member introuvable");
	// model.setEmail(member.getEmail());
	// model.setFunction(member.getFunction());
	// // model.SetIsAdmin(member.getIsAdmin());
	// model.setName(member.getName());
	// model.setPassword(member.getPassword());
	//
	// memberService.modifyAgent(model);
	// return "redirect:/members/member/" + model.getId();
	//
	// }

	@RequestMapping(value = "/member/{id_member}/profile/{id_profile}", method = RequestMethod.PUT)
	public ResponseEntity<?> modifyPorfile(@PathVariable("id_member") Member member,
			@PathVariable("id_profile") ProfileAuthorities profile) {
		Assert.notNull(member, "member cannot be null");
		Assert.notNull(profile, "profile cannot be null");
		memberService.modifyAuthoritie(member, profile);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
	public ResponseEntity<?> resourceManagedByMember(@PathVariable("id") Member member) {
		Set<Resource> searchedResource = memberService.resourceManaged(member);
		return new ResponseEntity<>(searchedResource, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/resources/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> teamAccounts(@PathVariable("id") Member member) {
		Set<AccessAccount> searchedResource = memberService.getTeamAccounts(member);
		return new ResponseEntity<>(searchedResource, HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}/accessedresources", method = RequestMethod.GET)
	public ResponseEntity<?> accessedResourcesByMember(@PathVariable("id") Member member) {
		List<Resource> searchedResource = memberService.AccessedResources(member);
		return new ResponseEntity<>(searchedResource, HttpStatus.OK);
	}
}