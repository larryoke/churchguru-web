package com.laotek.churchguru.web.clientm.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DispatchMobileServiceAsync {
	void execute(String sessionId, Action<?> action, AsyncCallback<Result> callback );
}
