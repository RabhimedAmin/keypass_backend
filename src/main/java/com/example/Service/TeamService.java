package com.example.Service;

import java.util.List;
import java.util.Set;

import com.example.model.AccessAccount;
import com.example.model.Member;
import com.example.model.Team;

public interface TeamService {
	public Team createGroupe(Team team);

	public Team modifyGroupe(Team team);

	void deleteGroupe(long id);

	public Team getGroupe(long id);

	public List<Team> getAllGroupes();

	// boolean affectAgentTOGroupe(Groupe groupe, List<Agent> agents);

	boolean accountAffectee(Team team, AccessAccount accessAccount);

	Set<AccessAccount> ajouterAccount(Team team, AccessAccount accessAccount);

	// void supprimerAgent(Groupe groupe, String name);

	void supprimerAgent(Member member);

	boolean memberAffected(Team team, Member member);

	boolean memberToTeam(Team team, Member member);

	void removeAccess(Team team, AccessAccount accessAccount);

	// boolean agentAffectee(Groupe groupe, String name);

}
