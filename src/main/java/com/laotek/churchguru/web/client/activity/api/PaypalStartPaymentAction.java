package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.sms.HasSmsSendingAndBuying;

public class PaypalStartPaymentAction extends AbstractDispatchAction implements
	Action<PaypalStartPaymentResult>, HasSmsSendingAndBuying {

    private int amountOfCredit;

    public PaypalStartPaymentAction() {
    }

    public PaypalStartPaymentAction(int amountOfCredit) {
	this.setAmountOfCredit(amountOfCredit);
    }

    public int getAmountOfCredit() {
	return amountOfCredit;
    }

    public void setAmountOfCredit(int amountOfCredit) {
	this.amountOfCredit = amountOfCredit;
    }

}
