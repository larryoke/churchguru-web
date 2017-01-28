package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MaritalStatus implements Serializable, IsSerializable {
    SINGLE("Single"), MARRIED("Married"), SEPARATED("Separated"), DIVORCED("Divorced"), WIDOWED("Widowed");

    private String desc;

    private MaritalStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MaritalStatus find(String desc) {
	for (MaritalStatus status : MaritalStatus.values()) {
	    if (status.getDesc().equals(desc)) {
		return status;
	    }
	}
	return null;
    }
}
