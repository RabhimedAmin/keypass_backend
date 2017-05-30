/**
 * 
 */
package com.example.business;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.model.Resource;
import com.example.model.Resource.ResourceType;

import lombok.Data;

/**
 * @author user
 *
 */
@Data
public class ResourceDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private LocalDateTime expirationDate;
	
	private int port;
	
	private String host;
	
	private String protocol;
	
	private ResourceType type;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
	
	private String name;
	
	
	

	/**
	 * 
	 */
	public ResourceDto()
	{
		super();
	}

	public Resource toModel()
	{
		Resource model = new Resource();
		model.setDescription(this.description);
		model.setExpirationDate(this.expirationDate);
		model.setModificationDate(this.modificationDate);
		model.setType(this.type);
		model.setHost(this.host);
		model.setPort(this.port);
		model.setProtocol(this.protocol);
		model.setName(this.name);
		
		return model;
	}
	
}
