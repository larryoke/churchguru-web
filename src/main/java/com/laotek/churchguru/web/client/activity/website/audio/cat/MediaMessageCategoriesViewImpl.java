package com.laotek.churchguru.web.client.activity.website.audio.cat;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

public class MediaMessageCategoriesViewImpl implements MediaMessageCategoriesView {

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
	Window.setTitle("Listen Categories");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("listening");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void initCategories(List<AudioMessageCategoryDto> dtos) {
	// TODO Auto-generated method stub

    }

}
