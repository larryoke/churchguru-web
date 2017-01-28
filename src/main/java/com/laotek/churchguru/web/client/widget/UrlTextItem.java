package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.HTML;

public class UrlTextItem extends TextItem {

    public UrlTextItem(String label) {
	super(label, true);
	clearCell(1, 0);
	setWidget(1, 0, new HTML("http://"));
	setWidget(1, 1, textbox);
	setWidget(1, 2, new HTML(".bigregister.net"));
    }

    @Override
    protected void displayError(String error) {
	setWidget(2, 1, new HTML(error));
    }

    @Override
    protected void removeErrorMessage() {
	if (isCellPresent(2, 1)) {
	    clearCell(2, 1);
	}
    }
}
