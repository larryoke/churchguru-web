package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum NotificationStatus implements Serializable, IsSerializable {
    RUNNING("Running"), STOPPED("Stopped");

    private String desc;

    private NotificationStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
