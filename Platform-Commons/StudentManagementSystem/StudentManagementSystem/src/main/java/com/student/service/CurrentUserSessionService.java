package com.student.service;

import com.student.model.Admin;
import com.student.model.CurrentUserSession;
import com.student.model.Student;

public interface CurrentUserSessionService {

	
	public Admin getAdminDetails(String key);
	
	public Student getStudentDetails(String key);
}
