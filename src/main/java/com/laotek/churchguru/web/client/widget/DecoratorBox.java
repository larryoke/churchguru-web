package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DecoratorBox extends Composite{
	public DecoratorBox(String title, Widget widget){
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(new HTML("<h3>" + title + "</h3>"));
		vPanel.add(widget);
		
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.add(vPanel);
		this.initWidget(decPanel);
	}
}
