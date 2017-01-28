package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;

public class OrgNotFoundException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public OrgNotFoundException(){}
	
	public OrgNotFoundException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
