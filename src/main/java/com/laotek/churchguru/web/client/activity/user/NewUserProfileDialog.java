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
import com.laotek.churchguru.web.shared.UserProfileDto;

public class NewUserProfileDialog extends UserProfileDialog {
    private static NewUserProfileDialog dialog = new NewUserProfileDialog();

    private NewUserProfileDialog() {
	super(true);
	setSize("350px", "50px");
	setGlassEnabled(true);
	setWidget(getForm(0));
    }

    public static NewUserProfileDialog getInstance() {
	return dialog;
    }

    public void init(String caption) {
	setText(caption);
	center();
    }

    public Button save() {
	Button button = new Button("Add User Profile");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (validate()) {
		    SubmitUserProfileAction action = new SubmitUserProfileAction();
		    UserProfileDto newUserProfileDto = new UserProfileDto();

		    newUserProfileDto.setUserProfileName(profileName.getValue());

		    newUserProfileDto.setMemberRole(UserRoleName
			    .valueOf(memberRole.getValue()));
		    newUserProfileDto.setQuickEmailRole(UserRoleName
			    .valueOf(quickEmailRole.getValue()));
		    newUserProfileDto.setAppUserRole(UserRoleName
			    .valueOf(appUserRole.getValue()));
		    newUserProfileDto.setOrganisationRole(UserRoleName
			    .valueOf(organisationRole.getValue()));
		    newUserProfileDto.setDonationRole(UserRoleName
			    .valueOf(donationRole.getValue()));

		    action.setNewUserProfileDto(newUserProfileDto);
		    UserContext.getInstance().decorateClientSessionId(action);

		    UserContext
			    .getInstance()
			    .getDispatchClient()
			    .execute(
				    action,
				    new AsyncCallback<SubmitUserDetailsResult>() {
					@Override
					public void onFailure(
						Throwable throwable) {
					    ErrorMessageDialog.getInstance()
						    .handleError(throwable);
					}

					@Override
					public void onSuccess(
						SubmitUserDetailsResult result) {

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
