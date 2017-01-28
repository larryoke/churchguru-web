package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;

public class PasswordResetException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public PasswordResetException(){}
	
	public PasswordResetException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
