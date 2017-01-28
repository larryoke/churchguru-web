package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MessageStatus implements Serializable, IsSerializable {
    DRAFT("Draft"), SENT("Sent");

    private String desc;

    private MessageStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MessageStatus find(String desc) {
	for (MessageStatus status : MessageStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
