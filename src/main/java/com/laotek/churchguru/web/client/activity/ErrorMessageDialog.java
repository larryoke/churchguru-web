package com.laotek.churchguru.web.client.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.laotek.churchguru.daos.shared.exceptions.DisabledUserAccountException;
import com.laotek.churchguru.daos.shared.exceptions.DuplicateUserNameException;
import com.laotek.churchguru.daos.shared.exceptions.EmailAddressAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.daos.shared.exceptions.FullnameAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.LoginException;
import com.laotek.churchguru.daos.shared.exceptions.MobileAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.NoResultFoundException;
import com.laotek.churchguru.daos.shared.exceptions.UserRoleMissingException;
import com.laotek.churchguru.daos.shared.exceptions.UsernameAlreadyExistException;
import com.laotek.churchguru.web.shared.DuplicateUserProfileException;

public class ErrorMessageDialog extends DialogBox {

    private static ErrorMessageDialog dialog = new ErrorMessageDialog();

    private ErrorMessageDialog() {
	super(true);
	setGlassEnabled(true);
	setAnimationEnabled(true);
    }

    public static ErrorMessageDialog getInstance() {
	return dialog;
    }

    public void handleError(Throwable throwable) {
	String message = null;
	if (throwable instanceof LoginException) {
	    message = ((LoginException) throwable).getMessage();
	    display("Login Error", message, null, false);
	    Window.Location.reload();

	} else if (throwable instanceof ExpiredSessionException) {
	    display("The user session has expired",
		    "The user session has expired", null, false);
	    Window.Location.reload();

	} else if (throwable instanceof DisabledUserAccountException) {
	    display("Account disabled", "Account is disabled", null, false);

	} else if (throwable instanceof DuplicateUserNameException) {
	    display("Duplicated username", "User name is already taken", null,
		    false);

	} else if (throwable instanceof EmailAddressAlreadyExistException) {
	    display("Email address error",
		    "Another user is already using this email address", null,
		    false);

	} else if (throwable instanceof UsernameAlreadyExistException) {
	    display("Username error",
		    "Another user is already using this usernane", null, false);

	} else if (throwable instanceof MobileAlreadyExistException) {
	    display("Mobile error",
		    "Another user is already using this mobile number", null,
		    false);

	} else if (throwable instanceof FullnameAlreadyExistException) {
	    display("Fullname error",
		    "Another user is registered with this fullname", null,
		    false);

	} else if (throwable instanceof UserRoleMissingException) {
	    display("Permission error",
		    "You do not have the permission for this action", null,
		    false);

	} else if (throwable instanceof NoResultFoundException) {
	    display("No result error", "No result is found", null, false);

	} else if (throwable instanceof DuplicateUserProfileException) {
	    display("Duplicate user profile name error",
		    "This user profile name is already in use. Please choose another user profile name",
		    null, false);

	} else {
	    display("Access denied", "Access denied", null, false);
	}
    }

    private void display(String title, String message, String learnMore,
	    boolean autoHide) {
	if (title != null) {
	    setText(title);
	}
	handleLayout(message, learnMore);
	handleAutoHide(autoHide);
	center();
    }

    private void handleLayout(String message, String learnMore) {
	FlexTable layout = new FlexTable();
	layout.setBorderWidth(0);
	FlexCellFormatter formatter = layout.getFlexCellFormatter();
	layout.setWidth("100%");

	int row = 0;
	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	formatter.setColSpan(row, 0, 2);
	layout.setWidget(row, 0, new Image("images/app/alert.png"));

	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(row, 0, 2);
	layout.setWidget(++row, 0, new HTML(message + "</center>" + "<br>"));

	row = handleLearnMore(learnMore, layout, formatter, row);

	layout.setWidget(++row, 0, ok());
	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidget(++row, 0, new HTML("&nbsp;"));

	setWidget(layout);
    }

    private int handleLearnMore(String learnMore, FlexTable layout,
	    FlexCellFormatter formatter, int row) {
	if (learnMore != null) {

	    layout.setWidget(++row, 0, new LearnMorePanel(new HTML(learnMore)));

	    formatter.setHorizontalAlignment(row, 0,
		    HasHorizontalAlignment.ALIGN_CENTER);
	    formatter.setColSpan(row, 0, 2);
	}
	return row;
    }

    private void handleAutoHide(boolean autoHide) {
	if (autoHide) {
	    Timer timer = new Timer() {
		@Override
		public void run() {
		    hide();
		    cancel();
		}
	    };
	    timer.schedule(4000);
	}
    }

    private Button ok() {
	Button button = new Button("OK");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	return button;
    }
}
