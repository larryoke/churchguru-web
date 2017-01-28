package com.laotek.churchguru.web.client.activity.user;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.PhoneItem;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;

public abstract class UserDialog extends DialogBox {

    protected Form form = new Form();
    protected TextItem forenames = new TextItem("First Name", true);
    protected TextItem surname = new TextItem("Surname", true);
    protected TextItem emailAddress = new TextItem("Email Address", true);
    protected PhoneItem mobileNumber = new PhoneItem("Mobile Number", true);
    protected SelectItem userProfile = new SelectItem("User Profile", true);

    public UserDialog(boolean value) {
	super(value);
    }

    protected boolean validate() {
	form.addFormItem(forenames);

	form.addFormItem(surname);

	emailAddress
		.setRegex("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
	form.addFormItem(emailAddress);

	form.addFormItem(mobileNumber);

	form.addFormItem(userProfile);

	return form.validateForm();
    }

    protected void getProfileOptions(final String currentUserProfileIdentifier) {
	GetUserProfileOptionsAction action = new GetUserProfileOptionsAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<GetUserProfileOptionsResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				ErrorMessageDialog.getInstance().handleError(
					throwable);
			    }

			    @Override
			    public void onSuccess(
				    GetUserProfileOptionsResult result) {
				userProfile.getListBox().clear();
				userProfile.getListBox().addItem("", "");
				for (Map.Entry<String, String> entry : result
					.getProfiles().entrySet()) {
				    userProfile.getListBox().addItem(
					    entry.getValue(), entry.getKey());
				    if (entry.getKey().equals(
					    currentUserProfileIdentifier)) {
					int index = userProfile.getListBox()
						.getItemCount() - 1;
					userProfile.getListBox()
						.setSelectedIndex(index);
				    }
				}

			    }
			});
    }

    protected Widget getForm(int row) {
	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidth("500px");

	layout.setWidget(row++, 0, userProfile);

	layout.setWidget(row++, 0, forenames);

	layout.setWidget(row++, 0, surname);

	layout.setWidget(row++, 0, surname);

	layout.setWidget(row++, 0, emailAddress);

	layout.setWidget(row++, 0, mobileNumber);

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
}
