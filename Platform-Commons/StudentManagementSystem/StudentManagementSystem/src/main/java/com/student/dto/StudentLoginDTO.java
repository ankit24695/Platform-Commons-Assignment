package com.student.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class StudentLoginDTO {

	@NotNull(message = "Enter the Student code")
	private Integer uniqueStudentCode;
	@Pattern(regexp = "^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$", message = "Enter date of birth in yyyy-mm-dd format")
	private String dateOfBirth;
}
