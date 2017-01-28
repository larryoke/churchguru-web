package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum GuestType implements Serializable, IsSerializable {
    VISITING("Visiting"), JOINING("Joining");

    private String desc;

    private GuestType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static GuestType find(String desc) {
	for (GuestType type : GuestType.values()) {
	    if (type.getDesc().equals(desc)) {
		return type;
	    }
	}
	return null;
    }
}
