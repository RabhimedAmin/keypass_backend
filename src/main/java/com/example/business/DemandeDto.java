/**
 * 
 */
package com.example.business;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.model.Request;
import com.example.model.Request.requestStatu;

import lombok.Data;

/**
 * @author user
 *
 */
@Data
public class DemandeDto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257838457849716002L;
	
	private Long idRequester;
	

	
	//@JsonFormat(pattern="yy-mm-dd HH:mm:ss")
//	@JsonDeserialize(using= LocalDateTimeDeserializer.class)
	private LocalDateTime expiryDate;
	
	private Long idResource;
	
	private String goals;

	private requestStatu statu;

	public Request toModel()
	{
		Request model = new Request();
		model.setGoals(this.goals);
		model.setExpiryDate(this.expiryDate);
		model.setStatu(this.statu);
		return model;
	}
	
}
