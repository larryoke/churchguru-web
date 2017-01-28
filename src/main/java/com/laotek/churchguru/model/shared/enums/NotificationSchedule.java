package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum NotificationSchedule implements Serializable, IsSerializable {
    DAILY("Daily"), WEELKLY("Weekly"), MONTHLY("Monthly");

    private String desc;

    private NotificationSchedule(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
