package com.laotek.churchguru.web.clientm.activity.prayerrequest;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface PrayerRequestView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm();

    void disableAndAlert();

    public interface Presenter {
	void submit(String title, String forename, String surname,
		String mobile, String emailAddress, String message);
    }
}
