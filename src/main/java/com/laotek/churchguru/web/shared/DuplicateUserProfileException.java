package com.laotek.churchguru.web.shared;

import java.io.Serializable;

public class DuplicateUserProfileException extends RuntimeException implements
	Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public DuplicateUserProfileException() {
    }

    public DuplicateUserProfileException(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }
}
