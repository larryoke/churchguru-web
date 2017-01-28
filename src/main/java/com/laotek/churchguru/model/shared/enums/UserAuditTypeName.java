package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum UserAuditTypeName implements Serializable, IsSerializable {
    CONFIRM_ACCOUNT("Confirm organisation and user accounts"), LOGIN("User Logged In"), ADD_MEMBER(
	    "Added Member"), SEND_MEMBER_INVITATION("Send Member Invitation"), ADD_GUEST("Added Guest"), ADD_USER(
		    "Added User"), ADD_USER_PROFILE("Add User Profile"), UPDATE_USER_DETAILS(
			    "Updated User Details"), UPDATE_USER_PERMISSION("Updated User Permissions"), VIEW_USER(
				    "Viewed Users"), VIEW_USER_DETAILS("Viewed User Details"), SEND_USERNAME_REMINDER(
					    "Send Username Reminder"), SEND_PASSWORD_REMINDER(
						    "Send Password Reminder"), RESET_PASSWORD("Reset Password");
    ;

    private String desc;

    private UserAuditTypeName(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

}
