package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SelectItem extends FormItem {

    private ListBox listBox = new ListBox();

    public SelectItem() {
	setWidget(0, 0, listBox);
	listBox.setStylePrimaryName("selectboxNormal");
    }

    public SelectItem(String label, boolean required) {
	super(label);
	setRequired(required);
	setWidget(1, 0, listBox);
	listBox.setStylePrimaryName("selectboxNormal");
    }

    public String getValue() {
	int selectedIndex = listBox.getSelectedIndex();
	return listBox.getValue(selectedIndex);
    }

    public String getValue(int index) {
	return listBox.getValue(index);
    }

    public void addItem(String value) {
	listBox.addItem(value);
    }

    public void addChangeHandler(ChangeHandler handler) {
	listBox.addChangeHandler(handler);
    }

    public void addItem(String label, String value) {
	listBox.addItem(label, value);
    }

    public void addItem(String label, String value, boolean selectItem) {
	listBox.addItem(label, value);
	if (selectItem) {
	    listBox.setSelectedIndex(listBox.getItemCount() - 1);
	}
    }

    @Override
    public boolean validate() {
	int selectedIndex = listBox.getSelectedIndex();
	String value = listBox.getValue(selectedIndex);
	if (isRequired() && value != null && value.equals("")) {
	    listBox.addStyleName("textboxError");
	    displayError("A value is required.");
	    return false;
	}

	if (isRequired() && value == null) {
	    listBox.addStyleName("textboxError");
	    displayError("A value is required.");
	    return false;
	}
	removeErrorMessage();
	listBox.removeStyleName("textboxError");
	return true;
    }

    public ListBox getListBox() {
	return listBox;
    }

    public void clear() {
	listBox.clear();
    }

    public void disable() {
	disableLabel();
	listBox.addStyleName("labelDisable");
	listBox.setEnabled(false);
    }

    public void enable() {
	enableLabel();
	listBox.addStyleName("labelNormal");
	listBox.removeStyleName("labelDisable");
	listBox.setEnabled(true);
    }

    public void setEnabled(boolean value) {
	if (value) {
	    enable();
	} else {
	    disable();
	}
    }

    protected void displayError(String error) {
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
