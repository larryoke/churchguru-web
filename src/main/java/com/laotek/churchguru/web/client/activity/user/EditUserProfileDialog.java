package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.activity.LearnMorePanel;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.shared.UserProfileDto;

public class EditUserProfileDialog extends UserProfileDialog {
    private static EditUserProfileDialog dialog = new EditUserProfileDialog();

    private UserProfileDto userProfileDto;

    private EditUserProfileDialog() {
	super(true);
	setSize("350px", "50px");
	setGlassEnabled(true);
	setWidget(getForm(0));
    }

    public static EditUserProfileDialog getInstance() {
	return dialog;
    }

    public void init(String caption, UserProfileDto currentUser) {
	userProfileDto = currentUser;
	profileName.setValue(userProfileDto.getUserProfileName());
	profileNameIdentifier = userProfileDto.getUserProfileIdentifier();

	initDropDown(memberRole, userProfileDto.getMemberRole());
	initDropDown(quickEmailRole, userProfileDto.getQuickEmailRole());
	initDropDown(appUserRole, userProfileDto.getAppUserRole());
	initDropDown(organisationRole, userProfileDto.getOrganisationRole());

	setText(caption);
	center();
    }

    private void initDropDown(SelectItem selectItem, UserRoleName userRoleName) {
	for (int i = 0; i < selectItem.getListBox().getItemCount(); ++i) {
	    String itemValue = selectItem.getListBox().getValue(i);
	    if (itemValue != null && !itemValue.equals("")) {
		UserRoleName currentValue = UserRoleName.valueOf(itemValue);

		if (currentValue.equals(userRoleName)) {
		    selectItem.getListBox().setSelectedIndex(i);
		    break;
		}

	    }
	}
    }

    public Button save() {
	Button button = new Button("Update User Profile");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (validate()) {
		    EditUserProfileAction action = new EditUserProfileAction();

		    userProfileDto.setUserProfileName(profileName.getValue());

		    userProfileDto.setMemberRole(UserRoleName
			    .valueOf(memberRole.getValue()));
		    userProfileDto.setQuickEmailRole(UserRoleName
			    .valueOf(quickEmailRole.getValue()));
		    userProfileDto.setAppUserRole(UserRoleName
			    .valueOf(appUserRole.getValue()));
		    userProfileDto.setOrganisationRole(UserRoleName
			    .valueOf(organisationRole.getValue()));
		    userProfileDto.setDonationRole(UserRoleName
			    .valueOf(donationRole.getValue()));

		    action.setUserProfileDto(userProfileDto);
		    UserContext.getInstance().decorateClientSessionId(action);

		    UserContext
			    .getInstance()
			    .getDispatchClient()
			    .execute(action,
				    new AsyncCallback<EditUserProfileResult>() {
					@Override
					public void onFailure(
						Throwable throwable) {
					    ErrorMessageDialog.getInstance()
						    .handleError(throwable);
					}

					@Override
					public void onSuccess(
						EditUserProfileResult result) {

					    hide();
					    ApplicationContext
						    .getInstance()
						    .getPlaceController()
						    .goTo(new UserProfilesPlace(
							    "userProfiles"));

					}
				    });
		} else {
		    scrollSlowlyToTop();
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

    @Override
    protected LearnMorePanel getLearnMore() {
	return new LearnMorePanel(
		new HTML(
			"This would enable you to select various level of perimissions and save as a profile :"
				+ "<ul>"
				+ "<li>For each role below, select the level of permission"
				+ "<li>You may later reuse this profile for multiple users that are fit for the select level permissions ."
				+ "</ul><br/>"));
    }
}
