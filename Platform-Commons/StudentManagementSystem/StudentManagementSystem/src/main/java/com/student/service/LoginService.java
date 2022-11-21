package com.student.service;

import com.student.dto.AdminLoginDTO;
import com.student.dto.StudentLoginDTO;
import com.student.exception.LoginException;

public interface LoginService {

	public String adminlogInAccount(AdminLoginDTO loginData) throws LoginException;
	public String studentlogInAccount(StudentLoginDTO loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
