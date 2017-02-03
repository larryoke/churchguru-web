package com.laotek.churchguru.web.clientm;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsView;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookView;
import com.laotek.churchguru.web.clientm.activity.give.GiveView;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomeView;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventView;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventTitlesView;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskView;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestView;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterView;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionView;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlView;
import com.laotek.churchguru.web.clientm.activity.youtube.YoutubeView;

public interface MobileFactory {
    EventBus getEventBus();

    PlaceController getPlaceController();

    MobileHomeView getMobileHomeView();

    PrayerRequestView getPrayerRequestView();

    UnderConstructionView getUnderConstructionView();

    SingleNoticeAndEventView getSingleMessageView();

    PastorDeskView getPastorDeskView();

    WebsiteUrlView getWebsiteUrlView();

    NoticeAndEventTitlesView getNewsTitlesView();

    GiveView getGiveView();

    AboutUsView getAboutUsView();

    TwitterView getTwitterView();

    FacebookView getFacebookView();

    YoutubeView getYoutubeView();

}
