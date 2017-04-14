package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MediaMessageStatus implements Serializable, IsSerializable {
    NEW("Not loaded"),

    INCOMPLETE("Incomplete"),

    LOADING("Loading"),

    LOADED("Loaded"),

    PUBLISHED("Published"),

    UNPUBLISHED("Unpublished");

    private String desc;

    private MediaMessageStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MediaMessageStatus find(String desc) {
	for (MediaMessageStatus status : MediaMessageStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
