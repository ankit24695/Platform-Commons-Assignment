package com.student.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.student.model.AddressType;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddressDTO {
    
	@NotNull
	private String area;
	@NotNull
	private String state;
	@NotNull
	private String district;
	@NotNull
	private String pincode;
	@NotNull
	private AddressType addressType;
}
