package com.student.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CourseDTO {
    
	@NotNull
	private String courseName;
	@NotNull
	private String courseType;
	@NotNull
	private String duration;
	@NotNull
	private String topic;
}

