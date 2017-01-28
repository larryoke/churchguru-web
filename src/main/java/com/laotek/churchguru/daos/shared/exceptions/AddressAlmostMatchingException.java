package com.laotek.churchguru.daos.shared.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressAlmostMatchingException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Long> almostMatchingAddress = new ArrayList<Long>();
		
	public AddressAlmostMatchingException() {}
	public AddressAlmostMatchingException(List<Long> almostMatchingAddress) {
		this.almostMatchingAddress = almostMatchingAddress;
	}
	
	public List<Long> getAlmostMatchingAddress() {
		return almostMatchingAddress;
	}
		
}
