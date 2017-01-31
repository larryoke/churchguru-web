package com.laotek.churchguru.web.client.activity.website.listening.speaker;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

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
	MainMenuContext.getInstance().showMessageMediaPanel("eStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public AudioMessageSpeakerDto add() {
	// TODO Auto-generated method stub
	return null;
    }

}
