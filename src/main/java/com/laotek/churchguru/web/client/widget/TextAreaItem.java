package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextArea;

public class TextAreaItem extends FormItem {
    protected TextArea textArea = new TextArea();
    private boolean numericCharsOnly;
    private boolean alphaCharsOnly;
    private String regex;
    private int min;
    private int max;

    public TextAreaItem() {
	super();
	setWidget(1, 0, textArea);
	textArea.setStylePrimaryName("textboxNormal");

	getCellFormatter().setVerticalAlignment(0, 0,
		HasVerticalAlignment.ALIGN_TOP);

	textArea.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
    }

    public TextAreaItem(String label, boolean required) {
	super(label);
	setRequired(required);
	setWidget(1, 0, textArea);
	textArea.setStylePrimaryName("textboxNormal");

	getCellFormatter().setVerticalAlignment(1, 0,
		HasVerticalAlignment.ALIGN_TOP);
    }

    public TextAreaItem(String label, boolean required,
	    HorizontalAlignmentConstant alignmentConstant) {
	super(label, alignmentConstant);
	setRequired(required);
	setWidget(1, 0, textArea);
	textArea.setStylePrimaryName("textboxNormal");
    }

    public void setWidth(String width) {
	textArea.setWidth(width);
    }

    public void setHeight(String height) {
	textArea.setHeight(height);
    }

    public String getValue() {
	return textArea.getValue();
    }

    public void setValue(String value) {
	textArea.setValue(value);
    }

    public void setRange(int min, int max) {
	this.min = min;
	this.max = max;
    }

    public void focus() {
	textArea.setFocus(true);
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
	String value = textArea.getValue();
	if (isRequired() && value != null && value.equals("")) {
	    textArea.addStyleName("textAreaError");
	    displayError("A value is required.");
	    return false;
	}

	if (isRequired() && value == null) {
	    textArea.addStyleName("textAreaError");
	    displayError("A value is required.");
	    return false;
	}

	if (min > 0 && value != null && value.length() < min) {
	    textArea.addStyleName("textAreaError");
	    displayError("Value is less than minimum length of " + min);
	    return false;
	}

	if (max > 0 && value != null && max < value.length()) {
	    textArea.addStyleName("textAreaError");
	    displayError("Value exceeds maximum length of " + max);
	    return false;
	}
	if (numericCharsOnly && value != null && value.matches("[a-zA-Z]+")) {
	    textArea.addStyleName("textAreaError");
	    displayError("Only numeric characters allowed.");
	    return false;
	}
	if (alphaCharsOnly && value != null && value.matches("[0-9]")) {
	    textArea.addStyleName("textAreaError");
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
	textArea.removeStyleName("textAreaError");
	return true;
    }

    public TextArea getTextArea() {
	return textArea;
    }

    @Override
    public void disable() {
	disableLabel();
	textArea.addStyleName("labelDisable");
	textArea.setEnabled(false);
    }

    @Override
    public void enable() {
	enableLabel();
	textArea.addStyleName("labelNormal");
	textArea.removeStyleName("labelDisable");
	textArea.setEnabled(true);
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
