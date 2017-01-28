package com.laotek.churchguru.web.clientm.activity.home;

import net.customware.gwt.dispatch.shared.Result;

public class SubmitDonationDetailsResult implements Result {
    public SubmitDonationDetailsResult(String paypalApprovalUrl) {
	this.paypalApprovalUrl = paypalApprovalUrl;
    }

    public SubmitDonationDetailsResult() {
    }

    private String paypalApprovalUrl;

    public String getPaypalApprovalUrl() {
	return paypalApprovalUrl;
    }

    public void setPaypalApprovalUrl(String paypalApprovalUrl) {
	this.paypalApprovalUrl = paypalApprovalUrl;
    }
}
