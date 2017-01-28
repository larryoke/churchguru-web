package com.laotek.churchguru.web.clientm.activity.twitter;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface TwitterView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    void stop();

    public interface Presenter {
    }
}
