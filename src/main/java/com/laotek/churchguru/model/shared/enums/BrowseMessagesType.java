package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum BrowseMessagesType implements Serializable, IsSerializable {
    DRAFT("Draft Messages"), POSTED("Posted Messages");

    private String desc;

    private BrowseMessagesType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
