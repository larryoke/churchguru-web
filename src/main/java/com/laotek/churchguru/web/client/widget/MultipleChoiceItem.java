package com.laotek.churchguru.web.client.widget;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

@Deprecated
public class MultipleChoiceItem extends Composite {
	private List<String> selectedValues = new ArrayList<String>();
	
	private FlexTable panel = new FlexTable();
	
//	public MultipleChoiceItem(){
//		panel.setBorderWidth(1);
//	}
	
	public MultipleChoiceItem(String... values){
		initWidget(panel);
		init(values);
	}

	public MultipleChoiceItem(final LinkedHashMap<String, Boolean> values) {
		initWidget(panel);
		init(values);
	}
	
	public void init(String... values){
		int index = 0;
		for(final String value : values){
			final CheckBox checkbox = new CheckBox();
			checkbox.setText(value);
			checkbox.setValue(false);
			panel.setWidget(index, 0, checkbox);
			panel.setWidget(index, 1, new HTML(value));
			index = index + 1;
			
			checkbox.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (checkbox.getValue()){
						if (!selectedValues.contains(value)){
							selectedValues.add(value);
						}
					}else{
						if (selectedValues.contains(value)){
							selectedValues.remove(value);
						}
					}			
				}
			});
		}
	}
	
	public void init(final LinkedHashMap<String, Boolean> values) {		
		int index = 0;
		for(final String key : values.keySet()){
			final CheckBox checkbox = new CheckBox();
			checkbox.setText(key);
			checkbox.setValue(values.get(key));
			panel.setWidget(index, 0, checkbox);
			panel.setWidget(index, 1, new HTML(key));
			index = index + 1;
			
			checkbox.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (checkbox.getValue()){
						if (!selectedValues.contains(key)){
							selectedValues.add(key);
						}
					}else{
						if (selectedValues.contains(key)){
							selectedValues.remove(key);
						}
					}			
				}
			});
		}
	}
	
	public List<String> getSelectedValues(){
		return selectedValues;
	}
}
