package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface NewUserView extends IsWidget  {

    void setName(String adminPermName);
    void setPresenter(Presenter presenter);
    void initTab();

    public interface Presenter {
        void goTo(Place place);
    }
}
