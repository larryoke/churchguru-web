package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum EStoreMessageStatus implements Serializable, IsSerializable {
    NEW("New"),

    INCOMPLETE("Incomplete"),

    LOADING("Loading"),

    PUBLISHED("Published");

    private String desc;

    private EStoreMessageStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static EStoreMessageStatus find(String desc) {
	for (EStoreMessageStatus title : EStoreMessageStatus.values()) {
	    if (title.getDesc().equals(desc)) {
		return title;
	    }
	}
	return null;
    }
}
