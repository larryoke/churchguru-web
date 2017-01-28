package com.laotek.churchguru.web.clientm.dispatch;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class DispatchMobileClient extends AbstractDispatchAsync {

    private DispatchMobileServiceAsync realMobileService;

    private String clientSessionId;

    public DispatchMobileClient(DispatchMobileServiceAsync realService,
	    String clientSessionId, ExceptionHandler exceptionHandler) {
	super(exceptionHandler);
	this.realMobileService = realService;
	this.clientSessionId = clientSessionId;
    }

    @Override
    public <A extends Action<R>, R extends Result> void execute(final A action,
	    final AsyncCallback<R> callback) {
	realMobileService.execute(clientSessionId, action,
		new AsyncCallback<Result>() {
		    public void onFailure(Throwable caught) {
			DispatchMobileClient.this.onFailure(action, caught, callback);
		    }

		    public void onSuccess(Result result) {
			DispatchMobileClient.this.onSuccess(action, (R) result,
				callback);
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
