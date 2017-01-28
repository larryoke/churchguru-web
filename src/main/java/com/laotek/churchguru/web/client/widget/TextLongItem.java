package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public class TextLongItem extends TextItem {
    public TextLongItem() {
	textbox.setWidth("350px");
    }

    public TextLongItem(String label, boolean required) {
	super(label, required);
	textbox.setWidth("350px");
    }

    public TextLongItem(String label, boolean required,
	    HorizontalAlignmentConstant alignment) {
	super(label, required, alignment);
	textbox.setWidth("350px");
    }

    public void displayError(String error) {
	HTML html = new HTML(error);
	html.setStylePrimaryName("errorMessage");
	setWidget(2, 0, html);
    }

    protected void removeErrorMessage() {
	if (isCellPresent(2, 0)) {
	    clearCell(2, 0);
	}
    }
}
