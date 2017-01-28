package com.laotek.churchguru.model.shared.enums.sharedmob;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum DonationType implements Serializable, IsSerializable {
    OFFERING("Offering"), TITHE("Tithe"), BUILDING_FUND("Building Fund"), THANKSGIVING(
	    "Thanksgiving"), SPECIAL_OFFERING("Special Offering"), OTHER("Other");

    private String desc;

    private DonationType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static DonationType find(String desc) {
	for (DonationType type : DonationType.values()) {
	    if (type.getDesc().equals(desc)) {
		return type;
	    }
	}
	return null;
    }
}
