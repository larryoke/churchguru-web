package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DispatchAsync {

    <A extends Action<R>, R extends Result> void execute(A action, AsyncCallback<R> callback );

}
