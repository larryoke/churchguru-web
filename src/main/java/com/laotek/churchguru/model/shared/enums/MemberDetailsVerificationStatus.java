package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MemberDetailsVerificationStatus implements Serializable, IsSerializable {

    REQUESTED("Verification Requested"),

    NO_CHANGES_SUBMITTED("No Changes Submitted"),

    AWAITING_APPROVAL("Awaiting Approval");

    private String desc;

    private MemberDetailsVerificationStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
