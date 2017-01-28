package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.MessageDialog;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.TextLongItem;

public class ForgotPasswordDialog extends ForgotDialogBox {

    private Form form = new Form();
    private TextLongItem username = new TextLongItem("Username", true);

    public ForgotPasswordDialog() {
	super(true);
	setGlassEnabled(true);
	setAnimationEnabled(true);

	setText("Forgot your password?");

	int row = 0;

	FlexTable mainPanel = new FlexTable();
	mainPanel.setBorderWidth(0);
	mainPanel.setWidget(row, 0, new Image("images/app/forgotAccess.png;"));
	mainPanel.setWidget(row, 1, new HTML("&nbsp;"));
	mainPanel.setWidget(row, 2, new HTML("&nbsp;"));
	mainPanel.getFlexCellFormatter().setWidth(row, 0, "47%");
	mainPanel.getFlexCellFormatter().setWidth(row, 1, "6%");
	mainPanel.getFlexCellFormatter().setWidth(row, 2, "47%");
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel
		.setWidget(
			++row,
			0,
			new HTML(
				"Enter your username here and you'll receive a message from the email address you're registered "
					+ "with to enable you to reset your password.<br>"
					+ "<b>You have 3 days to reset the password, or you'll have to start the process again.</b>"));
	mainPanel.getFlexCellFormatter().setColSpan(row, 0, 3);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel.setWidget(++row, 0, new HTML("&nbsp;"));
	mainPanel.getFlexCellFormatter().setColSpan(row, 0, 3);

	mainPanel.setWidget(++row, 0, username);
	mainPanel.getFlexCellFormatter().setColSpan(row, 0, 3);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel.setWidget(++row, 0, new HTML("&nbsp;"));
	mainPanel.getFlexCellFormatter().setColSpan(row, 0, 1);

	mainPanel.setWidget(++row, 0, cancel());
	mainPanel.setWidget(row, 1, new HTML("&nbsp;"));
	mainPanel.setWidget(row, 2, send());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_RIGHT);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(row, 2,
		HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel.setWidget(++row, 0, new HTML("&nbsp;"));
	mainPanel.getFlexCellFormatter().setColSpan(row, 0, 3);

	form.addFormItem(username);

	setGlassEnabled(true);

	setWidget(mainPanel);

    }

    private Anchor cancel() {
	Anchor anchor = new Anchor("Cancel");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	return anchor;
    }

    private Button send() {
	Button button = new Button("Send Now");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (form.validateForm()) {
		    ForgotCredentialAction action = new ForgotCredentialAction();
		    action.setUsername(username.getValue());
		    UserContext.getInstance().decorateClientSessionId(action);
		    dispatchClient.execute(action,
			    new AsyncCallback<ForgotCredentialResult>() {
				@Override
				public void onFailure(Throwable caught) {
				    MessageDialog
					    .getInstance()
					    .display(
						    "Problem",
						    "Unable to initiate the password reset operation. "
							    + "Please contact the system administrator.",
						    null, false);
				}

				@Override
				public void onSuccess(
					ForgotCredentialResult result) {
				    hide();
				    MessageDialog
					    .getInstance()
					    .display(
						    "Message sent..",
						    "<h2>Your message has been sent.</h2>",
						    null, true);
				}
			    });
		}
	    }
	});
	return button;
    }

}
