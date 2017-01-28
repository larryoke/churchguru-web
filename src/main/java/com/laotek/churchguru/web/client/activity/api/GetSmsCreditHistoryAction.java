package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.sms.HasSmsSendingAndBuying;

public class GetSmsCreditHistoryAction extends AbstractDispatchAction implements
	Action<GetSmsCreditHistoryResult>, HasSmsSendingAndBuying {

    public GetSmsCreditHistoryAction() {
    }
}
