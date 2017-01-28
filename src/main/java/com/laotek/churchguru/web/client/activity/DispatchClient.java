package com.laotek.churchguru.web.client.activity;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class DispatchClient extends AbstractDispatchAsync {

    private DispatchServiceAsync realService;

    private String clientSessionId;

    public DispatchClient(DispatchServiceAsync realService, String clientSessionId, ExceptionHandler exceptionHandler) {
	super(exceptionHandler);
	this.realService = realService;
	this.clientSessionId = clientSessionId;
    }

    @Override
    public <A extends Action<R>, R extends Result> void execute(final A action, final AsyncCallback<R> callback) {
	realService.execute(clientSessionId, action, new AsyncCallback<Result>() {
	    public void onFailure(Throwable caught) {
		DispatchClient.this.onFailure(action, caught, callback);
	    }

	    public void onSuccess(Result result) {
		DispatchClient.this.onSuccess(action, (R) result, callback);
	    }
	});
    }

    public String getClientSessionId() {
	return clientSessionId;
    }

    public void setClientSessionId(String clientSessionId) {
	this.clientSessionId = clientSessionId;
    }

}
