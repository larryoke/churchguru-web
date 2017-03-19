package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainLayout extends Composite {

    interface MyUiBinder extends UiBinder<Widget, MainLayout> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    ScrollPanel mainScrollPanel;

    @UiField
    VerticalPanel mainVLayout;

    private HeaderLayout headerPanel = HeaderLayout.getInstance();

    public MainLayout(SimplePanel bodyPanel, ClientFactory clientFactory) {
	initWidget(uiBinder.createAndBindUi(this));

	headerPanel.buildFull();
	mainVLayout.add(headerPanel);
	mainVLayout.add(bodyPanel);
	mainVLayout.add(new HTML("&nbsp;"));
    }

    public void setHeaderUserName(String userName) {
	headerPanel.setUserName(userName);
    }

    public void setHeaderOrgName(String orgName) {
	headerPanel.setOrgName(orgName);
    }
}
