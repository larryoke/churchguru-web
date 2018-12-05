package com.laotek.churchguru.web.clientm.activity.privacypolicy;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface PrivacyPolicyView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    public interface Presenter {
    }
}
