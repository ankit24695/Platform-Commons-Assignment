package com.student.service;

import java.util.List;

import com.student.dto.AdminDTO;
import com.student.dto.CourseDTO;
import com.student.dto.StudentDTO;
import com.student.dto.StudentsDTO;
import com.student.exception.CourseException;
import com.student.exception.LoginException;
import com.student.exception.StudentException;
import com.student.exception.UnaothorizedUserException;
import com.student.model.Address;
import com.student.model.Admin;
import com.student.model.Course;
import com.student.model.Student;

public interface AdminService {
	
	public Admin adminRegister(AdminDTO adminDto);

	public Student registerStudent(StudentDTO studentDTO, String key) throws LoginException, UnaothorizedUserException;
	
	public Course addCourse(CourseDTO courseDto, String key) throws LoginException, UnaothorizedUserException;
	
	public String addStudentToCourse(Integer studentID, Integer courseID, String key) throws LoginException, UnaothorizedUserException, StudentException , CourseException;
	
	public List<StudentsDTO> findStudentByName(String name, String key) throws LoginException, UnaothorizedUserException, StudentException;
	
	public List<StudentsDTO> findStudentByCourse(Integer courseID, String key) throws LoginException, UnaothorizedUserException, StudentException, CourseException;
	
	public List<Address> findStudentAddress(Integer studentId, String key) throws LoginException, UnaothorizedUserException, StudentException;
	
}
