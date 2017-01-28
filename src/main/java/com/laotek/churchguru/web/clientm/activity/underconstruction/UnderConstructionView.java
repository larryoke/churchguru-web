package com.laotek.churchguru.web.clientm.activity.underconstruction;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface UnderConstructionView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    public interface Presenter {
    }
}
