package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public class PostcodeItem extends TextItem {

    public PostcodeItem() {
	super();
	setWidget(0, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	textbox.setWidth("100px");

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		validate();
	    }
	});
    }

    public PostcodeItem(String label, boolean required) {
	super(label, required);
	setRequired(required);
	setWidget(1, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	textbox.setWidth("100px");
    }

    public PostcodeItem(String label, boolean required,
	    HorizontalAlignmentConstant alignmentConstant) {
	super(label, required, alignmentConstant);
	setRequired(required);
	setWidget(1, 0, textbox);
	textbox.setStylePrimaryName("textboxNormal");
	textbox.setWidth("100px");
    }
}
