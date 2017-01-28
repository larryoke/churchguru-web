package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum GuestInvitationType implements Serializable, IsSerializable {
    FRIEND("Friend"), BANNER("Banner"), EMAIL("Email"), FLYER("Flyer"), OTHER("Other");

    private String desc;

    private GuestInvitationType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static GuestInvitationType find(String desc) {
	for (GuestInvitationType type : GuestInvitationType.values()) {
	    if (type.getDesc().equals(desc)) {
		return type;
	    }
	}
	return null;
    }
}
