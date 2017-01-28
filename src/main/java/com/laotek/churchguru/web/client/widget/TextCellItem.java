package com.laotek.churchguru.web.client.widget;

import java.util.Map;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextCellItem extends FormItem {
    private String errorMessage = null;
    protected TextBox textbox = new TextBox();
    private boolean numericCharsOnly;
    private boolean alphaCharsOnly;
    private String regex;
    private int min;
    private int max;

    private Map<String, Widget> rowWidget = null;

    public TextCellItem(String label, Map<String, Widget> rowWidget, boolean required) {
	super();
	this.label = label;
	setRequired(required);
	this.rowWidget = rowWidget;
	setWidget(0, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	initTextbox(label, "175px");

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
    }

    public TextCellItem(String label, Map<String, Widget> rowWidget, boolean required, String width) {
	super();
	this.label = label;
	setRequired(required);
	this.rowWidget = rowWidget;
	setWidget(0, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	initTextbox(label, width);

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
    }

    private void initTextbox(final String label, String width) {
	final TextBox textbox = getTextbox();

	textbox.setStylePrimaryName("fullNameTextboxInsideLabel");
	textbox.setWidth(width);
	textbox.setValue(label);

	textbox.addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(FocusEvent event) {
		if (textbox.getValue() != null && textbox.getValue().equals(label)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("textboxNormal");
		}
	    }
	});

	textbox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (textbox.getValue() != null && textbox.getValue().equals(label)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("textboxNormal");
		}
	    }
	});

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		if (textbox.getValue() == null || (textbox.getValue() != null && textbox.getValue().equals(""))) {
		    textbox.setValue(label);
		    textbox.setStylePrimaryName("fullNameTextboxInsideLabel");
		}
	    }
	});

	textbox.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		if (errorMessage != null && !"".equals(errorMessage)) {
		    displayError(errorMessage);
		}
	    }
	});

	textbox.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		PopupCellPanel.getInstance().hide();
	    }
	});
    }

    public String getValue() {
	String value = textbox.getValue();
	if (label.equals(value)) {
	    return null;
	}
	return value;
    }

    public void setValue(String value) {
	if (value != null && !value.equals("") && !value.equals(label)) {
	    textbox.setValue(value);
	    this.getTextbox().setStylePrimaryName("textboxNormal");
	}
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
	return validate(true);
    }

    public boolean validate(boolean displayError) {
	String value = textbox.getValue();
	if (isRequired() && value != null && value.equals("")) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("A value is required.");
	    }
	    return false;
	}

	if (isRequired() && value != null && value.equals(label)) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("A value is required.");
	    }
	    return false;
	}

	if (isRequired() && value == null) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("A value is required.");
	    }
	    return false;
	}

	if (min > 0 && value != null && value.length() < min) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("Value is less than minimum length of " + min);
	    }
	    return false;
	}

	if (max > 0 && value != null && max < value.length()) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("Value exceeds maximum length of " + max);
	    }
	    return false;
	}
	if (numericCharsOnly && value != null && value.matches("[a-zA-Z]+")) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("Only numeric characters allowed.");
	    }
	    return false;
	}
	if (alphaCharsOnly && value != null && value.matches("[0-9]")) {
	    textbox.setStylePrimaryName("textboxError");
	    if (displayError) {
		displayError("No numeric characters allowed");
	    }
	    return false;
	}
	if (value != null && !"".equals(value)) {
	    if (regex != null && !"".equals(regex)) {
		if (!value.matches(regex)) {
		    textbox.setStylePrimaryName("textboxError");
		    if (displayError) {
			displayError("Invalid format");
		    }
		    return false;
		}
	    }
	}
	removeErrorMessage();
	if (textbox.getValue() == null || (textbox.getValue() != null && textbox.getValue().equals(""))
		|| (textbox.getValue() != null && textbox.getValue().equals(label))) {
	    textbox.setStylePrimaryName("fullNameTextboxInsideLabel");
	} else {
	    textbox.setStylePrimaryName("textboxNormal");
	}
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
	textbox.removeStyleName("labelDisable");
	textbox.setEnabled(true);
    }

    protected void displayError(String error) {
	this.errorMessage = error;
	int x = textbox.getAbsoluteLeft();
	int y = textbox.getAbsoluteTop() + 15;
	PopupCellPanel.getInstance().setWidget(new Label(error));
	PopupCellPanel.getInstance().setPopupPosition(x, y);
	PopupCellPanel.getInstance().show();
    }

    protected void removeErrorMessage() {
	this.errorMessage = null;
    }

    public Map<String, Widget> getRowWidget() {
	return rowWidget;
    }

    public boolean empty() {
	return getValue() == null || "".equals(getValue());
    }
}
