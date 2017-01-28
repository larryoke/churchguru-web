package com.laotek.churchguru.web.clientm.activity.watch;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface WatchView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    public interface Presenter {
    }
}
