package com.laotek.churchguru.web.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageLayout extends VerticalPanel {
	public PageLayout(String name){
		setSize("100%", "100%");
		add(new HTML("<center><h1>" + name +
				"</h1></center>"));
	}
}
