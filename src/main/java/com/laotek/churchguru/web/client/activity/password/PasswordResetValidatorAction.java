package com.laotek.churchguru.web.client.activity.password;

import net.customware.gwt.dispatch.shared.Action;

public class PasswordResetValidatorAction implements
		Action<PasswordResetValidatorResult> {
	private String passwordIdentifier;

	public String getPasswordIdentifier() {
		return passwordIdentifier;
	}

	public void setPasswordIdentifier(String passwordIdentifier) {
		this.passwordIdentifier = passwordIdentifier;
	}

}
