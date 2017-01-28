package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum FeedbackMessageType implements Serializable, IsSerializable {
    DASHBOARD("Dashboard"), MANAGE_MEMBERS("Manage Members"), MANAGE_GUESTS("Manage Guests"), MANAGE_USERS(
	    "Manage Users"), EMAIL_NEWSLETTER("Email Newsletter"), SMS_MESSAGING("SMS Messaging"), MANAGE_DEPARTMENT(
		    "Manage Departments"), CHURCHGURU_SHOP("ChurchGURU Shop"), OTHER("Other");

    private String desc;

    private FeedbackMessageType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static FeedbackMessageType find(String desc) {
	for (FeedbackMessageType status : FeedbackMessageType.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
