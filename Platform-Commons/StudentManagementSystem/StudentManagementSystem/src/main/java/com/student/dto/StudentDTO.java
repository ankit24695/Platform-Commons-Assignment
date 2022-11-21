package com.student.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.student.model.AddressType;

import lombok.Data;

@Data
public class StudentDTO {
    
	@NotNull
	private String studentName;
	@NotNull
	@Pattern(regexp = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$", message = "Enter date of birth in yyyy-mm-dd format")
	private String dateOfBirth;
	@NotNull
	@Email(message = "Enter the correct mail")
	private String email;
	@NotNull
	private String mobileNumber;
	@NotNull
	private String area;
	@NotNull
	private String state;
	@NotNull
	private String district;
	@NotNull
	private String pincode;
	private AddressType addressType;
	
	
	
	
}
