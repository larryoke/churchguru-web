package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MediaMessageStatus implements Serializable, IsSerializable {
    NEW("Not loaded"),

    INCOMPLETE("Incomplete"),

    LOADING("Loading"),

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
	for (MediaMessageStatus title : MediaMessageStatus.values()) {
	    if (title.getDesc().equals(desc)) {
		return title;
	    }
	}
	return null;
    }
}
