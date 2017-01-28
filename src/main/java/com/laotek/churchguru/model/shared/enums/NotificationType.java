package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum NotificationType implements Serializable, IsSerializable {
    BIRTHDAY("Birthday Notifications"), DEMOGRAPHICS("Demographics Notifications");

    private String desc;

    private NotificationType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

}
