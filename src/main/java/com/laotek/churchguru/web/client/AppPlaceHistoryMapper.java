package com.laotek.churchguru.web.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.laotek.churchguru.web.client.activity.SystemSettingsPlace;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.dashboard.AccountQuotaPlace;
import com.laotek.churchguru.web.client.activity.dashboard.NewslettersChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartPlace;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchPlace;
import com.laotek.churchguru.web.client.activity.home.HomePlace;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessageNewPlace;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessagesPlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupPlace;
import com.laotek.churchguru.web.client.activity.user.SingleUserPlace;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.WebsitePlace;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersPlace;

@WithTokenizers({ HomePlace.Tokenizer.class, AccountQuotaPlace.Tokenizer.class, NewslettersChartPlace.Tokenizer.class,
	WeeklyAttendanceChartPlace.Tokenizer.class, PeopleChartPlace.Tokenizer.class, AllUserPlace.Tokenizer.class,
	SystemSettingsPlace.Tokenizer.class, SingleUserPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
	PasswordResetPlace.Tokenizer.class, NewUserSetupPlace.Tokenizer.class, UserProfilesPlace.Tokenizer.class,

	ListeningMessagesPlace.Tokenizer.class, ListeningMessageNewPlace.Tokenizer.class,
	ListeningGalleryPlace.Tokenizer.class, ListeningGalleryNewItemPlace.Tokenizer.class,
	ListeningCustomersPlace.Tokenizer.class, ListeningCustomerDetailsPlace.Tokenizer.class,
	ListeningCategoryNewPlace.Tokenizer.class, ListeningCategoriesPlace.Tokenizer.class,
	ListeningNotificationsPlace.Tokenizer.class, ListeningSpeakerNewPlace.Tokenizer.class,

	WatchingMessagesPlace.Tokenizer.class, WatchingMessageNewPlace.Tokenizer.class,

	ListeningSpeakersPlace.Tokenizer.class, WebsitePlace.Tokenizer.class, DonationSearchPlace.Tokenizer.class,
	GeneralChurchAppPlace.Tokenizer.class, PostNoticeOrEventChurchAppPlace.Tokenizer.class,
	PreviewNoticeOrEventChurchAppPlace.Tokenizer.class, GetCurrentNoticesAndEventsHistoryPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}