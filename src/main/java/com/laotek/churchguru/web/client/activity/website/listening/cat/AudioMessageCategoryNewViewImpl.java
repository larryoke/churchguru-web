package com.laotek.churchguru.web.client.activity.website.listening.cat;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

public class AudioMessageCategoryNewViewImpl implements AudioMessageCategoryNewView {

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
	MainMenuContext.getInstance().showMessageMediaPanel("category");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public AudioMessageCategoryDto add() {
	AudioMessageCategoryDto categoryDto = new AudioMessageCategoryDto();
	return categoryDto;
    }

}
