package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public class ListeningSpeakerNewViewImpl implements ListeningSpeakerNewView {

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
	Window.setTitle("EStore New Speaker");
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showListeningPanel("eStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public ListeningSpeakerDto add() {
	// TODO Auto-generated method stub
	return null;
    }

}
