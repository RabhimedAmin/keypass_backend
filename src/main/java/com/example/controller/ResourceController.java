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

import com.example.Service.AccessAccountService;
import com.example.Service.MemberService;
import com.example.Service.ResourceService;
import com.example.Service.TeamService;
import com.example.business.AccountDto;
import com.example.business.ResourceDto;
import com.example.model.AccessAccount;
import com.example.model.Member;
import com.example.model.Resource;
import com.example.model.Team;

@RestController("/resources")
@RequestMapping("/resources")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private AccessAccountService accessAccountService;

	@Autowired
	private MemberService memberService;
	@Autowired
	private TeamService teamService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getRessourceById(@PathVariable("id") final Long ressourceId) {
		Resource searchedressource = resourceService.getRessource(ressourceId);
		return new ResponseEntity<>(searchedressource, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Resource>> getAllressources() {
		List<Resource> searchedressource = resourceService.getAllRessources();
		return new ResponseEntity<>(searchedressource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String creatressource(@RequestBody ResourceDto ressourceDto) {
		Resource resource = resourceService.createRessource(ressourceDto.toModel());
		return "redirect:/resources/resource/" + resource.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable("id") Resource model, @RequestBody ResourceDto ressource) {
		Assert.notNull(model, "Ressource introuvable");
		model.setDescription(ressource.getDescription());
		model.setExpirationDate(ressource.getExpirationDate());
		model.setName(ressource.getName());
		model.setCreationDate(ressource.getCreationDate());
		model.setModificationDate(ressource.getModificationDate());
		model.setHost(ressource.getHost());
		model.setPort(ressource.getPort());
		model.setProtocol(ressource.getProtocol());
		model.setType(ressource.getType());

		resourceService.modifyRessource(model);
		return "redirect:/resources/resource/" + model.getId();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		resourceService.deleteRessource(id);
		return "redirect:/resources";
	}

	@RequestMapping(value = "/{id}/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<AccessAccount>> getAllAccountsByRessoourceId(@PathVariable("id") Resource r) {
		System.err.println(r.getAccountsRessource());
		List<AccessAccount> searchedAccount = accessAccountService.getAllAccounts();
		return new ResponseEntity<>(searchedAccount, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/acess_account/",method = RequestMethod.GET)
	public ResponseEntity<List<AccessAccount>> getAllAccessAccounts() {
		List<AccessAccount> searchedressource = accessAccountService.getAllAccounts();
		return new ResponseEntity<>(searchedressource, HttpStatus.OK);
	}

	

	// @RequestMapping(value = "/{id}/accounts", method = RequestMethod.POST)
	// public String creatAccount(@PathVariable("id") Ressource resource,
	// Account account) {
	// Account myAccount = accountService.createAccount(account);
	// Assert.notNull(resource, " Creation account impossible.Resource ne peut
	// pas être null");
	// ressourceService.addAccountToRessource(resource, myAccount);
	// return "redirect:/ressources/" + resource.getId()+"/accounts";
	// }

	@RequestMapping(value = "/{idRessource}/accounts", method = RequestMethod.POST)
	public String creatAccount(@PathVariable("idRessource") Resource resource,
			@RequestBody AccessAccount accessAccount) {
		Assert.notNull(resource, " Creation account impossible.Resource ne peut pas être null");
		resourceService.addAccountToRessource(resource, accessAccount);
		return "redirect:/resources/" + resource.getId() + "/accounts";

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccessAccount> getAccountById(@PathVariable("id") Resource resource, Long Id) {
		AccessAccount searchedAccount = accessAccountService.getAccount(Id);
		return new ResponseEntity<>(searchedAccount, HttpStatus.OK);

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable("id") AccessAccount model, @RequestBody AccountDto account) {
		model.setLogin(account.getLogin());

		model.setDescription(account.getDescription());
		model.setPassword(account.getNewPassword());
		accessAccountService.modifyAccount(model);
		return "redirect:/resources/account/" + model.getId();

	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	public String deleteAccountFromRessource(Long id) {
		accessAccountService.deleteAccount(id);
		return "redirect:/resources/accounts";
	}

	@RequestMapping(value = "{idResource}/manager/{idManager}", method = POST, produces = APPLICATION_JSON_VALUE)
	public String GrantManagerAuthority(@PathVariable("idResource") Resource ressource,
			@PathVariable("idManager") Member member) {
		Assert.notNull(member, "Manager not found");
		Assert.notNull(ressource, "Resource not found");
		memberService.addResourceManager(member, ressource);
		return "redirect:Resource/" + ressource.getId() + "/manager/" + member.getId();
	}

	@RequestMapping(value = "/manager/{id_manager}/resource/{id_resource}", method = RequestMethod.DELETE)
	public String RevokeManager(@PathVariable("id_resource") Resource r, @PathVariable ("id_manager")Member member) {
		memberService.deleteResourceManager(member, r);
		;
		return "redirect:Resource/" + r.getId() + "/member/" + member.getId();
	}

	@RequestMapping(value = ("team /{id_team}/account/{id_account}"), method = RequestMethod.POST)
	public ResponseEntity<?> affecteAccountToGroupe(@PathVariable("id_team") Team team,
			@PathVariable("id_account") AccessAccount accessAccount) {
		teamService.ajouterAccount(team, accessAccount);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = ("team /{id_team}/account/{id_account}"), method = RequestMethod.DELETE)
	public ResponseEntity<?> revokeAccess(@PathVariable("id_team") Team team,
			@PathVariable("id_account") AccessAccount accessAccount) {
		teamService.removeAccess(team, accessAccount);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
