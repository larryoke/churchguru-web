package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum DonationThankyouEmailSendStatus implements Serializable, IsSerializable {
    NOT_SENT("not sent"), READY_TO_BE_SENT("ready to be sent"), SENT("sent"),;

    private String label;

    private DonationThankyouEmailSendStatus(String label) {
	this.label = label;
    }

    public String getLabel() {
	return label;
    }
}
