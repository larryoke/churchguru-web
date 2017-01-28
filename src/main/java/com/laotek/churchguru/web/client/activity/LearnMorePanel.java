package com.laotek.churchguru.web.client.activity;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;

public class LearnMorePanel extends Composite{
	private DisclosurePanel disclosurePanel = new DisclosurePanel();

	public LearnMorePanel() {
		initWidget(disclosurePanel);
		disclosurePanel.setHeader(new HTML("Learn more"));
		disclosurePanel.setAnimationEnabled(true);
		disclosurePanel.setStylePrimaryName("disclosurePanel");
	}

	public LearnMorePanel(HTML content) {
		this();
		disclosurePanel.setContent(content);
	}
	
	public void setContent(HTML content){
		disclosurePanel.setContent(content);
	}
}
