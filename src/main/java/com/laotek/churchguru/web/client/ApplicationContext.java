package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.laotek.churchguru.web.shared.OrganisationDto;

public class ApplicationContext {

    private static ApplicationContext appContext = new ApplicationContext();

    private PlaceController placeController;

    private ScrollPanel mainScrollPanel;

    private OrganisationDto orgDto;

    private String adminEmail;

    private ApplicationContext() {

    }

    public static ApplicationContext getInstance() {
	return appContext;
    }

    public void initPlaceController(PlaceController placeController) {
	this.placeController = placeController;
    }

    public PlaceController getPlaceController() {
	return placeController;
    }

    public ScrollPanel getMainScrollPanel() {
	return mainScrollPanel;
    }

    public void setMainScrollPanel(ScrollPanel mainScrollPanel) {
	this.mainScrollPanel = mainScrollPanel;
    }

    public void scrollToTop() {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		mainScrollPanel.scrollToTop();
	    }
	});
    }

    public void scrollToBottom() {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		mainScrollPanel.scrollToBottom();
	    }
	});
    }

    public void scrollSlowlyToTop() {
	Timer scrollTimer = new Timer() {
	    @SuppressWarnings("unused")
	    public void run() {
		int currentVerticalScrollPosition = ApplicationContext
			.getInstance().getMainScrollPanel()
			.getVerticalScrollPosition();

		if (currentVerticalScrollPosition > 0) {

		    currentVerticalScrollPosition = ApplicationContext
			    .getInstance().getMainScrollPanel()
			    .getVerticalScrollPosition() - 15;

		    ApplicationContext
			    .getInstance()
			    .getMainScrollPanel()
			    .setVerticalScrollPosition(
				    currentVerticalScrollPosition);
		} else {
		    cancel();
		}
	    }
	};
	scrollTimer.scheduleRepeating(10);
    }

    public OrganisationDto getOrgDto() {
	return orgDto;
    }

    public void setOrgDto(OrganisationDto orgDto) {
	this.orgDto = orgDto;
    }

    public String getAdminEmail() {
	return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
	this.adminEmail = adminEmail;
    }

}
