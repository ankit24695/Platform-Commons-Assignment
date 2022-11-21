package com.student.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.AdminLoginDTO;
import com.student.dto.StudentLoginDTO;
import com.student.exception.LoginException;
import com.student.service.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoginController {
    
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<String> adminLogin(@Valid @RequestBody AdminLoginDTO adminLoginDto) throws LoginException{
		
		String message = loginService.adminlogInAccount(adminLoginDto);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	
	@PostMapping("/studentLogin")
	public ResponseEntity<String> studentLogin(@Valid @RequestBody StudentLoginDTO studentLoginDto) throws LoginException{
		
		String message = loginService.studentlogInAccount(studentLoginDto);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
    @DeleteMapping("/logout")
	public ResponseEntity<String> studentLogin(@RequestParam("key") String key) throws LoginException{
		
		String message = loginService.logOutFromAccount(key);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	
	
}
