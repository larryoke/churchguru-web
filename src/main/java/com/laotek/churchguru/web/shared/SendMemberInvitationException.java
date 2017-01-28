package com.laotek.churchguru.web.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SendMemberInvitationException extends RuntimeException implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Map<String, String> emailAddressToErrorMessageMap = new HashMap<String, String>();
	
	public SendMemberInvitationException(){}
	
	public SendMemberInvitationException(
			Map<String, String> emailAddressToErrorMessageMap) {
		this.emailAddressToErrorMessageMap = emailAddressToErrorMessageMap;
	}
	public Map<String, String> getEmailAddressToErrorMessageMap() {
		return emailAddressToErrorMessageMap;
	}
}
