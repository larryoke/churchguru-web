package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ApplicationMessages;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.shared.UserDto;

public class NewUserDialog extends UserDialog {
    private static NewUserDialog dialog = new NewUserDialog();

    private NewUserDialog() {
	super(true);
	setSize("350px", "50px");
	setGlassEnabled(true);
	setWidget(getForm(0));
    }

    public static NewUserDialog getInstance() {
	return dialog;
    }

    public void init(String caption) {
	center();
	setText(caption);
	getProfileOptions(null);

    }

    public Button save() {
	Button button = new Button("Add User");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (validate()) {
		    if (Window.confirm(ApplicationMessages.instance
			    .newUserEmailSendMessage())) {

			SubmitUserDetailsAction action = new SubmitUserDetailsAction();
			UserDto dto = new UserDto();
			dto.setEmailAddress(emailAddress.getValue());
			dto.setForenames(forenames.getValue());
			dto.setSurname(surname.getValue());
			dto.setMobile(mobileNumber.getValue());
			dto.setUserProfileIdentifier(userProfile.getValue());

			action.setNewUserDto(dto);
			UserContext.getInstance().decorateClientSessionId(
				action);

			UserContext
				.getInstance()
				.getDispatchClient()
				.execute(
					action,
					new AsyncCallback<SubmitUserDetailsResult>() {
					    @Override
					    public void onFailure(
						    Throwable throwable) {
						ErrorMessageDialog
							.getInstance()
							.handleError(throwable);
					    }

					    @Override
					    public void onSuccess(
						    SubmitUserDetailsResult result) {

						hide();
						ApplicationContext
							.getInstance()
							.getPlaceController()
							.goTo(new AllUserPlace(
								"allUser"));

					    }
					});

		    }
		}
	    }
	});
	return button;
    }

    public Anchor cancel() {
	Anchor anchor = new Anchor("Cancel");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	return anchor;
    }
}
