package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource,Long> {

}
