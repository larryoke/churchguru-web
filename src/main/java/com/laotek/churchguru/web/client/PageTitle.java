package com.laotek.churchguru.web.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class PageTitle extends Composite{
	public PageTitle(String title) {
		initWidget(
			new HTML(
				"<br/><div style=\"font-size: 2em;font-weight: bold;color: #777777;\">" 
				+ title 
				+ "</div><br/>"));
	}
}
