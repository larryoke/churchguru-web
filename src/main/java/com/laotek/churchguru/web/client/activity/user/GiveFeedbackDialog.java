package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.laotek.churchguru.model.shared.enums.FeedbackMessageType;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.activity.MessageDialog;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.SelectItem;

public class GiveFeedbackDialog extends DialogBox {

    private Form form = new Form();
    private SelectItem subject = new SelectItem("Subject", true);
    private TextArea body = new TextArea();
    private CheckBoxItem sendCopyToSender = new CheckBoxItem(
	    "Send me a copy of message", false);

    public GiveFeedbackDialog() {
	super(true);
	setGlassEnabled(true);
	setAnimationEnabled(true);
	setWidget();

	setText("Your feedback is welcome. Thank you.");

	subject.getListBox().addItem("");
	for (FeedbackMessageType type : FeedbackMessageType.values()) {
	    subject.getListBox().addItem(type.getDesc());
	}
	form.addFormItem(subject);
    }

    private void setWidget() {
	FlexTable layout = new FlexTable();
	layout.setBorderWidth(0);
	FlexCellFormatter formatter = layout.getFlexCellFormatter();

	layout.setWidget(0, 0, subject);
	formatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(0, 0, 2);

	body.setWidth("350px");
	body.setHeight("250px");
	layout.setWidget(1, 0, body);
	formatter.setColSpan(1, 0, 2);

	layout.setWidget(2, 0, sendCopyToSender);
	formatter.setColSpan(2, 0, 2);

	layout.setWidget(4, 0, new HTML("&nbsp;"));

	layout.setWidget(4, 0, cancel());
	layout.setWidget(4, 1, send());
	formatter.setHorizontalAlignment(4, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	formatter.setHorizontalAlignment(4, 1,
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
	Button button = new Button("Send Now");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (form.validateForm()) {
		    GiveFeedbackAction action = new GiveFeedbackAction();
		    action.setType(FeedbackMessageType.find(subject.getValue()));
		    action.setBody(body.getValue());
		    action.setSendCopyToSender(sendCopyToSender.getCheckbox()
			    .getValue());

		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext
			    .getInstance()
			    .getDispatchClient()
			    .execute(action,
				    new AsyncCallback<GiveFeedbackResult>() {
					@Override
					public void onFailure(Throwable caught) {
					    ErrorMessageDialog.getInstance()
						    .handleError(caught);
					}

					@Override
					public void onSuccess(
						GiveFeedbackResult result) {
					    hide();
					    MessageDialog
						    .getInstance()
						    .display(
							    "Message sent...",
							    "Thank you very much for your message.<br/>We shall reply contact you as soon as possible.",
							    null, true);
					}
				    });
		}
	    }
	});
	return button;
    }

}
