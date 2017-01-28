package com.laotek.churchguru.web.client.widget;

import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.web.shared.PhoneDto;

public class PhoneCellItem extends FormItem {
    protected TextBox textbox = new TextBox();
    private CountryCode currentCountryCode = null;
    private String errorMessage = null;

    private Image currentSelection = new Image(CountryCode.UK.getImageUrl());
    private Image dropDownArrow = new Image("images/app/drop_down.png");
    private Map<String, Widget> rowWidget = null;
    HorizontalPanel panel = new HorizontalPanel();

    public PhoneCellItem(String label, Map<String, Widget> rowWidget,
	    boolean required) {
	this.label = label;
	textbox.setStylePrimaryName("textboxBorderless");
	textbox.setWidth("150px");
	setRequired(required);
	initTextbox(label);

    }

    private void initTextbox(final String label) {
	final TextBox textbox = getTextbox();

	setWidget(0, 0, panel);
	panel.add(currentSelection);
	panel.add(dropDownArrow);
	panel.add(textbox);
	panel.setStylePrimaryName("phoneItemBorder");
	textbox.setStylePrimaryName("phoneTextboxBlur");

	setValue(new PhoneDto(CountryCode.UK, label));

	// populate the drop down
	createSelections();

	textbox.addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(FocusEvent event) {
		onTextboxFocus();
	    }
	});

	textbox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		onTextboxFocus();
	    }
	});

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		onTextboxBlur();
		validate();
	    }
	});

	textbox.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		if (errorMessage != null && !"".equals(errorMessage)) {
		    displayError(errorMessage);
		}
	    }
	});

	textbox.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		PopupCellPanel.getInstance().hide();
	    }
	});
    }

    public void setValue(String countryCodeStr, String number) {
	currentCountryCode = CountryCode.valueOf(countryCodeStr);
	currentSelection.setUrl(currentCountryCode.getImageUrl());
	textbox.setValue(number);
	updateTextboxStyle();
    }

    public void setValue(PhoneDto phoneDto) {
	setValue(phoneDto.getCountryCode().name(), phoneDto.getNumber());
    }

    private void updateTextboxStyle() {
	if (textbox.getValue() != null && !textbox.getValue().equals("")
		&& !textbox.getValue().equals(label)
		&& !textbox.getValue().equals(currentCountryCode.getCode())) {
	    textbox.setStylePrimaryName("phoneTextboxNormal");

	} else if (textbox.getValue() == null
		|| (textbox.getValue() != null && currentCountryCode != null && textbox
			.getValue().equals(currentCountryCode.getCode()))
		|| (textbox.getValue() != null && textbox.getValue().equals(""))) {
	    textbox.setStylePrimaryName("phoneTextboxBlur");
	}
    }

    public TextBox getTextbox() {
	return textbox;
    }

    private void doDropDownOnclick(final ScrollPanel scrollPanel,
	    final DecoratedPopupPanel dropDown, ClickEvent event) {
	Widget source = (Widget) event.getSource();
	int left = source.getAbsoluteLeft();
	int top = source.getAbsoluteTop() + 25;

	dropDown.setWidget(scrollPanel);
	dropDown.setPopupPosition(left, top);
	scrollPanel.setSize("200px", "100px");
	dropDown.setSize("200px", "100px");
	dropDown.show();
    }

    private void createSelections() {
	final VerticalPanel selectRows = new VerticalPanel();
	final ScrollPanel scrollPanel = new ScrollPanel(selectRows);
	final DecoratedPopupPanel dropDown = new DecoratedPopupPanel(true);
	currentSelection.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		doDropDownOnclick(scrollPanel, dropDown, event);
	    }
	});

	dropDownArrow.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		doDropDownOnclick(scrollPanel, dropDown, event);
	    }
	});

	for (final CountryCode code : CountryCode.values()) {
	    HorizontalPanel panel = new HorizontalPanel();
	    Image image = new Image(code.getImageUrl());
	    panel.add(image);
	    panel.add(new HTML("&nbsp;"));
	    HTML nameItem = new HTML(code.getName());
	    nameItem.setStylePrimaryName("handPointer");
	    panel.add(nameItem);
	    panel.add(new HTML("&nbsp;"));
	    panel.add(new HTML(code.getCode()));
	    image.setStylePrimaryName("handPointer");

	    image.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    Image image = (Image) event.getSource();
		    updateCountryCode(dropDown, code, image);
		    updatePhoneText();
		}
	    });
	    nameItem.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    HTML html = (HTML) event.getSource();
		    HorizontalPanel parent = (HorizontalPanel) html.getParent();

		    Image image = (Image) parent.getWidget(0);
		    updateCountryCode(dropDown, code, image);
		    updatePhoneText();
		}
	    });
	    selectRows.add(panel);
	}
    }

    public PhoneDto getValue() {
	return new PhoneDto(currentCountryCode, textbox.getValue());
    }

    public String getTextValue() {
	String value = textbox.getValue();
	if (label.equals(value)) {
	    return null;
	}
	return value;
    }

    private void updateCountryCode(final DecoratedPopupPanel dropDown,
	    final CountryCode code, final Image image) {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		currentSelection.setUrl(image.getUrl());
		currentCountryCode = code;
		dropDown.hide();
		setCurrentCountryCode(currentCountryCode);
	    }
	});
    }

    private void updatePhoneText() {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		String code = currentCountryCode.getCode();
		String text = textbox.getValue();
		textbox.setStylePrimaryName("phoneTextboxNormal");
		if (text == null || (text != null && text.equals(""))) {
		    textbox.setValue(code);

		} else if (!text.startsWith(code)) {
		    textbox.setValue(code);
		}
	    }
	});
    }

    public void setCurrentCountryCode(CountryCode currentCountryCode) {
	this.currentCountryCode = currentCountryCode;
    }

    @Override
    public boolean validate() {
	return validate(true);
    }

    public boolean validate(boolean displayError) {
	String value = textbox.getValue();

	if (isRequired()) {
	    if (value == null || "".equals(value)) {
		panel.addStyleName("phoneTextboxError");
		displayError("A value is required.");
		return false;
	    }
	    if (value != null) {
		if (value.equals(label)) {
		    panel.addStyleName("phoneTextboxError");
		    displayError("A value is required.");
		    return false;
		}
		if (currentCountryCode != null
			&& value.equals(currentCountryCode.getCode())) {
		    panel.addStyleName("phoneTextboxError");
		    displayError("A value is required.");
		    return false;
		}
	    }
	}

	if (value != null && currentCountryCode != null
		&& !value.equals(currentCountryCode.getCode())
		&& !value.equals(label)
		&& !value.matches(currentCountryCode.getRegex())) {
	    panel.addStyleName("phoneTextboxError");
	    displayError("Unknown number format");
	    return false;
	}
	removeErrorMessage();
	panel.removeStyleName("phoneTextboxError");
	return true;
    }

    @Override
    public void disable() {
	disableLabel();
	textbox.addStyleName("labelDisable");
	textbox.setEnabled(false);
    }

    @Override
    public void enable() {
	enableLabel();
	textbox.addStyleName("labelNormal");
	textbox.removeStyleName("labelDisable");
	textbox.setEnabled(true);
    }

    protected void displayError(String error) {
	this.errorMessage = error;
	int x = textbox.getAbsoluteLeft();
	int y = textbox.getAbsoluteTop() + 15;
	PopupCellPanel.getInstance().setWidget(new Label(error));
	PopupCellPanel.getInstance().setPopupPosition(x, y);
	PopupCellPanel.getInstance().show();
    }

    protected void removeErrorMessage() {
	this.errorMessage = null;
    }

    public boolean empty() {
	return getTextValue() == null
		|| "".equals(getTextValue())
		|| (currentCountryCode != null && currentCountryCode.getCode()
			.equals(getTextValue()));
    }

    private void onTextboxFocus() {
	if (textbox.getValue() != null && textbox.getValue().equals(label)) {
	    CountryCode countryCode = currentCountryCode;
	    setValue(new PhoneDto(countryCode, countryCode.getCode()));
	}
	textbox.setStylePrimaryName("phoneTextboxNormal");
    }

    private void onTextboxBlur() {
	if (textbox.getValue() == null
		|| (textbox.getValue() != null && currentCountryCode != null && textbox
			.getValue().equals(currentCountryCode.getCode()))
		|| (textbox.getValue() != null && textbox.getValue().equals(""))) {
	    textbox.setValue(label);
	    textbox.setStylePrimaryName("phoneTextboxBlur");
	}
    }
}
