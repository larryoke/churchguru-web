package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ListeningMessageStatus implements Serializable, IsSerializable {
    NEW("New"),

    INCOMPLETE("Incomplete"),

    LOADING("Loading"),

    PUBLISHED("Published");

    private String desc;

    private ListeningMessageStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static ListeningMessageStatus find(String desc) {
	for (ListeningMessageStatus title : ListeningMessageStatus.values()) {
	    if (title.getDesc().equals(desc)) {
		return title;
	    }
	}
	return null;
    }
}
