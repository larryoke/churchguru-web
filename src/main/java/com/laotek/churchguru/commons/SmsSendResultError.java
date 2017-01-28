package com.laotek.churchguru.commons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class SmsSendResultError {
    private int code;
    private String message;
    	
    public String getMessage() {
	return message;
    }
    
    @XmlElement
    public void setMessage(String message) {
	this.message = message;
    }
    public int getCode() {
	return code;
    }
    
    @XmlElement
    public void setCode(int code) {
	this.code = code;
    }
}
