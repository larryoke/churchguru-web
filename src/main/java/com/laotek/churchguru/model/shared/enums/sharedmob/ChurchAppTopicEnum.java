package com.laotek.churchguru.model.shared.enums.sharedmob;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ChurchAppTopicEnum implements Serializable, IsSerializable {
    HOME("home"),

    PASTORS_DESK("pastorDesk"),

    PRAYER_REQUEST("prayerRequest"),

    NOTICES_AND_EVENTS("noticesAndEvents"),

    DONATION("donation"), TWITTER("twitter"),

    FACEBOOK("facebook"), LISTEN("listen"),

    YOUTUBE("youtube"), ABOUT_US("aboutUs");

    private String key;
    private String desc;

    private ChurchAppTopicEnum(String desc) {
	this.key = desc;
    }

    public String getKey() {
	return key;
    }

    public String getDesc() {
	return desc;
    }

    public static ChurchAppTopicEnum find(String key) {
	for (ChurchAppTopicEnum title : ChurchAppTopicEnum.values()) {
	    if (title.getKey().equals(key)) {
		return title;
	    }
	}
	return null;
    }
}
