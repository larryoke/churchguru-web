package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordItem extends FormItem {
    private PasswordTextBox textbox = new PasswordTextBox();
    private PasswordItem other;

    public PasswordItem(String label, boolean required) {
	super(label);
	setRequired(required);
	setWidget(1, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	textbox.setWidth("300px");
	textbox.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {

	    }
	});
    }

    public PasswordTextBox getTextbox() {
	return textbox;
    }

    public void setValue(String value) {
	textbox.setValue(value);
    }

    public String getValue() {
	return textbox.getValue();
    }

    public void setOtherPasswordItem(PasswordItem other) {
	this.other = other;
    }

    @Override
    public boolean validate() {
	String value = textbox.getValue();
	if (isRequired() && value != null && value.equals("")) {
	    textbox.addStyleName("textboxError");
	    displayError("A value is required.");
	    return false;
	}

	if (isRequired() && value == null) {
	    textbox.addStyleName("textboxError");
	    displayError("A value is required.");
	    return false;
	}
	if (other != null && other.getTextbox() != null
		&& !other.getTextbox().getValue().equals(value)) {
	    textbox.addStyleName("textboxError");
	    displayError("Passwords do not match");
	    return false;
	}
	removeErrorMessage();
	textbox.removeStyleName("textboxError");
	return true;
    }

    public void focus() {
	textbox.setFocus(true);
    }

    @Override
    public void disable() {
	textbox.setEnabled(false);
    }

    @Override
    public void enable() {
	textbox.setEnabled(true);
    }

    protected void displayError(String error) {
	HTML html = new HTML(error);
	html.setStylePrimaryName("errorMessage");
	setWidget(2, 0, html);
    }

    protected void removeErrorMessage() {
	if (isCellPresent(2, 1)) {
	    clearCell(2, 1);
	}
    }
}
