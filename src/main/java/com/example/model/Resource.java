package com.example.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;



@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Resource
{
	public enum ResourceType {
		SERVEURAPPLICATIF, SERVEURWEB, SERVEURBD, SERVEURSMTP,PLATFORM , OTHER
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="host")
	private String host;

	@Column(name="port")
	private int port;
	
	@Column(name="protocol")
	private String protocol;
	
	private ResourceType type;

	@Column(name = "description")
	private String description;
	
	@CreatedDate
	@Column(name = "creation_date")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime creationDate;
	
	
	@LastModifiedDate
	@Column(name = "modif_date")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime modificationDate;
	
	@Column(name = "expiration_date")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime expirationDate;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "resource_id")
	//@JsonBackReference
	private Set<AccessAccount> accountsRessource;
	

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "resource_managares", 
		joinColumns = @JoinColumn(name = "ressource_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "managers_id", referencedColumnName = "id")
	)
	private Set<Member> managers ;
	
	public Resource()
	{
		super();
	}
	
	/**
	 * @return the idRessources
	 */
	public Long getId()
	{
		return id;
	}
	
	
	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate()
	{
		return creationDate;
	}
	
	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate)
	{
		this.creationDate = creationDate;
	}
	
	/**
	 * @return the modificationDate
	 */
	public LocalDateTime getModificationDate()
	{
		return modificationDate;
	}
	
	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(LocalDateTime modificationDate)
	{
		this.modificationDate = modificationDate;
	}
	
	/**
	 * @return the expirationDate
	 */
	public LocalDateTime getExpirationDate()
	{
		return expirationDate;
	}
	
	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(LocalDateTime expirationDate)
	{
		this.expirationDate = expirationDate;
	}
	
	/**
	 * @return the accountsRessource
	 */
	public Set<AccessAccount> getAccountsRessource()
	{
		return accountsRessource;
	}
	
	/**
	 * @param accountsRessource
	 *            the accountsRessource to set
	 */
//	public void setAccountsRessource(Set<AccessAccount> accountsRessource)
//	{
//		this.accountsRessource = accountsRessource;
//	}
	
	public ResourceType getType()
	{
		return type;
	}

	public void setType(ResourceType type)
	{
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * @return the managers
	 */
	public Set<Member> getManagers()
	{
		return managers;
	}

//	/**
//	 * @param managers the managers to set
//	 */
//	public void setManagers(Set<Member> managers)
//	{
//		this.managers = managers;
//	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol()
	{
		return protocol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
