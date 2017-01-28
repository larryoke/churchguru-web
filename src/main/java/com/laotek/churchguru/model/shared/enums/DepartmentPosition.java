package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum DepartmentPosition implements Serializable, IsSerializable {
    MEMBER("Member"), DEPUTY_LEADER("Deputy Leader"), LEADER("Leader");

    private String desc;

    private DepartmentPosition(String value) {
	this.desc = value;
    }

    public String getDesc() {
	return desc;
    }

}
