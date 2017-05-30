package com.example.model;

import java.time.LocalDateTime;
import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;



@Entity
@EntityListeners(AuditingEntityListener.class)
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
	
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "resource_id")
	@JsonIgnore
	private Set<AccessAccount> accountsRessource;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Ressource [id=" + id + ", host=" + host + ", port=" + port
				+ ", protocol=" + protocol + ", type=" + type + ", description="
				+ description + ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate + ", expirationDate="
				+ expirationDate + ", accountsRessource=" + accountsRessource
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountsRessource == null) ? 0
				: accountsRessource.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modificationDate == null) ? 0
				: modificationDate.hashCode());
		result = prime * result + port;
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (accountsRessource == null)
		{
			if (other.accountsRessource != null)
				return false;
		} else if (!accountsRessource.equals(other.accountsRessource))
			return false;
		if (creationDate == null)
		{
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expirationDate == null)
		{
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (host == null)
		{
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modificationDate == null)
		{
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (port != other.port)
			return false;
		if (protocol == null)
		{
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	
}
