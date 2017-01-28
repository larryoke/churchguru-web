package com.laotek.churchguru.web.client.widget;

import java.util.Map;

import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DayMonthCellItem extends FormItem {

    private ListBox dayListBox = new ListBox();
    private ListBox monthListBox = new ListBox();

    private SimplePanel dayBorderPanel = new SimplePanel(dayListBox);
    private SimplePanel monthBorderPanel = new SimplePanel(monthListBox);

    private Map<String, Widget> rowWidget = null;

    public DayMonthCellItem(Map<String, Widget> rowWidget, boolean required) {

	setRequired(required);
	this.rowWidget = rowWidget;
	initDay();
	initMonth();

	setBorderWidth(0);
	setWidget(0, 0, dayBorderPanel);
	setWidget(0, 1, monthBorderPanel);
    }

    private void initDay() {
	dayListBox.addItem("");
	dayListBox.addItem("");

	SelectElement selectElement = SelectElement.as(dayListBox.getElement());
	NodeList<OptionElement> options = selectElement.getOptions();
	options.getItem(1).setLabel("BirthDay");
	options.getItem(1).setDisabled(true);

	dayListBox.addItem("1");
	dayListBox.addItem("2");
	dayListBox.addItem("3");
	dayListBox.addItem("4");
	dayListBox.addItem("5");
	dayListBox.addItem("6");
	dayListBox.addItem("7");
	dayListBox.addItem("8");
	dayListBox.addItem("9");
	dayListBox.addItem("10");
	dayListBox.addItem("11");
	dayListBox.addItem("12");
	dayListBox.addItem("13");
	dayListBox.addItem("14");
	dayListBox.addItem("15");
	dayListBox.addItem("16");
	dayListBox.addItem("17");
	dayListBox.addItem("18");
	dayListBox.addItem("19");
	dayListBox.addItem("20");
	dayListBox.addItem("21");
	dayListBox.addItem("22");
	dayListBox.addItem("23");
	dayListBox.addItem("24");
	dayListBox.addItem("25");
	dayListBox.addItem("26");
	dayListBox.addItem("27");
	dayListBox.addItem("28");
	dayListBox.addItem("29");
	dayListBox.addItem("30");
	dayListBox.addItem("31");
	dayListBox.addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		validateDateSelection();
	    }
	});
    }

    private void initMonth() {
	monthListBox.addItem("");
	monthListBox.addItem("");

	SelectElement selectElement = SelectElement.as(monthListBox.getElement());
	NodeList<OptionElement> options = selectElement.getOptions();
	options.getItem(1).setLabel("BirthMonth");
	options.getItem(1).setDisabled(true);
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
	monthListBox.addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		validateMonthSelection();
	    }
	});
    }

    public void addChangeHandler(ChangeHandler dayChangeHandler, ChangeHandler monthChangeHandler) {
	dayListBox.addChangeHandler(dayChangeHandler);
	monthListBox.addChangeHandler(monthChangeHandler);
    }

    public void initItem(int date, String month) {
	for (int i = 0; i < dayListBox.getItemCount(); ++i) {
	    String day = dayListBox.getValue(i);
	    if (day != null && !"".equals(day)) {
		if (day.equals(String.valueOf(date))) {
		    dayListBox.setSelectedIndex(i);
		}
	    }
	}

	for (int i = 0; i < monthListBox.getItemCount(); ++i) {
	    String monthStr = monthListBox.getValue(i);
	    if (monthStr != null && !"".equals(monthStr)) {
		if (monthStr.equals(month)) {
		    monthListBox.setSelectedIndex(i);
		}
	    }

	}
    }

    private boolean validateMonthSelection() {
	int selectedIndex = monthListBox.getSelectedIndex();
	String value = monthListBox.getValue(selectedIndex);
	if ("Feb".equals(value)) {
	    selectedIndex = dayListBox.getSelectedIndex();
	    value = dayListBox.getValue(selectedIndex);
	    if ("30".equals(value) || "31".equals(value)) {
		monthBorderPanel.addStyleName("selectboxError");
		return false;
	    }

	} else if ("Apr".equals(value) || "Jun".equals(value) || "Sep".equals(value) || "Nov".equals(value)) {
	    selectedIndex = dayListBox.getSelectedIndex();
	    value = dayListBox.getValue(selectedIndex);
	    if ("31".equals(value)) {
		monthBorderPanel.addStyleName("selectboxError");
		return false;
	    }

	} else {
	    dayListBox.removeStyleName("selectboxError");
	    monthBorderPanel.removeStyleName("selectboxError");
	}
	return true;
    }

    private boolean validateDateSelection() {
	int selectedIndex = dayListBox.getSelectedIndex();
	String value = dayListBox.getValue(selectedIndex);
	if ("30".equals(value)) {
	    selectedIndex = monthListBox.getSelectedIndex();
	    value = monthListBox.getValue(selectedIndex);
	    if ("Feb".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	} else if ("31".equals(value)) {
	    selectedIndex = monthListBox.getSelectedIndex();
	    value = monthListBox.getValue(selectedIndex);
	    if ("Feb".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	    if ("Apr".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	    if ("Jun".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	    if ("Sep".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	    if ("Nov".equals(value)) {
		dayBorderPanel.addStyleName("selectboxError");
		return false;
	    }
	} else {
	    dayBorderPanel.removeStyleName("selectboxError");
	    monthBorderPanel.removeStyleName("selectboxError");
	}
	return true;
    }

    @Override
    public boolean validate() {
	return validate(true);
    }

    public boolean validate(boolean displayError) {
	if (isRequired()) {
	    int selectedIndex = dayListBox.getSelectedIndex();
	    String day = dayListBox.getValue(selectedIndex);

	    selectedIndex = monthListBox.getSelectedIndex();
	    String month = monthListBox.getValue(selectedIndex);

	    boolean dayError = false;
	    boolean monthError = false;
	    if ("".equals(day) || null == day) {
		dayBorderPanel.addStyleName("selectboxError");
		dayError = true;
	    }
	    if ("".equals(month) || null == month) {
		monthListBox.addStyleName("selectboxError");
		monthError = true;
	    }
	    if (dayError || monthError) {
		if (displayError) {
		    displayError("A value is required.");
		}
		return false;
	    }
	}

	if (!validateMonthSelection()) {
	    return false;
	} else if (!validateDateSelection()) {
	    return false;
	}
	removeErrorMessage();
	return true;
    }

    protected void displayError(String error) {
	int x = dayListBox.getAbsoluteLeft();
	int y = dayListBox.getAbsoluteTop() + 15;
	PopupCellPanel.getInstance().setWidget(new Label(error));
	PopupCellPanel.getInstance().setPopupPosition(x, y);
	PopupCellPanel.getInstance().show();
    }

    public String getDay() {
	int selectedIndex = dayListBox.getSelectedIndex();
	return dayListBox.getValue(selectedIndex);
    }

    public String getMonth() {
	int selectedIndex = monthListBox.getSelectedIndex();
	return monthListBox.getValue(selectedIndex);
    }

    @Override
    void disable() {
	disableLabel();
	dayListBox.setEnabled(false);
	monthListBox.setEnabled(false);
    }

    @Override
    void enable() {
	enableLabel();
	dayListBox.setEnabled(true);
	monthListBox.setEnabled(true);
    }

    protected void removeErrorMessage() {
	if (isCellPresent(2, 1)) {
	    clearCell(2, 1);
	}
    }

    public boolean emptyDay() {
	String value = getDay();
	return value == null || "".equals(value);
    }

    public boolean emptyMonth() {
	String value = getMonth();
	return value == null || "".equals(value);
    }
}
