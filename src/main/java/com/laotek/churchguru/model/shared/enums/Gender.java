package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Gender implements Serializable, IsSerializable {
    GIRL("Girl"), BOY("Boy");

    private String desc;

    private Gender(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static Gender find(String desc) {
	for (Gender gender : Gender.values()) {
	    if (gender.getDesc().equals(desc)) {
		return gender;
	    }
	}
	return null;
    }
}
