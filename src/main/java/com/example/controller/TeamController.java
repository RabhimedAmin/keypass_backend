package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.TeamService;
import com.example.business.TeamDto;
import com.example.model.Member;
import com.example.model.Team;



@RestController("/Teams")
@RequestMapping("/teams")
public class TeamController {
	@Autowired
	private TeamService teamService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Team> getgroupeById(@PathVariable("id") final Long groupeId) {
		Team searchedgroupe = teamService.getGroupe(groupeId);
		return new ResponseEntity<>(searchedgroupe, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Team>> getAllgroupe() {
		List<Team> searchedgroupe = teamService.getAllGroupes();
		return new ResponseEntity<>(searchedgroupe, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String creategroupe(@RequestBody Team team) {
		teamService.createGroupe(team);
		return "redirect:/Team/" + team.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable ("id") Team team , @RequestBody TeamDto model) {
		team.setLibelle(model.getLibelle());
		team.setDescription(model.getDescription());
		teamService.modifyGroupe(team);
		return "redirect:/Team/" + team.getId();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		teamService.deleteGroupe(id);
		return "redirect:/Teams";
	}

	// @RequestMapping(value = ("/{id}/addAgent"), method = RequestMethod.POST)
	// public String affecteAgentToGroupe(
	// @PathVariable("id") Groupe groupe, @RequestBody List<Agent> agents)
	// {
	// groupeService.affectAgentTOGroupe(groupe, agents);
	//
	// return "redirect:/groupes";
	// }

	@RequestMapping(value = "/member/{id_member}", method = RequestMethod.DELETE)
	public String deleteAgentFromGroupe(@PathVariable("id_member") Member member) {
		teamService.supprimerAgent(member);
		return "redirect:/member/" + member.getId();
	}

	@RequestMapping(value = "team/{id_team}/member/{id_member}", method = POST, produces = APPLICATION_JSON_VALUE)
	public String ajoutAgentToGroupe(@PathVariable("id_team") Team team, @PathVariable("id_member") Member member) {
		Assert.notNull(member, "member not found");
		Assert.notNull(team, "team not found");
		teamService.memberToTeam(team, member);

		return "redirect:/Teams/" + team.getId() + "/member" + member.getId();
	}
	// @RequestMapping(value = ("/"), method = RequestMethod.POST)
	// public ResponseEntity<?> affecteAccountToGroupe(
	// @PathVariable("id") Groupe groupe,Account account
	// )
	// {
	// groupeService.ajouterAccount(groupe, account);
	//
	// return new ResponseEntity<>(HttpStatus.OK);
	// }
}