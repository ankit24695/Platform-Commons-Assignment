package com.student.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminDTO {
     
	@NotNull
	private String adminName;
	@NotNull
	private String adminUserName;
	@NotNull
	@Size(min = 5)
	private String adminPassword;
}
