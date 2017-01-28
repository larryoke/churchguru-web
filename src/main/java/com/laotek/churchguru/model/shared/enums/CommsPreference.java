package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum CommsPreference implements Serializable, IsSerializable {
    ANY("Any"), MOBILE("Mobile Phone"), EMAIL("Email");

    private String desc;

    private CommsPreference(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static CommsPreference find(String desc) {
	for (CommsPreference preference : CommsPreference.values()) {
	    if (preference.getDesc().equals(desc)) {
		return preference;
	    }
	}
	return null;
    }
}
