package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;

public class MediaMessageCategoryNewViewImpl implements MediaMessageCategoryNewView {

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
	Window.setTitle("AudioMessage New Category");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("category");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public MediaMessageCategoryDto add() {
	MediaMessageCategoryDto categoryDto = new MediaMessageCategoryDto();
	return categoryDto;
    }

}
