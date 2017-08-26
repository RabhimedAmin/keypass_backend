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

import org.springframework.util.Assert;

import com.example.model.utils.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "Function")
	private String function;

	@Column(name = "admin")
	private boolean isAdmin;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private ProfileAuthorities profile;

	@ManyToOne(cascade= CascadeType.DETACH)
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToMany(mappedBy = "managers", cascade= CascadeType.DETACH)
	@JsonIgnore
	private Set<Resource> resource;
	
	private String secret;

	public Member() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public Set<Resource> getResource() {
		return resource;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the profile
	 */
	public ProfileAuthorities getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(ProfileAuthorities profile) {
		this.profile = profile;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public boolean isManager() {
		Assert.notNull(profile, "Member without profile");
		return profile.getGrantedAuthority().equals(Authority.MANAGER);
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
}
