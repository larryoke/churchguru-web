package com.laotek.churchguru.web.shared;

import java.io.Serializable;

public class PaypalException extends RuntimeException implements
	Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public PaypalException() {
    }

    public PaypalException(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }
}
