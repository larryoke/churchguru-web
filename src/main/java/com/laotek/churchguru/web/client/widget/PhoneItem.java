package com.laotek.churchguru.web.client.widget;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.web.shared.PhoneDto;

public class PhoneItem extends FormItem {
    protected TextBox textbox = new TextBox();
    private CountryCode currentCountryCode = null;

    private Image currentSelection = new Image("");
    private Image dropDownArrow = new Image("images/app/drop_down.png");

    public PhoneItem(boolean required, PhoneDto phoneDto) {

	setRequired(required);
	setValue(phoneDto);
    }

    public PhoneItem(String label, boolean required) {
	super(label);

	textbox.setStylePrimaryName("textboxBorderless");
	textbox.setWidth("200px");

	setRequired(required);
	HorizontalPanel panel = new HorizontalPanel();
	setWidget(1, 0, panel);
	panel.add(currentSelection);
	panel.add(dropDownArrow);
	panel.add(textbox);

	panel.setStylePrimaryName("phoneItemBorder");

	setValue(new PhoneDto(CountryCode.UK, ""));
    }

    public void setValue(PhoneDto phoneDto) {
	currentCountryCode = phoneDto.getCountryCode();
	String number = phoneDto.getNumber();
	currentSelection.setUrl(currentCountryCode.getImageUrl());
	if (number == null || (number != null && number.equals(""))) {
	    textbox.setValue(currentCountryCode.getCode());
	} else {
	    textbox.setValue(number);
	}
	setCurrentCountryCode(currentCountryCode);

	createSelections();
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
    boolean validate() {
	String value = textbox.getValue();

	if (isRequired()) {
	    if (value == null || "".equals(value)) {
		textbox.addStyleName("textboxError");
		displayError("A value is required.");
		return false;
	    }
	    if (value != null) {
		if (value.equals(label)) {
		    textbox.addStyleName("textboxError");
		    displayError("A value is required.");
		    return false;
		}
		if (currentCountryCode != null
			&& value.equals(currentCountryCode.getCode())) {
		    textbox.addStyleName("textboxError");
		    displayError("A value is required.");
		    return false;
		}
	    }
	}

	if (value != null && currentCountryCode != null
		&& !value.equals(currentCountryCode.getCode())
		&& !value.equals(label)
		&& !value.matches(currentCountryCode.getRegex())) {
	    textbox.addStyleName("textboxError");
	    displayError("Unknown number format");
	    return false;
	}
	removeErrorMessage();
	textbox.removeStyleName("textboxError");
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
