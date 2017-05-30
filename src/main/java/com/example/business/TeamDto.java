package com.example.business;

import java.io.Serializable;

import com.example.model.Team;

import lombok.Data;

@Data
public class TeamDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String libelle;
	
	private String description;
	
	
	
	public Team toModel(){
		Team model = new Team();
		model.setLibelle(libelle);
		
		model.setDescription(description);
		return model;
	}
}
