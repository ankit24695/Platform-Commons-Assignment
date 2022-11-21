package com.student.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.student.model.CurrentUserSession;
import com.student.model.Student;
import com.student.repository.AddressDao;
import com.student.repository.AdminDao;
import com.student.repository.CourseDao;
import com.student.repository.CurrentUserSessionDao;
import com.student.repository.StudentDao;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private CurrentUserSessionDao cusdao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private AddressDao addressDao;
	
	
	@Override
	public Admin adminRegister(AdminDTO adminDto) {
		
		Admin newAdmin =new Admin(adminDto.getAdminName(), adminDto.getAdminUserName(), adminDto.getAdminPassword());
		
		return adminDao.save(newAdmin);
		
	}
	
	@Override
	public Student registerStudent(StudentDTO studentDTO, String key) throws LoginException, UnaothorizedUserException {
        
		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
		
		if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to register a student");
		
		Integer randomCode =  Integer.parseInt(RandomString.getRandomNumberString());
		
		Student newStudent = new Student(studentDTO.getStudentName(), LocalDate.parse(studentDTO.getDateOfBirth()), studentDTO.getEmail(), studentDTO.getMobileNumber(), randomCode);
		
        Address address = new Address(studentDTO.getArea(), studentDTO.getState(), studentDTO.getDistrict(), studentDTO.getPincode(),studentDTO.getAddressType());
		
        Address newAddres = addressDao.save(address);
        
        newStudent.getListOfAddress().add(newAddres);
        
        Student registeredStudent =  studentDao.save(newStudent);
       
        newAddres.setStudent(registeredStudent);
        
		addressDao.save(newAddres);
        
       return registeredStudent;
	}

	@Override
	public Course addCourse(CourseDTO courseDto, String key) throws LoginException, UnaothorizedUserException {

		Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
		
		if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to add a course");
		
		Course course = new Course(courseDto.getCourseName(), courseDto.getCourseType(), courseDto.getDuration(), courseDto.getTopic());
		
		Course newCourse = courseDao.save(course);
		
		return newCourse;
	}

	@Override
	public String addStudentToCourse(Integer studentID, Integer courseID, String key)
			throws LoginException, UnaothorizedUserException, StudentException, CourseException {

      Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
		
		if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to register a student");
		
		Optional<Course> optCourse = courseDao.findById(courseID);
		
		if(!optCourse.isPresent()) throw new CourseException("No Course Exist with this CourseId");
		
		Optional<Student> optStudent = studentDao.findById(studentID);
		
		if(!optStudent.isPresent()) throw new StudentException("No Student Exist With this Id");
		
		Course course = optCourse.get();
		
		if(course.getListOfStudent().contains(optStudent.get())) {
			
			throw new StudentException("This course is already assigned to "+optStudent.get().getStudentName());
		}
		
		
		course.getListOfStudent().add(optStudent.get());
		
		courseDao.save(course);
		
		return optStudent.get().getStudentName()+" is assigned " +course.getCourseName()+" course";
	}

	@Override
	public List<StudentsDTO> findStudentByName(String name, String key)
			throws LoginException, UnaothorizedUserException, StudentException {
		
         Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
		
		if(!opt.isPresent()) throw new LoginException("Please login....");
		
		Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
		
		if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to use this service");
		
		List<Student> list = studentDao.findByStudentName(name);
		
		if(list.isEmpty()) throw new StudentException("No Student found with this name");
		
		List<StudentsDTO> listOfStudents = new ArrayList<>();
		
		for(Student student:list) {
			StudentsDTO studentsdto = new StudentsDTO(student.getStudentName(), student.getDateOfBirth(), student.getMobileNumber());
		    listOfStudents.add(studentsdto);
		}
		
		
		return listOfStudents;
	}

	@Override
	public List<StudentsDTO> findStudentByCourse(Integer courseID, String key)
			throws LoginException, UnaothorizedUserException, StudentException, CourseException {

		

	        Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
			
			if(!opt.isPresent()) throw new LoginException("Please login....");
			
			Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
			
			if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to register a student");
			
			Optional<Course> optCourse = courseDao.findById(courseID);
			
			if(!optCourse.isPresent()) throw new CourseException("No Course Exist with this CourseId");
			
			Course course = optCourse.get();
			
			List<Student> list = course.getListOfStudent();

			if(list.isEmpty()) throw new StudentException("There is no student in this course");
			
		
			List<StudentsDTO> listOfStudents = new ArrayList<>();
			
			for(Student student:list) {
				StudentsDTO studentsdto = new StudentsDTO(student.getStudentName(), student.getDateOfBirth(), student.getMobileNumber());
			    listOfStudents.add(studentsdto);
			}
			
			
			return listOfStudents;
	}

	@Override
	public List<Address> findStudentAddress(Integer studentId, String key)
			throws LoginException, UnaothorizedUserException, StudentException {
		
		    Optional<CurrentUserSession> opt = cusdao.findByUuid(key);
			
			if(!opt.isPresent()) throw new LoginException("Please login....");
			
			Optional<Admin> optadmin = adminDao.findById(opt.get().getUserId());
			
			if(!optadmin.isPresent()) throw new UnaothorizedUserException("You are not authorized to register a student");
			
			Optional<Student> optStudent = studentDao.findById(studentId);
			
			if(!optStudent.isPresent()) throw new StudentException("No Student Exist With this Id");
			
		    List<Address> list = optStudent.get().getListOfAddress();
		
		    return list;
	}

	

}
