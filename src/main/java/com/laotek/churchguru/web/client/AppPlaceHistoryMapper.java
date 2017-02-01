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
import com.laotek.churchguru.web.client.activity.media.watching.VideoMessageNewPlace;
import com.laotek.churchguru.web.client.activity.media.watching.VideoMessagesPlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupPlace;
import com.laotek.churchguru.web.client.activity.user.SingleUserPlace;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.WebsitePlace;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.audio.notif.AudioMessageNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakersPlace;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryPlace;

@WithTokenizers({ HomePlace.Tokenizer.class, AccountQuotaPlace.Tokenizer.class, NewslettersChartPlace.Tokenizer.class,
	WeeklyAttendanceChartPlace.Tokenizer.class, PeopleChartPlace.Tokenizer.class, AllUserPlace.Tokenizer.class,
	SystemSettingsPlace.Tokenizer.class, SingleUserPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
	PasswordResetPlace.Tokenizer.class, NewUserSetupPlace.Tokenizer.class, UserProfilesPlace.Tokenizer.class,

	AudioMessagesPlace.Tokenizer.class, AudioMessageNewPlace.Tokenizer.class,
	AudioMessageGalleryPlace.Tokenizer.class, AudioMessageGalleryNewItemPlace.Tokenizer.class,
	AudioMessageCustomersPlace.Tokenizer.class, AudioMessageCustomerDetailsPlace.Tokenizer.class,
	AudioMessageCategoryNewPlace.Tokenizer.class, AudioMessageCategoriesPlace.Tokenizer.class,
	AudioMessageNotificationsPlace.Tokenizer.class, AudioMessageSpeakerNewPlace.Tokenizer.class,

	VideoMessagesPlace.Tokenizer.class, VideoMessageNewPlace.Tokenizer.class,

	AudioMessageSpeakersPlace.Tokenizer.class, WebsitePlace.Tokenizer.class, DonationSearchPlace.Tokenizer.class,
	GeneralChurchAppPlace.Tokenizer.class, PostNoticeOrEventChurchAppPlace.Tokenizer.class,
	PreviewNoticeOrEventChurchAppPlace.Tokenizer.class, GetCurrentNoticesAndEventsHistoryPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}