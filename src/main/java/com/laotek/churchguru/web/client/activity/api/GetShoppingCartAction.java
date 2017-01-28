package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.sms.HasSmsSendingAndBuying;

public class GetShoppingCartAction extends AbstractDispatchAction implements
	Action<GetShoppingCartResult>, HasSmsSendingAndBuying {

    private int amountOfCredit;

    public GetShoppingCartAction() {
    }

    public GetShoppingCartAction(int amountOfCredit) {
	this.setAmountOfCredit(amountOfCredit);
    }

    public int getAmountOfCredit() {
	return amountOfCredit;
    }

    public void setAmountOfCredit(int amountOfCredit) {
	this.amountOfCredit = amountOfCredit;
    }

}
