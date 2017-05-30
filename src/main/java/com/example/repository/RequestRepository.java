package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long>
{
	
}
