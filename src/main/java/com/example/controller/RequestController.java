package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.RequestService;
import com.example.business.DemandeDto;
import com.example.model.Request;

@RestController("/Requests")
@RequestMapping("/Requests")
public class RequestController {
	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Request> getdemandeById(@PathVariable("id") final Long demandeId) {
		Request searcheddemande = requestService.getDemande(demandeId);
		return new ResponseEntity<>(searcheddemande, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Request>> getAlldemandes() {
		List<Request> searcheddemande = requestService.getAllDemandes();
		return new ResponseEntity<>(searcheddemande, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
	public String creatdemande(@RequestBody DemandeDto demande) {

		Request model = requestService.createNewDemande(demande.getId_Requester(), demande.getId_resource(),
				demande.toModel());
		return "redirect:/Requests/Request/" + model.getId();
	}

	@RequestMapping(value = "/demande/{id}", method = RequestMethod.PUT)
	public String update(@RequestBody Request request) {
		requestService.modifydemande(request);
		return "redirect:/Requests/Request/" + request.getId();

	}

	@RequestMapping(value = "/demande/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		requestService.deleteDemande(id);
		return "redirect:/Request";
	}
}