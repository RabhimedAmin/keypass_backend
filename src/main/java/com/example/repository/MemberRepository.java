package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Member;

public interface  MemberRepository extends JpaRepository <Member,Long> {

	@Transactional(readOnly=true)
	Member findFirstByEmail(String login);



}
