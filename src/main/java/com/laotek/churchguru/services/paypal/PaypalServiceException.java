package com.laotek.churchguru.services.paypal;

public class PaypalServiceException extends RuntimeException {
    public PaypalServiceException() {
	super();
	// TODO Auto-generated constructor stub
    }

    public PaypalServiceException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

    public PaypalServiceException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    public PaypalServiceException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

    public PaypalServiceException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;
}
