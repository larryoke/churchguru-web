package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MemberInvitationStatus implements Serializable, IsSerializable {
    REQUESTED("Requested"), FIRST_RESPONSE("First Response"), SECOND_RESPONSE("Second Response"), THIRD_RESPONSE(
	    "Third Response"), FOURTH_RESPONSE("Fourth Response"), FIFTH_RESPONSE("Fifth Response"),

    APPROVED("Approved");

    private String desc;

    private MemberInvitationStatus(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
