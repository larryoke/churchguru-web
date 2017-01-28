package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum UserAccountStatus implements Serializable, IsSerializable {
    INVITED("User invited"), DISABLED("Disabled"), ACTIVE("Active");

    private String desc;

    private UserAccountStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
