package com.laotek.churchguru.web.client.widget;

import java.util.Map;

import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SelectCellItem extends FormItem {
    private ListBox listBox = new ListBox();

    private SimplePanel borderPanel = new SimplePanel(listBox);

    private Map<String, Widget> rowWidget = null;

    public SelectCellItem(String label, Map<String, Widget> rowWidget, boolean required) {
	setRequired(required);
	this.label = label;
	this.rowWidget = rowWidget;
	addItem("");
	addItem("");

	SelectElement selectElement = SelectElement.as(listBox.getElement());
	NodeList<OptionElement> options = selectElement.getOptions();
	options.getItem(1).setLabel(label);
	// options.getItem(1).getStyle().setColor("#F8F8F8");
	options.getItem(1).setDisabled(true);

	setWidget(0, 0, borderPanel);

	// don't think this has any eefect
	// listBox.setStylePrimaryName("selectboxNormal");

	listBox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
    }

    public SelectCellItem(String label, boolean required) {
	super(label);
	setRequired(required);
	setWidget(1, 0, borderPanel);
	// listBox.setStylePrimaryName("selectboxNormal");
	listBox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
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

    public void setSelectedIndex(String desc) {
	if (desc != null) {
	    for (int i = 0; i < listBox.getItemCount(); ++i) {
		if (desc.equals(listBox.getValue(i))) {
		    listBox.setSelectedIndex(i);
		}
	    }
	}
    }

    public void addChangeHandler(ChangeHandler handler) {
	listBox.addChangeHandler(handler);
    }

    public void addItem(String label, String value) {
	listBox.addItem(label, value);
    }

    @Override
    public boolean validate() {
	return validate(true);
    }

    public boolean validate(boolean displayError) {
	int selectedIndex = listBox.getSelectedIndex();
	String value = listBox.getValue(selectedIndex);
	if (isRequired() && value != null && value.equals("")) {
	    borderPanel.setStylePrimaryName("selectboxError");
	    if (displayError) {
		displayError("A value is required.");
	    }
	    return false;
	}

	if (isRequired() && value == null) {
	    borderPanel.setStylePrimaryName("selectboxError");
	    if (displayError) {
		displayError("A value is required.");
	    }
	    return false;
	}
	removeErrorMessage();
	borderPanel.removeStyleName("selectboxError");
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
	int x = listBox.getAbsoluteLeft();
	int y = listBox.getAbsoluteTop() + 15;
	PopupCellPanel.getInstance().setWidget(new Label(error));
	PopupCellPanel.getInstance().setPopupPosition(x, y);
	PopupCellPanel.getInstance().show();
    }

    protected void removeErrorMessage() {
	if (isCellPresent(2, 0)) {
	    clearCell(2, 0);
	}
    }

    public Map<String, Widget> getRowWidget() {
	return rowWidget;
    }

    public boolean empty() {
	return getValue() == null || "".equals(getValue());
    }
}
