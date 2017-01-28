package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface WeeklyAttendanceChartView extends IsWidget {
    void setPresenter(Presenter presenter);
    void init();
    void initTab();
    void initWidgets();
    
    public interface Presenter {
        void goTo(Place place);
    }
}