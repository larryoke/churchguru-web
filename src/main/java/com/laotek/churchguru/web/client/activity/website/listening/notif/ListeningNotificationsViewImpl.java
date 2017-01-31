package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.NotificationDto;

public class ListeningNotificationsViewImpl implements ListeningNotificationsView {

    private Presenter presenter;

    @Override
    public Widget asWidget() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("EStore Notifications");
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showWebsitePanel("EStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void init(List<NotificationDto> dto) {
	// TODO Auto-generated method stub

    }

}
