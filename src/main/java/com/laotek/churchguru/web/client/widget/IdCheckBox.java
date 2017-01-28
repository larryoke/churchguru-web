package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.CheckBox;

public class IdCheckBox extends CheckBox{

	private String identifier;

	public IdCheckBox(String identifier, boolean value) {
		this.identifier = identifier;
		setValue(value);
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
