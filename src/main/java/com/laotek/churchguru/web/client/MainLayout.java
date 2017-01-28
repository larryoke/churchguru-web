package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
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

    @UiField
    FlexTable headerLayout;

    @UiField
    StackLayoutPanel stackMenu;

    @UiField
    DockLayoutPanel dockLayoutPanel;

    private HeaderLayout headerPanel = HeaderLayout.getInstance();

    public MainLayout(SimplePanel bodyPanel, ClientFactory clientFactory) {
	initWidget(uiBinder.createAndBindUi(this));

	headerLayout.setWidget(0, 0, headerPanel);
	headerLayout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	this.stackMenu.setWidth("100%");
	MainMenuContext.getInstance().initStackMenu(dockLayoutPanel, stackMenu);

	if (Window.getClientWidth() < 600) {
	    HeaderLayout.getInstance().buildMinimal();
	    MainMenuContext.getInstance().hideMenu();

	} else if (Window.getClientWidth() < 800) {
	    HeaderLayout.getInstance().buildNearFull();
	    MainMenuContext.getInstance().hideMenu();

	} else {
	    HeaderLayout.getInstance().buildFull();
	    MainMenuContext.getInstance().showMenu();
	}

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
