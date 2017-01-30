package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum EStoreNotificationType implements Serializable, IsSerializable {
    NOTIFY_ONLINE_MEMBERS("notifyOnlineMembers", "Notify Online Members"),

    NOTIFY_ALL_FULL_MEMBERS("notifyFullMembers", "Notify Full Members"),

    NOTIFY_GUESTS("notifyGuests", "Notify Guests"),

    NOTIFY_NON_WORKERS("notifyNonWorkers", "Notify Non Workers"),

    NOTIFY_WORKERS("notifyWorkers", "Notify Workers");

    private String key;
    private String desc;

    private EStoreNotificationType(String key, String desc) {
	this.key = key;
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public String getKey() {
	return key;
    }

    public static EStoreNotificationType findKey(String key) {
	for (EStoreNotificationType type : EStoreNotificationType.values()) {
	    if (type.getKey().equals(key)) {
		return type;
	    }
	}
	return null;
    }

    public static EStoreNotificationType findDesc(String desc) {
	for (EStoreNotificationType type : EStoreNotificationType.values()) {
	    if (type.getDesc().equals(desc)) {
		return type;
	    }
	}
	return null;
    }
}
