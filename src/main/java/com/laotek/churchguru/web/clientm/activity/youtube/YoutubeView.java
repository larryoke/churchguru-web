package com.laotek.churchguru.web.clientm.activity.youtube;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface YoutubeView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    public interface Presenter {
    }
}
