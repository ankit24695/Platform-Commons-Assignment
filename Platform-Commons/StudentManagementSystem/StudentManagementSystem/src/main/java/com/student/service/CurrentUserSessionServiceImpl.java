package com.student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exception.LoginException;
import com.student.model.Admin;
import com.student.model.CurrentUserSession;
import com.student.model.Student;
import com.student.repository.AdminDao;
import com.student.repository.CurrentUserSessionDao;
import com.student.repository.StudentDao;

@Service
public class CurrentUserSessionServiceImpl implements CurrentUserSessionService{
    
	@Autowired
	private CurrentUserSessionDao cusDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private StudentDao studentDao;
	
	
	@Override
	public Admin getAdminDetails(String key) {
		 Optional<CurrentUserSession> currentUser = cusDao.findByUuid(key);
		 if(currentUser.isPresent()) {
			Integer signUpUserId = 	currentUser.get().getUserId();
			 Optional<Admin> opt = adminDao.findById(signUpUserId);
			 if(opt.isPresent()) {
				 return opt.get();
			 }
			}
		
		 return null;
	}

	@Override
	public Student getStudentDetails(String key) {
		Optional<CurrentUserSession> currentUser = cusDao.findByUuid(key);
		 if(currentUser.isPresent()) {
			Integer signUpUserId = 	currentUser.get().getUserId();
			 Optional<Student> opt = studentDao.findById(signUpUserId);
			 if(opt.isPresent()) {
				 return opt.get();
			 }
			}
		
		 return null;
	}



}
