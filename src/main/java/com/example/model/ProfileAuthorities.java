/**
 * 
 */
package com.example.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.model.utils.Authority;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * @author user
 *
 */
@Entity
@Table(name = "profile_authorities")
@EntityListeners(AuditingEntityListener.class)
public class ProfileAuthorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	private Authority grantedAuthority;

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

//	@OneToMany(orphanRemoval = false)
//	@JoinColumn(name = "profile_id")
//	private Set<Member> members;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
//
//	public Set<Member> getMembers() {
//		return members;
//	}
//
//	public void setMembers(Set<Member> members) {
//		this.members = members;
//	}

	/**
	 * 
	 */
	public ProfileAuthorities() {
		super();
	}

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return name;
	}

	/**
	 * @param profileName
	 *            the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.name = profileName;
	}

	/**
	 * @return the grantedAuthority
	 */
	public Authority getGrantedAuthority() {
		return grantedAuthority;
	}

	/**
	 * @param grantedAuthority
	 *            the grantedAuthority to set
	 */
	public void setGrantedAuthority(Authority grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
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
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((grantedAuthority == null) ? 0 : grantedAuthority.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProfileAuthorities other = (ProfileAuthorities) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (grantedAuthority != other.grantedAuthority)
			return false;
		if (id != other.id)
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfileAuthorities [id=" + id + ", profileName=" + name + ", grantedAuthority=" + grantedAuthority
				+ ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + "]";
	}

}
