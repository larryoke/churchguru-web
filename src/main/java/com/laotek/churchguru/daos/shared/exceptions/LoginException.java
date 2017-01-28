package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;

public class LoginException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public LoginException(){}
	
	public LoginException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
