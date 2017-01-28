package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AbstractDispatchAsync implements DispatchAsync{
    private final ExceptionHandler exceptionHandler;


    public AbstractDispatchAsync( ExceptionHandler exceptionHandler ) {
    	this.exceptionHandler = exceptionHandler;
    }

    protected <A extends Action<R>, R extends Result> void onFailure( A action, Throwable caught, final AsyncCallback<R> callback ) {
    	if ( exceptionHandler != null && exceptionHandler.onFailure( caught ) == ExceptionHandler.Status.STOP ) {
    		return;
    	}
    	callback.onFailure( caught );
    }

    protected <A extends Action<R>, R extends Result> void onSuccess( A action, R result, final AsyncCallback<R> callback ) {
    	callback.onSuccess( result );
    }

}
