package com.laotek.churchguru.web.clientm.activity.aboutus;

import java.math.BigDecimal;

import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface AboutUsView extends DetailView {

    void setPresenter(Presenter presenter);

    void showForm(String orgName, String aboutUs, String aboutPastor, String fullAddress, String serviceTimes,
	    String websiteUrl, boolean isFacebook, boolean isTwitter, boolean isYoutube, String googleApiKey,
	    final BigDecimal lati, final BigDecimal longi);

    void goTo(String approvalUrl);

    public interface Presenter {
    }
}
