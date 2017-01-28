package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.sms.HasSmsSendingAndBuying;

public class GetApiAvailableCreditAction extends AbstractDispatchAction
	implements Action<GetApiAvailableCreditResult>, HasSmsSendingAndBuying {

    public GetApiAvailableCreditAction() {
    }
}
