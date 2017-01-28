package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MessageRecipientType implements Serializable, IsSerializable {
    MEMBER("Member"), GUEST("Guest"), USER("User");

    private String desc;

    private MessageRecipientType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MessageRecipientType find(String desc) {
	for (MessageRecipientType recipientType : MessageRecipientType.values()) {
	    if (recipientType.getDesc().equals(desc)) {
		return recipientType;
	    }
	}
	return null;
    }
}
