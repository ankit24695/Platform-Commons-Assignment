package com.student.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Student {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentID;
	private String studentName;
	private LocalDate dateOfBirth;
	private String email;
	private String mobileNumber;
	@Column(unique = true)
	private Integer uniqueStudentCode;
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private List<Address> listOfAddress=new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "listOfStudent")
	@JsonIgnore
	private List<Course> listOfCourse = new ArrayList<>();
	

	public Student(String studentName, LocalDate dateOfBirth, String email, String mobileNumber,
			Integer uniqueStudentCode) {
		super();
		this.studentName = studentName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.uniqueStudentCode = uniqueStudentCode;
	}
	
	
	
	
}
