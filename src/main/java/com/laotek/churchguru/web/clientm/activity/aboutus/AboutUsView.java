package com.laotek.churchguru.web.clientm.activity.aboutus;

import java.math.BigDecimal;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface AboutUsView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String orgName, String aboutUs, String aboutPastor, String fullAddress, String serviceTimes,
	    String websiteUrl, String googleApiUrl, BigDecimal lati, BigDecimal longi);

    void goTo(String approvalUrl);

    public interface Presenter {
    }
}
