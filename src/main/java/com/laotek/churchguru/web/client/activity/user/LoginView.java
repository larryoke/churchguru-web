package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView extends IsWidget {
    void setPresenter(Presenter presenter);
    void init();
    
    public interface Presenter {
        void goTo(Place place);
    }
}