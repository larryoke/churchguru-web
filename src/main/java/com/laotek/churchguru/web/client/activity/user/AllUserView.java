package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.UserDto;

public interface AllUserView extends IsWidget {
    void init();
    void setPresenter(Presenter presenter);
    void initUsers(List<UserDto> dtos);
    void initTab();

    public interface Presenter {
        void goTo(Place place);
    }
}