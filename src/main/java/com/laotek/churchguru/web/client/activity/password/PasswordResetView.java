package com.laotek.churchguru.web.client.activity.password;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface PasswordResetView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String username, String orgName, String passwordResetIdentifier);

    void initTab();

    void initWidgets();

    Widget asErrorPage();

    public interface Presenter {
	void goTo(Place place);
    }
}