package com.laotek.churchguru.web.client.activity.user;

import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.laotek.churchguru.web.client.EmailRecipientContext;
import com.laotek.churchguru.web.client.UserRowSelectionContext;

public class UserSelectionMenu extends FlexTable {

    private Button emailRecipientButton = new Button(
	    "Add to Email Recipient List");

    public UserSelectionMenu() {

	emailRecipientButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		Set<String> ids = UserRowSelectionContext.getInstance()
			.copyIds();
		if (ids.size() > 0) {
		    UserRowSelectionContext.getInstance().clear();
		    EmailRecipientContext.getInstance().addUserRecipients(
			    "Users", ids);
		}

		// ApplicationContext.getInstance().getPlaceController()
		// .goTo(new ComposeEmailNewsLetterPlace(null, true));
	    }
	});

	setWidget(0, 0, new HTML("&nbsp;"));
	setWidget(0, 1, new HTML("&nbsp;"));
	setWidget(0, 2, new HTML("&nbsp;"));
	setWidget(0, 3, new HTML("&nbsp;"));
	setWidget(0, 4, emailRecipientButton);
	setWidget(0, 5, new HTML("&nbsp;"));
	setWidget(0, 6, new HTML("&nbsp;"));
    }

    public void enableButtons(boolean enabled) {
	emailRecipientButton.setEnabled(enabled);
    }
}
