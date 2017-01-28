package com.laotek.churchguru.web.clientm.activity.facebook;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface FacebookView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    public interface Presenter {
    }
}
