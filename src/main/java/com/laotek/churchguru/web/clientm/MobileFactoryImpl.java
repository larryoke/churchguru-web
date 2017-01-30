package com.laotek.churchguru.web.clientm;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsView;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsViewImpl;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookView;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookViewImpl;
import com.laotek.churchguru.web.clientm.activity.give.GiveView;
import com.laotek.churchguru.web.clientm.activity.give.GiveViewImpl;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomeView;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomeViewImpl;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventView;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventViewImpl;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventTitlesView;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventTitlesViewImpl;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskView;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskViewImpl;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestView;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestViewImpl;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterView;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterViewImpl;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionView;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionViewImpl;
import com.laotek.churchguru.web.clientm.activity.watch.WatchView;
import com.laotek.churchguru.web.clientm.activity.watch.WatchViewImpl;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlView;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlViewImpl;

public class MobileFactoryImpl implements MobileFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(
            eventBus);

    private MobileHomeView mobileHomeView = null;
    private GiveView giveView = null;
    private AboutUsView aboutUsView = null;
    private FacebookView facebookView = null;
    private PastorDeskView pastorDeskView = null;
    private NoticeAndEventTitlesView newsTitlesView = null;
    private SingleNoticeAndEventView singleMessageView = null;
    private WebsiteUrlView websiteUrlView = null;
    private PrayerRequestView prayerRequestView = null;
    private WatchView watchView = null;
    private UnderConstructionView underConstructionView = null;

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public MobileHomeView getMobileHomeView() {
        if (mobileHomeView == null) {
            mobileHomeView = new MobileHomeViewImpl();
        }
        return mobileHomeView;
    }

    @Override
    public GiveView getGiveView() {
        if (giveView == null) {
            giveView = new GiveViewImpl();
        }
        return giveView;
    }

    @Override
    public UnderConstructionView getUnderConstructionView() {
        if (underConstructionView == null) {
            underConstructionView = new UnderConstructionViewImpl();
        }
        return underConstructionView;
    }

    public AboutUsView getAboutUsView() {
        if (aboutUsView == null) {
            aboutUsView = new AboutUsViewImpl();
        }
        return aboutUsView;
    }

    @Override
    public TwitterView getTwitterView() {
        return new TwitterViewImpl();
    }

    @Override
    public FacebookView getFacebookView() {
        if (facebookView == null) {
            facebookView = new FacebookViewImpl();
        }
        return facebookView;
    }

    @Override
    public PastorDeskView getPastorDeskView() {
        if (pastorDeskView == null) {
            pastorDeskView = new PastorDeskViewImpl();
        }
        return pastorDeskView;
    }

    @Override
    public NoticeAndEventTitlesView getNewsTitlesView() {
        if (newsTitlesView == null) {
            newsTitlesView = new NoticeAndEventTitlesViewImpl();
        }
        return newsTitlesView;
    }

    @Override
    public SingleNoticeAndEventView getSingleMessageView() {
        if (singleMessageView == null) {
            singleMessageView = new SingleNoticeAndEventViewImpl();
        }
        return singleMessageView;
    }

    @Override
    public WebsiteUrlView getWebsiteUrlView() {
        if (websiteUrlView == null) {
            websiteUrlView = new WebsiteUrlViewImpl();
        }
        return websiteUrlView;
    }

    @Override
    public PrayerRequestView getPrayerRequestView() {
        if (prayerRequestView == null) {
            prayerRequestView = new PrayerRequestViewImpl();
        }
        return prayerRequestView;
    }

    @Override
    public WatchView getWatchView() {
        if (watchView == null) {
            watchView = new WatchViewImpl();
        }
        return watchView;
    }
}
