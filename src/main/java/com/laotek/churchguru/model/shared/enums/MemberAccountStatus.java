package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MemberAccountStatus implements Serializable, IsSerializable {
    ACTIVE("Active"), OCCASIONAL("Occasional"), OVERSEAS("Overseas"), EX_MEMBER("Ex-Member");

    private String desc;

    private MemberAccountStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MemberAccountStatus find(String desc) {
	for (MemberAccountStatus status : MemberAccountStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
