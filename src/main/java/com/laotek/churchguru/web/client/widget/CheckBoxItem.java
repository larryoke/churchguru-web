package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class CheckBoxItem extends Composite {
    private CheckBox checkbox = new CheckBox();
    private CheckBoxItemHandler checkBoxItemHandler;

    public CheckBoxItem() {
	checkbox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

	    @Override
	    public void onValueChange(ValueChangeEvent<Boolean> event) {
		if (checkBoxItemHandler != null) {
		    checkBoxItemHandler.addChangeHandler(checkbox.getValue());
		}
	    }
	});
    }

    public CheckBoxItem(String label, boolean alignRight) {
	FlexTable table = new FlexTable();
	initWidget(table);
	FlexCellFormatter fcf = table.getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	if (alignRight) {
	    fcf.setWidth(0, 0, "150px");
	} else {
	    fcf.setWidth(0, 0, "3px");
	}

	table.setWidget(0, 0, checkbox);

	checkbox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

	    @Override
	    public void onValueChange(ValueChangeEvent<Boolean> event) {
		if (checkBoxItemHandler != null) {
		    checkBoxItemHandler.addChangeHandler(checkbox.getValue());
		}
	    }
	});

	HTML labelWidget = new HTML(label);
	labelWidget.setStylePrimaryName("pointer");
	labelWidget.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		boolean value = !checkbox.getValue();
		checkbox.setValue(value);

		if (checkBoxItemHandler != null) {
		    checkBoxItemHandler.addChangeHandler(value);
		}
	    }
	});

	table.setWidget(0, 1, labelWidget);
	setStylePrimaryName("handPointer");

	table.setHTML(1, 0, "&nbsp;");
	table.setHTML(1, 1, "&nbsp;");
    }

    public void addValueChangeHandler(CheckBoxItemHandler handler) {
	this.checkBoxItemHandler = handler;
    }

    public CheckBox getCheckbox() {
	return checkbox;
    }

    public void disable() {
	checkbox.setEnabled(false);
    }

    public void enable() {
	checkbox.setEnabled(true);
    }

    public boolean isChecked() {
	return checkbox.getValue();
    }

    public void check() {
	checkbox.setValue(true);
    }
}
