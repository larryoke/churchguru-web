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
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewPlace;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosPlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupPlace;
import com.laotek.churchguru.web.client.activity.user.SingleUserPlace;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.WebsitePlace;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingPlace;
import com.laotek.churchguru.web.client.activity.website.media.notif.MediaMessageNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.media.play.MediaMessagePlayPlace;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakersPlace;

@WithTokenizers({ AccountQuotaPlace.Tokenizer.class, NewslettersChartPlace.Tokenizer.class,
	WeeklyAttendanceChartPlace.Tokenizer.class, PeopleChartPlace.Tokenizer.class, AllUserPlace.Tokenizer.class,
	SystemSettingsPlace.Tokenizer.class, SingleUserPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
	PasswordResetPlace.Tokenizer.class, NewUserSetupPlace.Tokenizer.class, UserProfilesPlace.Tokenizer.class,

	MediaMessagesPlace.Tokenizer.class, MediaMessageNewPlace.Tokenizer.class,
	MediaMessageGalleryPlace.Tokenizer.class, MediaMessageGalleryNewItemPlace.Tokenizer.class,
	MediaMessageCustomersPlace.Tokenizer.class, MediaMessageCustomerDetailsPlace.Tokenizer.class,
	MediaMessageCategoryNewPlace.Tokenizer.class, MediaMessageCategoriesPlace.Tokenizer.class,
	MediaMessageNotificationsPlace.Tokenizer.class, MediaMessageSpeakerNewPlace.Tokenizer.class,
	MediaMessagesLoadingPlace.Tokenizer.class, MediaMessagePlayPlace.Tokenizer.class,

	YoutubeVideosPlace.Tokenizer.class, YoutubeVideoNewPlace.Tokenizer.class,

	MediaMessageSpeakersPlace.Tokenizer.class, WebsitePlace.Tokenizer.class, DonationSearchPlace.Tokenizer.class,
	GeneralChurchAppPlace.Tokenizer.class, PostNoticeOrEventChurchAppPlace.Tokenizer.class,
	PreviewNoticeOrEventChurchAppPlace.Tokenizer.class, GetCurrentNoticesAndEventsHistoryPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}