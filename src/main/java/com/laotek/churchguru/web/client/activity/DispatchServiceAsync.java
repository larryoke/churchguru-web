package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DispatchServiceAsync {
	void execute(String sessionId, Action<?> action, AsyncCallback<Result> callback );
}
