package com.laotek.churchguru.web.clientm;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsActivity;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsPlace;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookActivity;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookPlace;
import com.laotek.churchguru.web.clientm.activity.give.GiveActivity;
import com.laotek.churchguru.web.clientm.activity.give.GivePlace;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomeActivity;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventActivity;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventPlace;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventTitlesActivity;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventTitlesPlace;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskActivity;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskPlace;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestActivity;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestPlace;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterActivity;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterPlace;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionActivity;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionPlace;
import com.laotek.churchguru.web.clientm.activity.watch.WatchActivity;
import com.laotek.churchguru.web.clientm.activity.watch.WatchPlace;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlActivity;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlPlace;

public class MobileActivityMapper implements ActivityMapper {
    private MobileFactory clientFactory;

    public MobileActivityMapper(MobileFactory clientFactory) {
	super();
	this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
		if (place instanceof MobileHomePlace) {
			return new MobileHomeActivity((MobileHomePlace) place,
				clientFactory);
		}

		if (place instanceof GivePlace) {
			return new GiveActivity((GivePlace) place, clientFactory);
		}

		if (place instanceof PastorDeskPlace) {
			return new PastorDeskActivity(clientFactory);
		}

		if (place instanceof AboutUsPlace) {
			return new AboutUsActivity((AboutUsPlace) place, clientFactory);
		}

		if (place instanceof TwitterPlace) {
			return new TwitterActivity((TwitterPlace) place, clientFactory);
		}

		if (place instanceof FacebookPlace) {
			return new FacebookActivity((FacebookPlace) place, clientFactory);
		}

		if (place instanceof NoticeAndEventTitlesPlace) {
			return new NoticeAndEventTitlesActivity(
				(NoticeAndEventTitlesPlace) place, clientFactory);
		}

		if (place instanceof SingleNoticeAndEventPlace) {
			return new SingleNoticeAndEventActivity(
				(SingleNoticeAndEventPlace) place, clientFactory);
		}

		if (place instanceof WebsiteUrlPlace) {
			return new WebsiteUrlActivity(clientFactory);
		}

		if (place instanceof PrayerRequestPlace) {
			return new PrayerRequestActivity(clientFactory);
		}

		if (place instanceof WatchPlace) {
			return new WatchActivity(clientFactory);
		}

		if (place instanceof UnderConstructionPlace) {
			return new UnderConstructionActivity(clientFactory);
		}

		return null;
    }

}
