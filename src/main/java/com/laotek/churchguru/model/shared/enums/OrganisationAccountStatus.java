package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum OrganisationAccountStatus implements Serializable, IsSerializable {
    NEW_SUBMISSION("New Submission"), DISABLED("Disabled"), ACTIVE("Active");

    private String desc;

    private OrganisationAccountStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
