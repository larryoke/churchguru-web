package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.shared.UserDto;

public class EditUserDialog extends UserDialog {
    private static EditUserDialog dialog = new EditUserDialog();

    protected TextItem username = new TextItem("Username", true);
    protected SelectItem accountStatus = new SelectItem("Account Status", true);

    private long userId;
    private String userIdentifier;

    private EditUserDialog() {
	super(true);
	setSize("350px", "50px");
	setGlassEnabled(true);

	username.disable();

	FlexTable layout = (FlexTable) getForm(2);
	layout.setWidget(0, 0, username);
	accountStatus.getListBox().addItem(UserAccountStatus.INVITED.getDesc(),
		UserAccountStatus.INVITED.name());
	accountStatus.getListBox().addItem(UserAccountStatus.ACTIVE.getDesc(),
		UserAccountStatus.ACTIVE.name());
	accountStatus.getListBox().addItem(
		UserAccountStatus.DISABLED.getDesc(),
		UserAccountStatus.DISABLED.name());
	layout.setWidget(1, 0, accountStatus);

	setWidget(layout);
    }

    public static EditUserDialog getInstance() {
	return dialog;
    }

    public boolean validate() {
	form.addFormItem(username);
	form.addFormItem(accountStatus);

	return super.validate();
    }

    public void init(String caption, UserDto userDto) {
	center();
	setText(caption);

	userId = userDto.getId();
	userIdentifier = userDto.getIdentifier();
	username.setValue(userDto.getUsername());
	forenames.setValue(userDto.getForenames());
	surname.setValue(userDto.getSurname());
	emailAddress.setValue(userDto.getEmailAddress());
	mobileNumber.setValue(userDto.getMobile());

	boolean enable = userDto.getUsername().equals("admin") ? false : true;
	for (int i = 0; i < accountStatus.getListBox().getItemCount(); ++i) {
	    String value = accountStatus.getListBox().getValue(i);
	    if (userDto.getUserAccountStatus().name().equals(value)) {
		accountStatus.getListBox().setSelectedIndex(i);
		accountStatus.setEnabled(enable);
		break;
	    }
	}

	getProfileOptions(userDto.getUserProfileIdentifier());
    }

    public Button save() {
	Button button = new Button("Update User");
	boolean enable = username.equals("admin") ? false : true;
	button.setEnabled(enable);
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (validate()) {
		    EditUserDetailsAction action = new EditUserDetailsAction();
		    final UserDto currentUserDto = new UserDto();
		    currentUserDto.setId(userId);
		    currentUserDto.setIdentifier(userIdentifier);
		    currentUserDto.setEmailAddress(emailAddress.getValue());
		    currentUserDto.setForenames(forenames.getValue());
		    currentUserDto.setMobile(mobileNumber.getValue());
		    currentUserDto.setSurname(surname.getValue());
		    currentUserDto.setUserProfileIdentifier(userProfile
			    .getValue());
		    int index = accountStatus.getListBox().getSelectedIndex();
		    currentUserDto.setUserAccountStatus(UserAccountStatus
			    .valueOf(accountStatus.getListBox().getValue(index)));

		    action.setCurrentUserDto(currentUserDto);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext
			    .getInstance()
			    .getDispatchClient()
			    .execute(action,
				    new AsyncCallback<EditUserDetailsResult>() {
					@Override
					public void onFailure(
						Throwable throwable) {
					    ErrorMessageDialog.getInstance()
						    .handleError(throwable);
					}

					@Override
					public void onSuccess(
						EditUserDetailsResult result) {
					    hide();
					    ApplicationContext
						    .getInstance()
						    .getPlaceController()
						    .goTo(new SingleUserPlace(
							    currentUserDto
								    .getIdentifier()));

					}
				    });
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
