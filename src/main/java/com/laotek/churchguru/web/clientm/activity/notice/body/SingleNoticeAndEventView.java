package com.laotek.churchguru.web.clientm.activity.notice.body;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface SingleNoticeAndEventView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String title, String message, String eventDateAndTime,
	    String createdTimeDesc);

    public interface Presenter {
    }
}
