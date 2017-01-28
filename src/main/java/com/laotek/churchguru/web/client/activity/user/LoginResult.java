package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Result;

public class LoginResult implements Result{
	private String sessionId;
	
	public LoginResult(String sessionId) {
		this.sessionId = sessionId;
	}

	public LoginResult() {
	}

	public String getSessionId() {
		return sessionId;
	}
	
}
