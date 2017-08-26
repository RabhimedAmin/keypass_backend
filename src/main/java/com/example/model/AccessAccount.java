package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.util.Assert;

import com.example.business.AccountDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "access_account")
public class AccessAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Login")
	private String login;

	@Column(name = "password")
	@ColumnTransformer(
			  read="AES_DECRYPT(password, 'A45Z12EGG')", 
			  write="AES_ENCRYPT(?, 'A45Z12EGG')")
	//@Convert(converter=PasswordAESCrypto.class)
	private String password;

	@ManyToMany(mappedBy = "accessAccounts", cascade= CascadeType.DETACH)
	@JsonIgnore
	private Set<Team> teams;

	private String description;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "resource_id" , nullable = false, updatable = false)
	//@JsonManagedReference
	private Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public AccessAccount() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param members
	 *            the agents to set
	 */

	public Set<Team> getTeams() {
		return teams;
	}

	/**
	 * 
	 */

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	private boolean verifPassword(String oldPassword) {
		Assert.notNull(this.password, "password ne peut pas être null");
		Assert.notNull(oldPassword, "password ne peut pas être null");
		return this.getPassword().equals(oldPassword);
	}

	public void updatePassword(AccountDto accountDto) {
		Assert.notNull(accountDto, "compte ne peut pas être null");
		Assert.isTrue(verifPassword(accountDto.getOldPassword()), "Erruer lors de la vérification du mot de passe");
		this.setPassword(accountDto.getNewPassword());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", login=" + login + ", password=" + password + ", equipeDeTravails=" + teams
				+ ", description=" + description + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((teams == null) ? 0 : teams.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessAccount other = (AccessAccount) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (teams == null) {
			if (other.teams != null)
				return false;
		} else if (!teams.equals(other.teams))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}