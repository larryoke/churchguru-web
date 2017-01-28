package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.sms.HasSmsSendingAndBuying;

public class PaypalExitPaymentAction extends AbstractDispatchAction implements
	Action<PaypalExitPaymentResult>, HasSmsSendingAndBuying {

    private String status;
    private String identifier;
    private String payerId;

    public PaypalExitPaymentAction() {
    }

    public PaypalExitPaymentAction(String status, String identifier,
	    String payerId) {
	this.status = status;
	this.identifier = identifier;
	this.payerId = payerId;
    }

    public String getStatus() {
	return status;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getPayerId() {
	return payerId;
    }

    public void setPayerId(String payerId) {
	this.payerId = payerId;
    }

}
