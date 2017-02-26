package com.laotek.churchguru.web.client.widget;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.SystemSettingsPlace;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;

public class MainTabBar extends FlexTable {

    public MainTabBar() {
	setStylePrimaryName("mainTabBar");
	setWidth("900px");
	setHeight("40px");

	int column = 0;
	setWidget(0, column++, new HTML("&nbsp;"));

	Anchor anchor = new Anchor("Dashboard");
	anchor.setStylePrimaryName("mainTabBarAnchor");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {

		ApplicationContext.getInstance().getPlaceController().goTo(new GeneralChurchAppPlace("churchApp"));
	    }
	});
	setWidget(0, column++, anchor);

	anchor = new Anchor("Manage Users");
	anchor.setStylePrimaryName("mainTabBarAnchor");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {

		ApplicationContext.getInstance().getPlaceController().goTo(new AllUserPlace("allUser"));
	    }
	});
	setWidget(0, column++, anchor);

	anchor = new Anchor("System Settings");
	anchor.setStylePrimaryName("mainTabBarAnchor");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(new SystemSettingsPlace("systemSettings"));

	    }
	});
	setWidget(0, column++, anchor);

	setWidget(0, column++, new HTML("&nbsp;"));
    }

    public void hightlightSelected(final String selected) {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		for (int i = 0; i < 10; ++i) {
		    Widget current = getWidget(0, i);
		    if (current instanceof Anchor) {
			if (((Anchor) current).getText().equals(selected)) {
			    ((Anchor) current).addStyleName("boldMainTabBarAnchor");
			} else {
			    ((Anchor) current).removeStyleName("boldMainTabBarAnchor");
			}
		    }
		}
	    }
	});

    }

}
