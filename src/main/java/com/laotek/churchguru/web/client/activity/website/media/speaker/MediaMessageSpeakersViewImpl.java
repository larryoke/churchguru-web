package com.laotek.churchguru.web.client.activity.website.media.speaker;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public class MediaMessageSpeakersViewImpl implements MediaMessageSpeakersView {

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
	Window.setTitle("EStore Speakers");
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
    public void init(MediaMessageSpeakerDto dto) {
	// TODO Auto-generated method stub

    }

}
