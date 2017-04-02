package com.laotek.churchguru.web.client.activity.website.media.notif;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.NotificationDto;

public class MediaMessageNotificationsViewImpl implements MediaMessageNotificationsView {

    private Presenter presenter;

    @Override
    public Widget asWidget() {
	return null;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("Media Message Notifications");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showWebsitePanel("notif");
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
