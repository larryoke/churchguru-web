package com.laotek.churchguru.web.clientm.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public abstract class AbstractDispatchAsync implements DispatchAsync {
    private final ExceptionHandler exceptionHandler;

    public AbstractDispatchAsync(ExceptionHandler exceptionHandler) {
	this.exceptionHandler = exceptionHandler;
    }

    protected <A extends Action<R>, R extends Result> void onFailure(A action, Throwable caught,
	    final AsyncCallback<R> callback) {
	if (exceptionHandler != null && exceptionHandler.onFailure(caught) == ExceptionHandler.Status.STOP) {
	    return;
	}

	if (caught instanceof InvocationException) {
	    Dialogs.alert("Server Connection Error", "There appears to be a connection problem. Please try again later",
		    new AlertCallback() {
			@Override
			public void onButtonPressed() {
			    if (MGWT.getOsDetection().isAndroid()) {
				closeAndroidApp();
			    } else {
				closeIOSApp();
			    }
			}
		    });
	}

	callback.onFailure(caught);
    }

    protected <A extends Action<R>, R extends Result> void onSuccess(A action, R result,
	    final AsyncCallback<R> callback) {
	callback.onSuccess(result);
    }

    private native void closeAndroidApp() /*-{
					  if(typeof $wnd.androidAppProxy !== "undefined")
					  $wnd.androidAppProxy.closeApp();
					  else
					  alert("Running outside Android app");					       
					  }-*/;

    private native void closeIOSApp() /*-{
				      window.location = 'trinitychapel://somehost?closeIOS;				       
				      }-*/;

}
