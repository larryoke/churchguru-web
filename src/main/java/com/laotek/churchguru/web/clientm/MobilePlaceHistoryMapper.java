package com.laotek.churchguru.web.clientm;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsPlace;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookPlace;
import com.laotek.churchguru.web.clientm.activity.give.GivePlace;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.activity.message.MessagePlace;
import com.laotek.churchguru.web.clientm.activity.message.category.MessageCategoryPlace;
import com.laotek.churchguru.web.clientm.activity.message.title.MessageTitlePlace;
import com.laotek.churchguru.web.clientm.activity.notice.body.SingleNoticeAndEventPlace;
import com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventTitlesPlace;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskPlace;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestPlace;
import com.laotek.churchguru.web.clientm.activity.privacypolicy.PrivacyPolicyPlace;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterPlace;
import com.laotek.churchguru.web.clientm.activity.underconstruction.UnderConstructionPlace;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlPlace;
import com.laotek.churchguru.web.clientm.activity.youtube.YoutubePlace;

@WithTokenizers({ AboutUsPlace.Tokenizer.class, MobileHomePlace.Tokenizer.class, GivePlace.Tokenizer.class,
		FacebookPlace.Tokenizer.class, TwitterPlace.Tokenizer.class, YoutubePlace.Tokenizer.class,
		PastorDeskPlace.Tokenizer.class, SingleNoticeAndEventPlace.Tokenizer.class, WebsiteUrlPlace.Tokenizer.class,

		MessageCategoryPlace.Tokenizer.class, MessagePlace.Tokenizer.class, MessageTitlePlace.Tokenizer.class,

		UnderConstructionPlace.Tokenizer.class, NoticeAndEventTitlesPlace.Tokenizer.class,
		PrayerRequestPlace.Tokenizer.class, PrivacyPolicyPlace.Tokenizer.class, })
public interface MobilePlaceHistoryMapper extends PlaceHistoryMapper {
}