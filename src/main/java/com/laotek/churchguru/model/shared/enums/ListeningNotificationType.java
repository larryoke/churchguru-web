package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ListeningNotificationType implements Serializable, IsSerializable {
    NOTIFY_ONLINE_MEMBERS("notifyOnlineMembers", "Notify Online Members"),

    NOTIFY_ALL_FULL_MEMBERS("notifyFullMembers", "Notify Full Members"),

    NOTIFY_GUESTS("notifyGuests", "Notify Guests"),

    NOTIFY_NON_WORKERS("notifyNonWorkers", "Notify Non Workers"),

    NOTIFY_WORKERS("notifyWorkers", "Notify Workers");

    private String key;
    private String desc;

    private ListeningNotificationType(String key, String desc) {
	this.key = key;
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public String getKey() {
	return key;
    }

    public static ListeningNotificationType findKey(String key) {
	for (ListeningNotificationType type : ListeningNotificationType.values()) {
	    if (type.getKey().equals(key)) {
		return type;
	    }
	}
	return null;
    }

    public static ListeningNotificationType findDesc(String desc) {
	for (ListeningNotificationType type : ListeningNotificationType.values()) {
	    if (type.getDesc().equals(desc)) {
		return type;
	    }
	}
	return null;
    }
}
