package com.student.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminID;
	private String adminName;
	private String adminUserName;
	private String adminPassword;
	
	
	
	public Admin(String adminName, String adminUserName, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	
}
