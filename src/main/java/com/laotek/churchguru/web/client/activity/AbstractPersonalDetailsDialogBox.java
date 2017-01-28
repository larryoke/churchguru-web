package com.laotek.churchguru.web.client.activity;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.laotek.churchguru.daos.shared.exceptions.EmailAddressAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.FullnameAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.MobileAlreadyExistException;

public abstract class AbstractPersonalDetailsDialogBox extends DialogBox {

    public AbstractPersonalDetailsDialogBox(boolean autoHide, boolean modal) {
	super(autoHide, modal);
    }

    public AbstractPersonalDetailsDialogBox(boolean autoHide) {
	super(autoHide);
    }

    public void onFailure(Throwable throwable) {
	if (throwable instanceof EmailAddressAlreadyExistException) {
	    Window.alert("Another user is already using this email address");

	} else if (throwable instanceof MobileAlreadyExistException) {
	    Window.alert("Another user is already using this mobile number");

	} else if (throwable instanceof FullnameAlreadyExistException) {
	    Window.alert("Another user is registered with this fullname");
	}
    }

    protected abstract void createExistingAction();
}
