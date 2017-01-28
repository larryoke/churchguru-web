package com.laotek.churchguru.web.clientm.activity.website;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface WebsiteUrlView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String url);

    public interface Presenter {
    }
}
