package com.laotek.churchguru.web.client.activity;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.client.widget.TextLongItem;

public class SendQuickEmailDialog extends ScrollDialogBox {

    private Form form = new Form();
    private TextItem to = new TextItem("To", false);
    private SelectItem replyToEmailItem = new SelectItem("Reply To", true);
    private TextLongItem subject = new TextLongItem("Subject", true,
	    HasHorizontalAlignment.ALIGN_LEFT);
    private TextArea body = new TextArea();
    private CheckBoxItem sendCopyToSender;
    private VerticalPanel fromPanel = new VerticalPanel();
    private String recipientEmailAddress;
    private String recipientIdentifier;
    private EmailRecipientType emailRecipientType;

    private static SendQuickEmailDialog dialog = new SendQuickEmailDialog();

    private SendQuickEmailDialog() {
	super(false);
	to.getTextbox().setReadOnly(true);
	sendCopyToSender = new CheckBoxItem("Send me a copy of message", false);
	setGlassEnabled(true);

	setWidget();

	setText("Your feedback is welcome. Thank you.");

	form.addFormItem(replyToEmailItem);

	form.addFormItem(subject);

	setGlassEnabled(true);

    }

    private void setAnimation() {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		setAnimationEnabled(true);
	    }
	});
    }

    public static SendQuickEmailDialog getInstance() {
	return dialog;
    }

    public void init(EmailRecipientType emailRecipientType,
	    String recipientIdentifier, final String recipientEmailAddress) {
	this.emailRecipientType = emailRecipientType;
	this.recipientIdentifier = recipientIdentifier;
	this.recipientEmailAddress = recipientEmailAddress;
	if (recipientEmailAddress != null && !recipientEmailAddress.equals("")) {
	    setText("Send email to " + recipientEmailAddress);
	    to.setValue(recipientEmailAddress);
	}

	createSenderOptions();

	center();
    }

    private void createSenderOptions() {
	replyToEmailItem.getListBox().clear();
	replyToEmailItem.getListBox().addItem("");
	replyToEmailItem.getListBox().addItem(
		"Me <" + UserContext.getInstance().getEmailAddress() + ">",
		UserContext.getInstance().getEmailAddress());
	replyToEmailItem.getListBox()
		.addItem(
			"Admin <"
				+ ApplicationContext.getInstance()
					.getAdminEmail() + ">",
			ApplicationContext.getInstance().getAdminEmail());
    }

    private void setWidget() {
	FlexTable layout = new FlexTable();
	layout.setBorderWidth(0);
	FlexCellFormatter formatter = layout.getFlexCellFormatter();
	layout.setWidth("100%");

	fromPanel.add(replyToEmailItem);

	layout.setWidget(0, 0, to);
	formatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(0, 0, 2);

	layout.setWidget(1, 0, fromPanel);
	formatter.setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(1, 0, 2);

	layout.setWidget(2, 0, subject);
	formatter.setHorizontalAlignment(2, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(2, 0, 2);

	// layout.setWidget(3, 0, displayMailMergeOptionsAnchor());
	// formatter.setHorizontalAlignment(3, 0,
	// HasHorizontalAlignment.ALIGN_LEFT);
	// formatter.setColSpan(3, 0, 2);

	body.setWidth("97%");
	body.setHeight("240px");
	layout.setWidget(4, 0, body);
	formatter.setColSpan(4, 0, 2);

	layout.setWidget(5, 0, sendCopyToSender);
	formatter.setColSpan(5, 0, 2);

	layout.setWidget(6, 1, new HTML("&nbsp;"));

	layout.setWidget(6, 0, cancel());
	layout.setWidget(6, 1, send());

	formatter.setHorizontalAlignment(6, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	formatter.setHorizontalAlignment(6, 1,
		HasHorizontalAlignment.ALIGN_CENTER);

	setWidget(layout);
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
	final Button button = new Button("Send Now");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (form.validateForm() && validateMessage()) {
		    button.setEnabled(false);
		    SendQuickEmailAction action = new SendQuickEmailAction();
		    action.setRecipientIdentifier(recipientIdentifier);
		    action.setEmailRecipientType(emailRecipientType);
		    action.setReplyToAddr(replyToEmailItem.getValue());
		    action.setToAddr(recipientEmailAddress);
		    action.setSubject(subject.getValue());
		    action.setBody(body.getValue());
		    action.setSendCopyToSender(sendCopyToSender.getCheckbox()
			    .getValue());
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext
			    .getInstance()
			    .getDispatchClient()
			    .execute(action,
				    new AsyncCallback<SendQuickEmailResult>() {
					@Override
					public void onFailure(Throwable caught) {
					    button.setEnabled(true);
					    Window.alert(caught.getMessage());
					    MessageDialog
						    .getInstance()
						    .display(
							    "Problem",
							    caught.getMessage(),
							    null, false);
					}

					@Override
					public void onSuccess(
						SendQuickEmailResult result) {
					    button.setEnabled(true);
					    hide();
					    MessageDialog
						    .getInstance()
						    .display(
							    "Message sent..",
							    "<h2>Your message has been sent.</h2>",
							    null, true);
					}
				    });
		} else {
		    scrollSlowlyToTop();
		}
	    }
	});
	return button;
    }

    private boolean validateMessage() {
	if (EmailRecipientType.USER.equals(emailRecipientType)
		&& body.getValue().contains("<<address>>")) {
	    MessageDialog
		    .getInstance()
		    .display(
			    "Problem",
			    "Cannot substitute the address of the user. Address of user is not known",
			    null, false);
	    return false;
	}
	return true;
    }

}
