package com.laotek.churchguru.web.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.laotek.churchguru.web.client.activity.SystemSettingsPlace;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.dashboard.AccountQuotaPlace;
import com.laotek.churchguru.web.client.activity.dashboard.NewslettersChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartPlace;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchPlace;
import com.laotek.churchguru.web.client.activity.home.HomePlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupPlace;
import com.laotek.churchguru.web.client.activity.user.SingleUserPlace;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.WebsitePlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakersPlace;

@WithTokenizers({ HomePlace.Tokenizer.class, AccountQuotaPlace.Tokenizer.class, NewslettersChartPlace.Tokenizer.class,
	WeeklyAttendanceChartPlace.Tokenizer.class, PeopleChartPlace.Tokenizer.class, AllUserPlace.Tokenizer.class,
	SystemSettingsPlace.Tokenizer.class, SingleUserPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
	PasswordResetPlace.Tokenizer.class, NewUserSetupPlace.Tokenizer.class, UserProfilesPlace.Tokenizer.class,

	EStoreMessagesPlace.Tokenizer.class, EStoreMessageNewPlace.Tokenizer.class, EStoreGalleryPlace.Tokenizer.class,
	EStoreGalleryNewItemPlace.Tokenizer.class, EStoreCustomersPlace.Tokenizer.class,
	EStoreCustomerDetailsPlace.Tokenizer.class, EStoreCategoryNewPlace.Tokenizer.class,
	EStoreCategoriesPlace.Tokenizer.class, EStoreNotificationsPlace.Tokenizer.class,
	EStoreSpeakerNewPlace.Tokenizer.class, EStoreSpeakersPlace.Tokenizer.class, WebsitePlace.Tokenizer.class,
	DonationSearchPlace.Tokenizer.class, GeneralChurchAppPlace.Tokenizer.class,
	PostNoticeOrEventChurchAppPlace.Tokenizer.class, PreviewNoticeOrEventChurchAppPlace.Tokenizer.class,
	GetCurrentNoticesAndEventsHistoryPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}