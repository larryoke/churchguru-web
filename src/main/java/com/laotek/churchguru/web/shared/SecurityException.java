package com.laotek.churchguru.web.shared;

import java.io.Serializable;

public class SecurityException extends RuntimeException implements
	Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public SecurityException() {
    }

    public SecurityException(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }
}
