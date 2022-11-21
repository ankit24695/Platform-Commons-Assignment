package com.student.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dto.AdminLoginDTO;
import com.student.dto.StudentLoginDTO;
import com.student.exception.LoginException;
import com.student.model.Admin;
import com.student.model.CurrentUserSession;
import com.student.model.Student;
import com.student.repository.AdminDao;
import com.student.repository.CurrentUserSessionDao;
import com.student.repository.StudentDao;

@Service
public class LoginServiceImpl implements LoginService{
    
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private StudentDao studentDao;
	
	
	@Autowired
	private CurrentUserSessionDao cusDao;
	
	@Override
	public String adminlogInAccount(AdminLoginDTO loginData) throws LoginException {
		
		Optional<Admin> opt = adminDao.findByAdminUserName(loginData.getAdminUserName());
		
		if(!opt.isPresent())
		{
			throw new LoginException("No Admin Exist with this UserName");
		}
		
		Admin admin = opt.get();
		
		Optional<CurrentUserSession> currentUserOptional=cusDao.findByUserId(admin.getAdminID());
		
		if(currentUserOptional.isPresent()) {
			throw new LoginException("Admin Already logged in with this UserName");
		}
		
		if(admin.getAdminUserName().equals(loginData.getAdminUserName()) && admin.getAdminPassword().equals(loginData.getAdminPassword())) {
			String key = RandomString.getRandomNumberString();
			
			CurrentUserSession currentUserSession = new CurrentUserSession(admin.getAdminID(), key, LocalDateTime.now(), "Admin");
		    cusDao.save(currentUserSession);
		    
		    return currentUserSession.toString();
		}
		
		throw new LoginException("Invalid UserName or Password!");
		
	}

	@Override
	public String studentlogInAccount(StudentLoginDTO loginData) throws LoginException {
		
		Optional<Student> opt = studentDao.findByUniqueStudentCode(loginData.getUniqueStudentCode());
		
		if(!opt.isPresent())
		{
			throw new LoginException("No Student Exist with this student code");
		}
		
		Student student = opt.get();
		
        Optional<CurrentUserSession> currentUserOptional=cusDao.findByUserId(student.getStudentID());
		
		if(currentUserOptional.isPresent()) {
			throw new LoginException("Student Already logged in with this StudentCode");
		}
		
		if(student.getUniqueStudentCode().equals(loginData.getUniqueStudentCode()) && student.getDateOfBirth().compareTo(LocalDate.parse(loginData.getDateOfBirth()))==0) {
			String key = RandomString.getRandomNumberString();
			
			CurrentUserSession currentUserSession = new CurrentUserSession(student.getStudentID(), key, LocalDateTime.now(), "Student");
		    cusDao.save(currentUserSession);
		    
		    return currentUserSession.toString();
		}
		
		throw new LoginException("Invalid Student Code or Date of Birth!");
		
		

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
       
		Optional<CurrentUserSession> currentUserOptional= cusDao.findByUuid(key);
		
		if(!currentUserOptional.isPresent())
		{
			throw new LoginException("User has not logged in with this UserId");
		}
		
		CurrentUserSession currentUserSession =currentUserOptional.get();
		
		cusDao.delete(currentUserSession);
		
		
        return "Logged Out......";
	}

}
