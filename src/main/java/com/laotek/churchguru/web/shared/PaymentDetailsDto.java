package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PaymentDetailsDto implements IsSerializable {
    private String paymentId;
    private String approvalUrl;

    public PaymentDetailsDto() {
    }

    public String getPaymentId() {
	return paymentId;
    }

    public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
    }

    public String getApprovalUrl() {
	return approvalUrl;
    }

    public void setApprovalUrl(String approvalUrl) {
	this.approvalUrl = approvalUrl;
    }
}
