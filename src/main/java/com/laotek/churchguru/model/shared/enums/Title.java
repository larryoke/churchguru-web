package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Title implements Serializable, IsSerializable {
    MR("Mr"), MRS("Mrs"), MISS("Miss");

    private String desc;

    private Title(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static Title find(String desc) {
	for (Title title : Title.values()) {
	    if (title.getDesc().equals(desc)) {
		return title;
	    }
	}
	return null;
    }
}
