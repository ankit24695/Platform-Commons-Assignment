package com.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

	
	public Optional<Admin> findByAdminUserName(String userName);
}
