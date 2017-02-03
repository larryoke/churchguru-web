package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum LogoItemType implements Serializable, IsSerializable {
    CHURCH_LOGO("Church Logo"),

    CHURCH_APP_PROFILE_PIC("Church App Profile Pic"),

    MESSAGES_PHOTO("Messages"),

    GIVE_PHOTO("Give photo"),

    FACEBOOK_PHOTO("Give photo"),

    TWITTER_PHOTO("Give photo"),

    ABOUT_US_PHOTO("About Us photo"),

    LISTEN("Listen"),

    YOUTUBE("YouTube"),

    PASTORS_DESK_PHOTO("Pastor's desk photo"),

    ABOUT_PASTOR_PHOTO("About pastor photo"),

    PRAYER_REQUEST("Prayer request photo"),

    WEBSITE("Website");

    private String desc;

    private LogoItemType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static LogoItemType find(String desc) {
	for (LogoItemType gender : LogoItemType.values()) {
	    if (gender.getDesc().equals(desc)) {
		return gender;
	    }
	}
	return null;
    }
}
