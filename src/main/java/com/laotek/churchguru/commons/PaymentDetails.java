package com.laotek.churchguru.commons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paymentDetails")
public class PaymentDetails {
    private String paymentId;
    private String approvalUrl;

    public String getPaymentId() {
	return paymentId;
    }

    @XmlElement
    public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
    }

    public String getApprovalUrl() {
	return approvalUrl;
    }

    @XmlElement
    public void setApprovalUrl(String approvalUrl) {
	this.approvalUrl = approvalUrl;
    }

}
