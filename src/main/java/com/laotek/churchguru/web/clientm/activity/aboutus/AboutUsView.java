package com.laotek.churchguru.web.clientm.activity.aboutus;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface AboutUsView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String orgName, String aboutUs, String aboutPastor,
	    String fullAddress, String serviceTimes, String websiteUrl,
	    String googleApiUrl);

    void goTo(String approvalUrl);

    public interface Presenter {
    }
}
