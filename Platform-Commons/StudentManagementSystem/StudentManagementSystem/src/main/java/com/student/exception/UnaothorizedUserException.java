package com.student.exception;

public class UnaothorizedUserException extends Exception{

	public UnaothorizedUserException() {
		
	}
	
     public UnaothorizedUserException(String msg) {
		super(msg);
	}
}
