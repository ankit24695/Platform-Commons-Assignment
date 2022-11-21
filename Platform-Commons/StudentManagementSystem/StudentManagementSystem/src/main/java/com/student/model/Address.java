package com.student.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressID;
	private String area;
	private String state;
	private String district;
	private String pincode;
	@Enumerated(EnumType.ORDINAL)
	private AddressType addressType;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "student_ID")
	private Student student;
	
	
	public Address(String area, String state, String district, String pincode, AddressType addressType) {
		super();
		this.area = area;
		this.state = state;
		this.district = district;
		this.pincode = pincode;
		this.addressType = addressType;
	}
	
	
	
	

}
