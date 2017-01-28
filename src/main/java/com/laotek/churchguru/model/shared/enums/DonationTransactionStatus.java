package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum DonationTransactionStatus implements Serializable, IsSerializable {
    CREATED("created"), APPROVED("approved"), COMPLETED("completed"), CANCELLED("cancelled");

    private String label;

    private DonationTransactionStatus(String label) {
	this.label = label;
    }

    public String getLabel() {
	return label;
    }
}
