package com.laotek.churchguru.web.client.activity.password;

import net.customware.gwt.dispatch.shared.Action;

public class PasswordResetAction implements Action<PasswordResetResult> {
	private String password;
	private String passwordIdentifier;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordIdentifier() {
		return passwordIdentifier;
	}

	public void setPasswordIdentifier(String passwordIdentifier) {
		this.passwordIdentifier = passwordIdentifier;
	}

}
