package com.student.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentsDTO {

	
	private String studentName;
	private LocalDate dateOfBirth;
	private String mobileNumber;
}
