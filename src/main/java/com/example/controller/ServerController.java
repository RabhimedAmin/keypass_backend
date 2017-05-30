//package com.example.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.Service.ServerService;
//
//import com.example.model.Server;
//
//@RestController("/Servers")
//@RequestMapping("/Servers")
//
//public class ServerController {
//
//	@Autowired
//	ServerService serverService;
//
//	@RequestMapping(value = "/server/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Server> getServerById(@PathVariable("id") final Long serverId) {
//		Server searchedServer = serverService.getServer(serverId);
//		return new ResponseEntity<>(searchedServer, HttpStatus.OK);
//	}
//
//	@RequestMapping( method = RequestMethod.GET)
//	public ResponseEntity<List<Server>> getAllServer() {
//		List<Server> searchedServers = serverService.getAllServer();
//		return new ResponseEntity<>(searchedServers, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/server/{id}/affecte", method = RequestMethod.PUT)
//	public ResponseEntity<?> affecteServerToAccounts(@PathVariable("id") Long serverId,
//			@RequestBody List<Long> accountsIds) {
//		boolean affected = serverService.affecteServerToAccounts(serverId, accountsIds);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	@RequestMapping( method = RequestMethod.POST)
//	public String createServer(Server server) {
//		serverService.createServer(server);
//		return "redirect:/server/" + server.getId();
//	}
//
//	@RequestMapping(value = "/ser/{id}", method = RequestMethod.PUT)
//	public String update(@RequestBody Server server) {
//		serverService.modifyServer(server);
//		return "redirect:/server/" + server.getId();
//
//	}
//
//	@RequestMapping(value = "/serv/{id}", method = RequestMethod.DELETE)
//	public String delete(@PathVariable long id) {
//		serverService.deleteServer(id);
//		return "redirect:/servers";
//	}
//}
