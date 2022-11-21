package com.student.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdminLoginDTO {
    @NotNull(message = "Enter the user name")
	private String adminUserName;
    @NotNull(message = "Password cannot be left blank")
	private String adminPassword;
}
