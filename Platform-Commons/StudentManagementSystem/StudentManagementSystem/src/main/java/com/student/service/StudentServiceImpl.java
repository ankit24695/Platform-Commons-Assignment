package com.student.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dto.AddressDTO;
import com.student.dto.StudentDTO;
import com.student.exception.CourseException;
import com.student.exception.LoginException;
import com.student.exception.StudentException;
import com.student.exception.UnaothorizedUserException;
import com.student.model.Address;
import com.student.model.Course;
import com.student.model.CurrentUserSession;
import com.student.model.Student;
import com.student.repository.AddressDao;
import com.student.repository.CourseDao;
import com.student.repository.CurrentUserSessionDao;
import com.student.repository.StudentDao;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private CurrentUserSessionDao cusdao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public Student updateStudent(StudentDTO student, String key) throws LoginException, StudentException, UnaothorizedUserException {

		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Student> optStudent = studentDao.findById(opt.get().getUserId());
		
		if(!optStudent.isPresent()) throw new UnaothorizedUserException("Admin is not allowed to update the student details");
		
		Student currentstudent = optStudent.get();
		currentstudent.setDateOfBirth(LocalDate.parse(student.getDateOfBirth()));
		currentstudent.setEmail(student.getEmail());
		currentstudent.setStudentName(student.getStudentName());
		currentstudent.setMobileNumber(student.getMobileNumber());
		
		
		return studentDao.save(currentstudent);
		
	}

	@Override
	public List<Course> findAllCourses(String key) throws LoginException, UnaothorizedUserException, CourseException {
		
		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent())throw new LoginException("Please login....");
		
		Optional<Student> optStudent = studentDao.findById(opt.get().getUserId());
		
		if(!optStudent.isPresent()) throw new UnaothorizedUserException("You are not a student.");
		
		List<Course> list =optStudent.get().getListOfCourse();
		
		if(list.isEmpty()) throw new CourseException("You are not enrolled in any course");
		
		return list;
	}

	@Override
	public String leaveCourse(Integer courseID, String key) throws LoginException, CourseException, UnaothorizedUserException {
		
		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Course> optCourse = courseDao.findById(courseID);
		
		if(!optCourse.isPresent()) throw new CourseException("No Course Exist with this CourseId");
		
		Optional<Student> optStudent = studentDao.findById(opt.get().getUserId());
		
		if(!optStudent.isPresent()) throw new UnaothorizedUserException("You are not authorized to remove the course");
		
		List<Course> list = optStudent.get().getListOfCourse();
		
		if(list.isEmpty()) throw new CourseException("You are not enrolled to any course");
		
		boolean flag=false;
		
		for(Course course:list) {
			if(course.getCourseId()==courseID) {
				flag=true;
				break;
			}
		}
		if(flag) {
			
			Student student=optStudent.get();
			Course course = optCourse.get();
			course.getListOfStudent().remove(student);
			courseDao.save(course);
			return "You are removed from this course";
		}
		
		
		return "You are already not enrolled to this course";
	}

	@Override
	public Address addAddress(AddressDTO addressDto, String key) throws LoginException, UnaothorizedUserException {

		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Student> optStudent = studentDao.findById(opt.get().getUserId());
		
		if(!optStudent.isPresent()) throw new UnaothorizedUserException("You are not allowed to add address");
		
		Student student = optStudent.get();
		
		Address address = new Address(addressDto.getArea(), addressDto.getState(), addressDto.getDistrict(), addressDto.getPincode(),addressDto.getAddressType());
	
	    Address newAddress = addressDao.save(address);
	
	    student.getListOfAddress().add(newAddress);
	    
	    
	    newAddress.setStudent(studentDao.save(student));
	    
	    addressDao.save(newAddress);
	    
	    return newAddress;
	}

}
