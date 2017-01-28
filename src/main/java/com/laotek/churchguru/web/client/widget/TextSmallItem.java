package com.laotek.churchguru.web.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;


public class TextSmallItem extends TextItem{
	public TextSmallItem(final String label){
		super();
		setWidget(0, 0, textbox);
		textbox.setStylePrimaryName("textboxInsideLabel");
		textbox.setWidth("80px");
		textbox.setValue(label);
		
		textbox.addFocusHandler(new FocusHandler() {			
			@Override
			public void onFocus(FocusEvent event) {
				if (textbox.getValue() != null && textbox.getValue().equals(label)){
					textbox.setValue("");
					textbox.setStylePrimaryName("textboxNormal");					
				}
			}
		});
		
		textbox.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (textbox.getValue() != null && textbox.getValue().equals(label)){
					textbox.setValue("");
					textbox.setStylePrimaryName("textboxNormal");					
				}
			}
		});
		
		textbox.addBlurHandler(new BlurHandler() {			
			@Override
			public void onBlur(BlurEvent event) {
				if (textbox.getValue() == null || (textbox.getValue() != null && textbox.getValue().equals(""))){
					textbox.setValue(label);
					textbox.setStylePrimaryName("textboxInsideLabel");					
				}
			}
		});
	}
}
