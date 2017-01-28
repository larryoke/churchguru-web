package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum DepartmentMemberStatus implements Serializable, IsSerializable {
    MEMBER("Member"), DEPUTY_LEADER("Deputy Leader"), LEADER("Leader");

    private String desc;

    private DepartmentMemberStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static DepartmentMemberStatus find(String desc) {
	for (DepartmentMemberStatus status : DepartmentMemberStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }

}
