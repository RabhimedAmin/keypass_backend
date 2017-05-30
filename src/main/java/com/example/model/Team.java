package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "team_id")
	private Set <Member> memberList;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Team_AccessAccount", 
		joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "access_account_id", referencedColumnName = "id")
	)
	@JsonIgnore
	private Set<AccessAccount> accessAccounts;
	
	private String libelle;
	
	private String description;
	
	/**
	 * 
	 */
	public Team()
	{
		super();
	}
	
	/**
	 * @return the groupeId
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * @return the agentList
	 */
//	@JsonIgnore
//	public List<Member> getMemberList()
//	{
//		return memberList;
//	}
	
	@JsonIgnore
	public Set<AccessAccount> getAccounts()
	{
		return accessAccounts;
	}
	
	/**
	 * @param agentList
	 *            the agentList to set
	 */
//	@JsonIgnore
//	public void setMemberList(List<Member> memberList)
//	{
//		this.memberList = memberList;
////		this.memberList.clear();
////		this.memberList.addAll(memberList);
//	}
	
	
	
	/**
	 * @return the libelle
	 */
	public String getLibelle()
	{
		return libelle;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
//		result = prime * result
//				+ ((accessAccounts == null) ? 0 : accessAccounts.hashCode());
//		result = prime * result
//				+ ((memberList == null) ? 0 : memberList.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Team other = (Team) obj;
		if (accessAccounts == null)
		{
			if (other.accessAccounts != null)
				return false;
		} else if (!accessAccounts.equals(other.accessAccounts))
			return false;
//		if (memberList == null)
//		{
//			if (other.memberList != null)
//				return false;
//		} else if (!memberList.equals(other.memberList))
//			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelle == null)
		{
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Groupe [id=" + id + ", accounts="
				+ accessAccounts + ", libelle=" + libelle + ", description="
				+ description + "]";
	}
}
