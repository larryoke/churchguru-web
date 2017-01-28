package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.UserProfileDto;

public interface UserProfilesView extends IsWidget {

    void setName(String adminPermName);

    void setPresenter(Presenter presenter);

    void initUserProfiles(List<UserProfileDto> profiles);

    void initTab();

    public interface Presenter {
	void goTo(Place place);
    }
}
