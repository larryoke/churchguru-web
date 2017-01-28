package com.laotek.churchguru.web.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface SystemSettingsView extends IsWidget  {

    void setPresenter(Presenter presenter);
    void initTab();
    void init();

    public interface Presenter {
        void goTo(Place place);
    }
}
