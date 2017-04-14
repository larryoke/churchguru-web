package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MediaType implements Serializable, IsSerializable {

    MP3("Audio"),

    MP4("Video");

    private String desc;

    private MediaType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MediaType find(String desc) {
	for (MediaType title : MediaType.values()) {
	    if (title.getDesc().equals(desc)) {
		return title;
	    }
	}
	return null;
    }
}
