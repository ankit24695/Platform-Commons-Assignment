package com.student.service;

import java.util.List;
import java.util.Set;

import com.student.dto.AddressDTO;
import com.student.dto.StudentDTO;
import com.student.exception.CourseException;
import com.student.exception.LoginException;
import com.student.exception.StudentException;
import com.student.exception.UnaothorizedUserException;
import com.student.model.Address;
import com.student.model.Course;
import com.student.model.Student;

public interface StudentService {

	
	public Student updateStudent(StudentDTO student, String key) throws LoginException,  StudentException, UnaothorizedUserException;
	
	public List<Course> findAllCourses(String key) throws LoginException, UnaothorizedUserException, CourseException;
	
	public String leaveCourse(Integer courseID, String key) throws LoginException, CourseException,  UnaothorizedUserException;
	
	public Address addAddress(AddressDTO addressDto, String key) throws LoginException, UnaothorizedUserException;
	
}
