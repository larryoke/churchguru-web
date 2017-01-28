package com.laotek.churchguru.web.clientm.activity.home;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.laotek.churchguru.web.clientm.MobileFactory;

public interface MobileHomeView extends IsWidget {
    void setPresenter(Presenter presenter);

    void setTitle(String text);

    HasTapHandlers getAboutButton();

    void setTopics(List<Topic> createTopicsList, int width,
	    MobileFactory clientFactory);

    void onLoad();

    void displayLoadingProgress();

    void onFinishLoad();

    public interface Presenter {
	void goTo(Place place);

	void goToNoticesIf();
    }

}
