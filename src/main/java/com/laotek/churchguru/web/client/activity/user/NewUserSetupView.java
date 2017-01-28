package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface NewUserSetupView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String passwordResetIdentifier);

    void initWidgets();

    Widget asErrorPage();

    public interface Presenter {
	void goTo(Place place);
    }
}
