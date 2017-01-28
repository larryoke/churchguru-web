package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;

public abstract class ForgotDialogBox extends DialogBox {
    private DispatchServiceAsync dispatchService = GWT
	    .create(DispatchService.class);
    protected DispatchClient dispatchClient = new DispatchClient(
	    dispatchService, null, new DefaultExceptionHandler());

    public ForgotDialogBox(boolean b) {
	super(true);
    }
}
