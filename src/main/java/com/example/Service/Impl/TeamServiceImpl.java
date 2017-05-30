package com.example.Service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Service.TeamService;
import com.example.model.AccessAccount;
import com.example.model.Member;
import com.example.model.Team;
import com.example.repository.MemberRepository;
import com.example.repository.TeamRepository;

@Service("groupeService")
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Team createGroupe(Team team) {
		// TODO Auto-generated method stub
		return teamRepository.save(team);
	}

	@Override
	public Team modifyGroupe(Team team) {
		// TODO Auto-generated method stub
		return teamRepository.save(team);
	}

	@Override
	public boolean memberAffected(Team team, Member member) {
		return member.getTeam() != null && member.getTeam().equals(team);
	}

	@Override
	public boolean memberToTeam(Team team, Member member) {
		boolean affect = false;
		if (!memberAffected(team, member)) {
			member.setTeam(team);

			memberRepository.save(member);
			affect = true;
		}
		return affect;
	}

	@Override
	public boolean accountAffectee(Team team, AccessAccount accessAccount) {
		if (team.getAccounts().contains(accessAccount)) {
			return true;
		}

		return false;
	}

	// @Override
	// public boolean affectAgentTOGroupe(Groupe groupe, List<Agent> agents)
	// {
	// boolean affected = false;
	// if (agents != null && !agents.isEmpty())
	// for (Agent agent : agents)
	// {
	// if(agentAffectee(groupe, agent)){
	// continue;
	// }
	// groupe.getAgentList().add(agent);
	// groupeRepository.save(groupe);
	// agentRepository.save(agent);
	// affected = true;
	// }
	// return affected;
	// }
	//
	@Override
	@Transactional
	public void supprimerAgent(Member member) {
		memberRepository.saveAndFlush(member);
		member.setTeam(null);
		memberRepository.saveAndFlush(member);
	}
	//
	//// @Override
	//// public void supprimerAgent(Groupe groupe, String name)
	//// {
	////
	//// for(Agent agent:groupe.getAgentList())
	//// {if( groupe.getAgentList().contains(agent)&&
	// agent.getAgentName().equals(name))
	//// groupe.getAgentList().remove(agent);
	//// }
	//// }

	@Override
	public Team getGroupe(long id) {
		// TODO Auto-generated method stub
		return teamRepository.findOne(id);
	}

	@Override
	public List<Team> getAllGroupes() {
		// TODO Auto-generated method stub
		return teamRepository.findAll();
	}

	@Override
	public void deleteGroupe(long id) {
		// TODO Auto-generated method stub
		teamRepository.delete(id);
	}

	@Override
	public Set<AccessAccount> ajouterAccount(Team team, AccessAccount accessAccount) {
		if (!accountAffectee(team, accessAccount)) {
			team.getAccounts().add(accessAccount);
			teamRepository.save(team);
		}
		return team.getAccounts();

	}

	@Override
	public void removeAccess(Team team, AccessAccount accessAccount) {
		if (accountAffectee(team, accessAccount)) {
			team.getAccounts().remove(accessAccount);
			teamRepository.save(team);
		}
	}
}
