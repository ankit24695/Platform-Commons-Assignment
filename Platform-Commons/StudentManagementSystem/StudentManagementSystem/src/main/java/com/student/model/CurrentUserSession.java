package com.student.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CurrentUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currentUserId;
	private Integer userId;
	private String uuid;
	private LocalDateTime localDateTime;
	private String role;
	
	public CurrentUserSession(Integer userId, String uuid, LocalDateTime localDateTime, String role) {
		super();
		this.userId = userId;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
		this.role = role;
	}
	
	
	
	
	
	
	
}
