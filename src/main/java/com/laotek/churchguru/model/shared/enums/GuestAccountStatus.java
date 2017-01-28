package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum GuestAccountStatus implements Serializable, IsSerializable {
    ACTIVE("Active"), EX_GUEST("Ex-Guest");

    private String desc;

    private GuestAccountStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static GuestAccountStatus find(String desc) {
	for (GuestAccountStatus status : GuestAccountStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
