package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class MonthYearItem extends FormItem {

    private ListBox monthListBox = new ListBox();
    private ListBox yearListBox = new ListBox();

    public MonthYearItem(String label) {
	super(label);

	initMonth();

    }

    private void initMonth() {
	monthListBox.addItem("Jan");
	monthListBox.addItem("Feb");
	monthListBox.addItem("Mar");
	monthListBox.addItem("Apr");
	monthListBox.addItem("May");
	monthListBox.addItem("Jun");
	monthListBox.addItem("Jul");
	monthListBox.addItem("Aug");
	monthListBox.addItem("Sep");
	monthListBox.addItem("Oct");
	monthListBox.addItem("Nov");
	monthListBox.addItem("Dec");
    }

    @Override
    boolean validate() {
	return false;
    }

    @Override
    public void disable() {
	monthListBox.setEnabled(false);
	yearListBox.setEnabled(false);
    }

    @Override
    public void enable() {
	monthListBox.setEnabled(true);
	yearListBox.setEnabled(true);
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
