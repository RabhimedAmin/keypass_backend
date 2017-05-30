/**
 * 
 */
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ProfileAuthorities;

/**
 * @author user
 *
 */
@Repository
public interface ProfileAuthoritiesRepository
		extends JpaRepository<ProfileAuthorities, Long>
{
	
}
