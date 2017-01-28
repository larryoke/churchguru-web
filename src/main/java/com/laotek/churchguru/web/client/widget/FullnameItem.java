package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.laotek.churchguru.model.shared.enums.Title;
import com.laotek.churchguru.web.shared.FullnameDto;

public class FullnameItem extends FormItem {

    private SelectItem title = new SelectItem();
    private TextItem forenames = new TextItem();
    private TextItem surname = new TextItem();

    public FullnameItem(boolean required) {
	super("Fullname");
	setRequired(required);
	init();
    }

    private void init() {
	setBorderWidth(0);
	setCellSpacing(0);
	setCellPadding(0);

	initTitle();

	initTextbox(forenames, "forenames", "170px");
	initTextbox(surname, "surname", "145px");

	FlowPanel flowPanel = new FlowPanel();
	flowPanel.add(title);
	title.setStylePrimaryName("flowPanel");
	flowPanel.add(forenames);
	forenames.setStylePrimaryName("flowPanel");
	flowPanel.add(surname);
	surname.setStylePrimaryName("flowPanel");
	setWidget(1, 0, flowPanel);
    }

    private void initTitle() {
	title.addItem("");
	title.addItem(Title.MISS.getDesc());
	title.addItem(Title.MR.getDesc());
	title.addItem(Title.MRS.getDesc());
    }

    @Override
    public boolean validate() {

	if (isRequired()) {
	    String mtitle = title.getValue();
	    String forenamesValue = forenames.getValue();
	    String surnameValue = surname.getValue();

	    boolean titleError = false;
	    boolean forenamesError = false;
	    boolean surnameError = false;
	    if ("".equals(mtitle)) {
		titleError = true;
	    }
	    if ("forenames".equals(forenamesValue)) {
		forenamesError = true;
	    }
	    if ("surname".equals(surnameValue)) {
		surnameError = true;
	    }

	    if (titleError || forenamesError || surnameError) {
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
	getFlexCellFormatter().setHorizontalAlignment(2, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

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

    private void initTextbox(TextItem textboxItem, final String label,
	    String width) {
	final TextBox textbox = textboxItem.getTextbox();

	textbox.setStylePrimaryName("fullNameTextboxInsideLabel");
	textbox.setWidth(width);
	textbox.setValue(label);

	textbox.addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(FocusEvent event) {
		if (textbox.getValue() != null
			&& textbox.getValue().equals(label)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("textboxNormal");
		}
	    }
	});

	textbox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (textbox.getValue() != null
			&& textbox.getValue().equals(label)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("textboxNormal");
		}
	    }
	});

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		if (textbox.getValue() == null
			|| (textbox.getValue() != null && textbox.getValue()
				.equals(""))) {
		    textbox.setValue(label);
		    textbox.setStylePrimaryName("fullNameTextboxInsideLabel");
		}
	    }
	});
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

    // public TextItem getForenames() {
    // return forenames;
    // }
    //
    // public void setForenames(TextItem forenames) {
    // this.forenames = forenames;
    // }
    //
    // public TextItem getSurname() {
    // return surname;
    // }
    //
    // public void setSurname(TextItem surname) {
    // this.surname = surname;
    // }
    //
    // public SelectItem getTitleItem() {
    // return title;
    // }
    //
    // public void setTitle(SelectItem title) {
    // this.title = title;
    // }

}
