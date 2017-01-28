package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.LearnMorePanel;
import com.laotek.churchguru.web.client.activity.ScrollDialogBox;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;

public abstract class UserProfileDialog extends ScrollDialogBox {

    protected String profileNameIdentifier;

    protected Form form = new Form();
    protected TextItem profileName = new TextItem("Profile name", true);

    protected SelectItem memberRole = new SelectItem("Members and Guests", true);
    protected SelectItem quickEmailRole = new SelectItem("Quick Email", true);
    protected SelectItem appUserRole = new SelectItem("Users", true);
    protected SelectItem organisationRole = new SelectItem("Organisation", true);
    protected SelectItem donationRole = new SelectItem("Donations", true);

    public UserProfileDialog(boolean value) {
	super(value);
    }

    protected boolean validate() {
	form.addFormItem(profileName);

	form.addFormItem(memberRole);

	form.addFormItem(quickEmailRole);

	form.addFormItem(appUserRole);

	form.addFormItem(organisationRole);

	form.addFormItem(donationRole);

	return form.validateForm();
    }

    protected Widget getForm(int row) {
	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	// layout.setWidth("500px");

	layout.setWidget(row++, 0, getLearnMore());

	layout.setWidget(row++, 0, profileName);

	layout.setHTML(row++, 0, "&nbsp;");

	//
	// member
	organisationRole.getListBox().addItem("", "");
	organisationRole.getListBox().addItem(
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY.getDesc(),
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY.name());
	organisationRole.getListBox().addItem(
		UserRoleName.ORGANISATION_DATA_CRUD.getDesc(),
		UserRoleName.ORGANISATION_DATA_CRUD.name());
	layout.setWidget(row++, 0, organisationRole);

	//
	// member
	memberRole.getListBox().addItem("", "");
	memberRole.getListBox().addItem(
		UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY.getDesc(),
		UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY.name());
	memberRole.getListBox().addItem(
		UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.getDesc(),
		UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.name());
	layout.setWidget(row++, 0, memberRole);

	//
	// quick email
	quickEmailRole.getListBox().addItem("", "");
	quickEmailRole.getListBox().addItem(
		UserRoleName.QUICK_EMAIL_NOT_ALLOWED.getDesc(),
		UserRoleName.QUICK_EMAIL_NOT_ALLOWED.name());
	quickEmailRole.getListBox().addItem(
		UserRoleName.QUICK_EMAIL_ALLOWED.getDesc(),
		UserRoleName.QUICK_EMAIL_ALLOWED.name());
	layout.setWidget(row++, 0, quickEmailRole);

	//
	// app role
	appUserRole.getListBox().addItem("", "");
	appUserRole.getListBox().addItem(
		UserRoleName.USER_VIEW_NOT_ALLOWED.getDesc(),
		UserRoleName.USER_VIEW_NOT_ALLOWED.name());
	appUserRole.getListBox().addItem(
		UserRoleName.USER_VIEW_ALLOWED.getDesc(),
		UserRoleName.USER_VIEW_ALLOWED.name());
	appUserRole.getListBox().addItem(UserRoleName.USER_CRUD.getDesc(),
		UserRoleName.USER_CRUD.name());
	layout.setWidget(row++, 0, appUserRole);

	//
	// donation role
	donationRole.getListBox().addItem("", "");
	donationRole.getListBox().addItem(
		UserRoleName.DONATION_VIEW_NOT_ALLOWED.getDesc(),
		UserRoleName.DONATION_VIEW_NOT_ALLOWED.name());
	donationRole.getListBox().addItem(
		UserRoleName.DONATION_VIEW_ALLOWED.getDesc(),
		UserRoleName.DONATION_VIEW_ALLOWED.name());
	layout.setWidget(row++, 0, donationRole);

	//
	//
	layout.setWidget(row++, 0, new HTML("&nbsp;"));
	layout.setWidget(row++, 0, new HTML("&nbsp;"));

	FlexTable buttonPanel = new FlexTable();
	FlexCellFormatter fcf = buttonPanel.getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setWidth(0, 0, "150px");
	buttonPanel.setWidget(0, 0, cancel());
	buttonPanel.setHTML(0, 1, "&nbsp;");
	buttonPanel.setWidget(0, 2, save());
	layout.setWidget(row++, 0, buttonPanel);

	return layout;
    }

    protected abstract Button save();

    protected abstract Anchor cancel();

    protected abstract LearnMorePanel getLearnMore();
}
