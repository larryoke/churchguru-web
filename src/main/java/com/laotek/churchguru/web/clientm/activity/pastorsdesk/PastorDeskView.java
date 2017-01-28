package com.laotek.churchguru.web.clientm.activity.pastorsdesk;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface PastorDeskView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String message);

    public interface Presenter {
    }
}
