package com.laotek.churchguru.web.client.activity.user;


import net.customware.gwt.dispatch.shared.Action;

public class UserReloadAction implements Action<UserReloadResult>{

	private String sessionId;
	
	public UserReloadAction() {
	}

	public UserReloadAction(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}
	
}
