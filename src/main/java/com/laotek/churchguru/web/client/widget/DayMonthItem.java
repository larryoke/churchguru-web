package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class DayMonthItem extends FormItem {

    private ListBox dayListBox = new ListBox();
    private ListBox monthListBox = new ListBox();

    public DayMonthItem(String label, boolean required) {
	super(label);
	getFlexCellFormatter().setColSpan(0, 0, 2);

	setRequired(required);
	initDay();
	initMonth();

	setBorderWidth(0);
	setWidget(1, 0, dayListBox);
	setWidget(1, 1, monthListBox);

	dayListBox.setStylePrimaryName("selectboxNormal");
	monthListBox.setStylePrimaryName("selectboxNormal");
    }

    private void initDay() {
	dayListBox.addItem("");
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
		monthListBox.addStyleName("textboxError");
		return false;
	    }

	} else if ("Apr".equals(value) || "Jun".equals(value)
		|| "Sep".equals(value) || "Nov".equals(value)) {
	    selectedIndex = dayListBox.getSelectedIndex();
	    value = dayListBox.getValue(selectedIndex);
	    if ("31".equals(value)) {
		monthListBox.addStyleName("textboxError");
		return false;
	    }

	} else {
	    dayListBox.removeStyleName("textboxError");
	    monthListBox.removeStyleName("textboxError");
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
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	} else if ("31".equals(value)) {
	    selectedIndex = monthListBox.getSelectedIndex();
	    value = monthListBox.getValue(selectedIndex);
	    if ("Feb".equals(value)) {
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	    if ("Apr".equals(value)) {
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	    if ("Jun".equals(value)) {
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	    if ("Sep".equals(value)) {
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	    if ("Nov".equals(value)) {
		dayListBox.addStyleName("textboxError");
		return false;
	    }
	} else {
	    dayListBox.removeStyleName("textboxError");
	    monthListBox.removeStyleName("textboxError");
	}
	return true;
    }

    @Override
    boolean validate() {
	if (isRequired()) {
	    int selectedIndex = dayListBox.getSelectedIndex();
	    String day = dayListBox.getValue(selectedIndex);

	    selectedIndex = monthListBox.getSelectedIndex();
	    String month = monthListBox.getValue(selectedIndex);

	    boolean dayError = false;
	    boolean monthError = false;
	    if ("".equals(day)) {
		dayListBox.addStyleName("textboxError");
		dayError = true;
	    }
	    if ("".equals(month)) {
		monthListBox.addStyleName("textboxError");
		monthError = true;
	    }
	    if (dayError || monthError) {
		displayError("A value is required.");
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
	getFlexCellFormatter().setColSpan(2, 1, 2);

	HTML html = new HTML(error);
	html.setStylePrimaryName("errorMessage");
	setWidget(2, 1, html);
    }

    public String getDay() {
	int selectedIndex = dayListBox.getSelectedIndex();
	return dayListBox.getValue(selectedIndex);
    }

    public int getDayAsInt() {
	int selectedIndex = dayListBox.getSelectedIndex();
	return Integer.parseInt(dayListBox.getValue(selectedIndex));
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
}
