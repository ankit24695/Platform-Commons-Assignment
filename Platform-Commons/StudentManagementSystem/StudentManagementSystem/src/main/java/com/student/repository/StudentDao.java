package com.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{

	
	public Optional<Student> findByUniqueStudentCode(Integer uniqueCode);
	
	public List<Student> findByStudentName(String studentName);
}
