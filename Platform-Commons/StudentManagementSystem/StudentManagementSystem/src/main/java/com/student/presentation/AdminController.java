package com.student.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.student.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody AdminDTO adminDto){
		
		Admin newAdmin = adminService.adminRegister(adminDto);
		return new ResponseEntity<Admin>(newAdmin, HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("/registerStudent")
	public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentDTO studentDto, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException{
		
		Student newStudent = adminService.registerStudent(studentDto,key);
		
		return new ResponseEntity<Student>(newStudent, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse(@Valid @RequestBody CourseDTO courseDto, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException {
		
		Course newCourse = adminService.addCourse(courseDto, key);
		
		return new ResponseEntity<Course>(newCourse, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/addStudentToCourse/{studentId}/{courseId}")
	public ResponseEntity<String> addStudentToCourse(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException, StudentException, CourseException {
		
		String addedToCourse = adminService.addStudentToCourse(studentId, courseId, key);
		
		return new ResponseEntity<String>(addedToCourse, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/findStudentByName/{name}")
	public ResponseEntity<List<StudentsDTO>> studentsByName(@PathVariable("name") String name, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException, StudentException{
		
		List<StudentsDTO> list = adminService.findStudentByName(name, key);
		
		return new ResponseEntity<List<StudentsDTO>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/findStudentByCourse/{courseId}")
	public ResponseEntity<List<StudentsDTO>> studentsByCourse(@PathVariable("courseId") Integer courseId, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException, StudentException, CourseException {
		
		List<StudentsDTO> list = adminService.findStudentByCourse(courseId, key);
		
		return new ResponseEntity<List<StudentsDTO>>(list, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/findStudentAddress/{studentId}")
	public ResponseEntity<List<Address>> findAddressById(@PathVariable("studentId") Integer studentId, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException, StudentException{
		
		List<Address> list = adminService.findStudentAddress(studentId, key);
		
		return new ResponseEntity<List<Address>>(list, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
