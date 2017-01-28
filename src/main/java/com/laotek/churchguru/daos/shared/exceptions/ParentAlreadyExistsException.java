package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;

import com.laotek.churchguru.model.shared.enums.Title;

public class ParentAlreadyExistsException extends RuntimeException implements
		Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Title parentType;
	private String parentFullname;
	private String childFullname;
	
	

	public ParentAlreadyExistsException(Title parentType,
			String parentFullname, String childFullname) {
		super();
		this.parentType = parentType;
		this.parentFullname = parentFullname;
		this.childFullname = childFullname;
	}

	public ParentAlreadyExistsException() {
		super();
	}

	public Title getParentType() {
		return parentType;
	}

	public String getParentFullname() {
		return parentFullname;
	}

	public String getChildFullname() {
		return childFullname;
	}

}
