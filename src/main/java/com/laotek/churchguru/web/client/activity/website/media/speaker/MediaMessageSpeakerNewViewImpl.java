package com.laotek.churchguru.web.client.activity.website.media.speaker;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public class MediaMessageSpeakerNewViewImpl implements MediaMessageSpeakerNewView {

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
	// MainMenuContext.getInstance().showMessageMediaPanel("eStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public MediaMessageSpeakerDto add() {
	// TODO Auto-generated method stub
	return null;
    }

}
