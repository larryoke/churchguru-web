package com.laotek.churchguru.web.clientm.activity.message;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface MessageView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(MessageMobDto dto);

    void refresh();

    public interface Presenter {
    }
}
