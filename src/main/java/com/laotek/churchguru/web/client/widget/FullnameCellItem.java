package com.laotek.churchguru.web.client.widget;

import java.util.Map;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.Title;
import com.laotek.churchguru.web.shared.FullnameDto;

public class FullnameCellItem extends FormItem {

    private SelectCellItem title = null;
    private TextCellItem forenames = null;
    private TextCellItem surname = null;
    private Map<String, Widget> rowWidget = null;

    public FullnameCellItem(boolean required, Map<String, Widget> rowWidget) {
	this.rowWidget = rowWidget;
	title = new SelectCellItem("Title", rowWidget, true);
	forenames = new TextCellItem("forenames", rowWidget, true, "120px");
	surname = new TextCellItem("surname", rowWidget, true, "120px");
	getFlexCellFormatter().setColSpan(0, 0, 3);
	setRequired(required);
	init();
    }

    public void initSurname(String surname) {
	this.surname.getTextbox().setValue(surname);
	if (surname != null && !surname.equals("") && !surname.equals("surname")) {
	    this.surname.getTextbox().setStylePrimaryName("textboxNormal");
	}
    }

    public void initTitle(String title) {
	for (int i = 0; i < this.title.getListBox().getItemCount(); ++i) {
	    if (title.equals(this.title.getListBox().getItemText(i))) {
		this.title.getListBox().setSelectedIndex(i);
	    }
	}
    }

    public void initForenames(String forenames) {
	this.forenames.getTextbox().setValue(forenames);
	if (forenames != null && !forenames.equals("") && !forenames.equals("forenames")) {
	    this.forenames.getTextbox().setStylePrimaryName("textboxNormal");
	}
    }

    private void init() {
	setBorderWidth(0);
	setCellSpacing(0);
	setCellPadding(0);

	initTitle();

	getFlexCellFormatter().setColSpan(0, 0, 3);
	setWidget(1, 0, title);
	setWidget(1, 1, forenames);
	setWidget(1, 2, surname);
    }

    private void initTitle() {
	title.addItem(Title.MISS.getDesc());
	title.addItem(Title.MR.getDesc());
	title.addItem(Title.MRS.getDesc());
    }

    @Override
    public boolean validate() {
	return validate(true);
    }

    public boolean validate(boolean displayError) {

	if (isRequired()) {
	    String mtitle = title.getValue();
	    String forenamesValue = forenames.getValue();
	    String surnameValue = surname.getValue();

	    boolean titleError = false;
	    boolean forenamesError = false;
	    boolean surnameError = false;
	    if ("".equals(mtitle) || mtitle == null) {
		titleError = true;
	    }
	    if ("".equals(forenamesValue) || forenamesValue == null) {
		forenamesError = true;
	    }
	    if ("".equals(surnameValue) || surnameValue == null) {
		surnameError = true;
	    }

	    if (titleError || forenamesError || surnameError) {
		if (displayError)
		    displayError("Fullname is required.");
		return false;
	    }
	}
	removeErrorMessage();
	return true;
    }

    @Override
    protected void removeErrorMessage() {
	if (isCellPresent(2, 0)) {
	    clearCell(2, 0);
	}
    }

    protected void displayError(String error) {
	getFlexCellFormatter().setColSpan(2, 0, 3);
	getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);

	HTML html = new HTML(error);
	html.setStylePrimaryName("errorMessage");
	setWidget(2, 0, html);
    }

    @Override
    void disable() {
	title.disable();
	forenames.disable();
	surname.disable();
    }

    @Override
    void enable() {
	title.enable();
	forenames.enable();
	surname.enable();
    }

    public FullnameDto getFullnameDto() {
	FullnameDto dto = new FullnameDto();
	dto.setForenames(forenames.getValue());
	dto.setSurname(surname.getValue());
	dto.setTitle(Title.find(title.getValue()));
	return dto;
    }

    public void setFullnameDto(FullnameDto dto) {
	for (int i = 0; i < title.getListBox().getItemCount(); ++i) {
	    if (title.getValue(i).equals(dto.getTitle().getDesc())) {
		title.getListBox().setSelectedIndex(i);
	    }
	}
	forenames.setValue(dto.getForenames());
	forenames.getTextbox().setStylePrimaryName("textboxNormal");

	surname.setValue(dto.getSurname());
	surname.getTextbox().setStylePrimaryName("textboxNormal");

    }

    public Map<String, Widget> getRowWidget() {
	return rowWidget;
    }

    public SelectCellItem getTitleCell() {
	return title;
    }

    public TextCellItem getForenames() {
	return forenames;
    }

    public TextCellItem getSurname() {
	return surname;
    }

    public boolean empty() {
	return title.empty() && forenames.empty() && surname.empty();
    }
}
