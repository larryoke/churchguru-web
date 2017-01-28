package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public abstract class FormItem extends FlexTable {

    private boolean required;
    private FlexTable labelPanel = new FlexTable();

    private HTML labelHtml = new HTML();
    protected String label;

    public FormItem() {

    }

    public FormItem(String label) {
	setBorderWidth(0);
	this.label = label;
	labelHtml.setHTML(label + ":");
	labelPanel.setWidget(0, 0, labelHtml);
	FlexCellFormatter fcf = getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setWidth(0, 0, "150px");
	setWidget(0, 0, labelPanel);
    }

    public FormItem(String label, HorizontalAlignmentConstant alignment) {
	setBorderWidth(0);
	this.label = label;
	labelHtml.setHTML(label + ":");
	labelPanel.setWidget(0, 0, labelHtml);
	FlexCellFormatter fcf = getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, alignment);
	// fcf.setWidth(0, 0, "150px");
	setWidget(0, 0, labelPanel);
    }

    public FlexTable getLabelPanel() {
	return labelPanel;
    }

    public void setLabelPanel(FlexTable labelPanel) {
	this.labelPanel = labelPanel;
    }

    public boolean isRequired() {
	return required;
    }

    public void setRequired(boolean required) {
	this.required = required;
	if (required) {
	    labelPanel.setWidget(0, 1, new HTML("<b>*</b>"));
	}
    }

    // protected void displayError(String error) {
    // HTML html = new HTML(error);
    // html.setStylePrimaryName("errorMessage");
    // setWidget(2, 0, html);
    // }
    //
    // protected void removeErrorMessage() {
    // if (isCellPresent(2, 1)) {
    // clearCell(2, 1);
    // }
    // }

    abstract void displayError(String error);

    abstract void removeErrorMessage();

    protected void disableLabel() {
	labelHtml.setStylePrimaryName("labelDisable");
    }

    protected void enableLabel() {
	labelHtml.setStylePrimaryName("labelNormal");
    }

    public String getLabel() {
	return label;
    }

    abstract boolean validate();

    abstract void disable();

    abstract void enable();
}
