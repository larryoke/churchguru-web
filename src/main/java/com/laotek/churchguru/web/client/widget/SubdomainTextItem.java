package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class SubdomainTextItem extends FormItem {
    protected TextBox textbox = new TextBox();
    private boolean numericCharsOnly;
    private boolean alphaCharsOnly;
    private String regex;
    private int min;
    private int max;

    public SubdomainTextItem(String label, String domain) {
	super(label);
	FlexTable borderBox = new FlexTable();
	borderBox.setStylePrimaryName("subdomainTextArea");
	borderBox.setWidget(0, 1, textbox);
	HTML mainDomain = new HTML(".<b>" + domain + "</b>");
	borderBox.setWidget(0, 2, mainDomain);
	setWidget(0, 1, borderBox);
	textbox.setStylePrimaryName("subdomainTextboxDomain");
    }

    public String getValue() {
	return textbox.getValue();
    }

    public void setValue(String value) {
	textbox.setValue(value);
    }

    public void setRange(int min, int max) {
	this.min = min;
	this.max = max;
    }

    public void focus() {
	textbox.setFocus(true);
    }

    public void setRangeMin(int min) {
	this.min = min;
    }

    public void setRangeMax(int max) {
	this.max = max;
    }

    public boolean isNumericCharsOnly() {
	return numericCharsOnly;
    }

    public void setNumericCharsOnly(boolean numericCharsOnly) {
	this.numericCharsOnly = numericCharsOnly;
    }

    public boolean isAlphaCharsOnly() {
	return alphaCharsOnly;
    }

    public void setAlphaCharsOnly(boolean alphaCharsOnly) {
	this.alphaCharsOnly = alphaCharsOnly;
    }

    public String getRegex() {
	return regex;
    }

    public void setRegex(String regex) {
	this.regex = regex;
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

	if (min > 0 && value != null && value.length() < min) {
	    textbox.addStyleName("textboxError");
	    displayError("Value is less than minimum length of " + min);
	    return false;
	}

	if (max > 0 && value != null && max < value.length()) {
	    textbox.addStyleName("textboxError");
	    displayError("Value exceeds maximum length of " + max);
	    return false;
	}
	if (numericCharsOnly && value != null && value.matches("[a-zA-Z]+")) {
	    textbox.addStyleName("textboxError");
	    displayError("Only numeric characters allowed.");
	    return false;
	}
	if (alphaCharsOnly && value != null && value.matches("[0-9]")) {
	    textbox.addStyleName("textboxError");
	    displayError("No numeric characters allowed");
	    return false;
	}
	if (regex != null && !"".equals(regex)) {
	    if (!value.matches(regex)) {
		displayError("Invalid format");
		return false;
	    }
	}
	removeErrorMessage();
	textbox.removeStyleName("textboxError");
	return true;
    }

    public TextBox getTextbox() {
	return textbox;
    }

    @Override
    public void disable() {
	disableLabel();
	textbox.addStyleName("labelDisable");
	textbox.setEnabled(false);
    }

    @Override
    public void enable() {
	enableLabel();
	textbox.addStyleName("labelNormal");
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
