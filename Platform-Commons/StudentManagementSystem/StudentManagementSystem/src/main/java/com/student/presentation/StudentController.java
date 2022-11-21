package com.student.presentation;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.AddressDTO;
import com.student.dto.StudentDTO;
import com.student.exception.CourseException;
import com.student.exception.LoginException;
import com.student.exception.StudentException;
import com.student.exception.UnaothorizedUserException;
import com.student.model.Address;
import com.student.model.Course;
import com.student.model.Student;
import com.student.service.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@PutMapping("/updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestBody StudentDTO student, @RequestParam("key") String key) throws LoginException, StudentException, UnaothorizedUserException{
		
		Student  updatedStudent = studentService.updateStudent(student, key);
		
		return new ResponseEntity<Student>(updatedStudent, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/listOfCourses")
	public ResponseEntity<List<Course>> findAllCourses(@RequestParam("key") String key) throws LoginException, UnaothorizedUserException, CourseException{
		
		List<Course> list = studentService.findAllCourses(key);
		
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/leaveCourse/{courseId}")
	public ResponseEntity<String> leaveCourse(@PathVariable("courseId") Integer courseId, @RequestParam("key") String key) throws LoginException, CourseException, UnaothorizedUserException{
		
		String message = studentService.leaveCourse(courseId, key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	
	@PostMapping("/addAddress")
	public ResponseEntity<Address> addAddress(@Valid @RequestBody AddressDTO addressDto, @RequestParam("key") String key) throws LoginException, UnaothorizedUserException{
		
		Address address = studentService.addAddress(addressDto, key);
		
		return new ResponseEntity<Address>(address, HttpStatus.ACCEPTED);
		
	}
}
