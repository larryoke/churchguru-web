package com.laotek.churchguru.web.shared;

import java.io.Serializable;

public class InsufficientSmsCreditException extends RuntimeException implements
	Serializable {

    private static final long serialVersionUID = 1L;
    private int availableCredit;
    private int requestedCredit;
    private String message;

    public InsufficientSmsCreditException() {
    }

    public InsufficientSmsCreditException(int availableCredit,
	    int requestedCredit) {
	this.availableCredit = availableCredit;
	this.requestedCredit = requestedCredit;
    }

    public InsufficientSmsCreditException(String message) {
	this.message = message;
    }

    public int getAvailableCredit() {
	return availableCredit;
    }

    public int getRequestedCredit() {
	return requestedCredit;
    }

    public String getMessage() {
	return message;
    }
}
