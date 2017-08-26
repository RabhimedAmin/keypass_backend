package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Member;

public interface  MemberRepository extends JpaRepository <Member,Long> {

	@Transactional(readOnly=true)
	Member findFirstByEmail(String login);

	@Query("Update Member m Set m.secret = :secret where m.email = :email")
	@Transactional
	@Modifying
	public void updateSecret(@Param("email") String email,@Param("secret") String secret);



}
