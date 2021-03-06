package com.laotek.churchguru.web.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.laotek.churchguru.web.client.activity.LoginActivity;
import com.laotek.churchguru.web.client.activity.SystemSettingsActivity;
import com.laotek.churchguru.web.client.activity.SystemSettingsPlace;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryActivity;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.dashboard.NewsLettersChartActivity;
import com.laotek.churchguru.web.client.activity.dashboard.NewslettersChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartActivity;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartPlace;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartActivity;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartPlace;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchActivity;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchPlace;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewActivity;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewPlace;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosActivity;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosPlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetActivity;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserActivity;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserSetuptActivity;
import com.laotek.churchguru.web.client.activity.user.SingleUserActivity;
import com.laotek.churchguru.web.client.activity.user.SingleUserPlace;
import com.laotek.churchguru.web.client.activity.user.UserProfilesActivity;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.WebsiteActivity;
import com.laotek.churchguru.web.client.activity.website.WebsitePlace;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryActivity;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryNewItemActivity;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewActivity;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessagesActivity;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoriesActivity;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoryNewActivity;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomerDetailsActivity;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomersActivity;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingActivity;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingPlace;
import com.laotek.churchguru.web.client.activity.website.media.notif.MediaMessageNotificationsActivity;
import com.laotek.churchguru.web.client.activity.website.media.notif.MediaMessageNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.media.play.MediaMessagePlayActivity;
import com.laotek.churchguru.web.client.activity.website.media.play.MediaMessagePlayPlace;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakerNewActivity;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakersActivity;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakersPlace;

public class AppActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof PeopleChartPlace)
			return new PeopleChartActivity((PeopleChartPlace) place, clientFactory);

		else if (place instanceof NewslettersChartPlace)
			return new NewsLettersChartActivity((NewslettersChartPlace) place, clientFactory);

		else if (place instanceof WeeklyAttendanceChartPlace)
			return new WeeklyAttendanceChartActivity((WeeklyAttendanceChartPlace) place, clientFactory);

		else if (place instanceof AllUserPlace)
			return new AllUserActivity((AllUserPlace) place, clientFactory);

		else if (place instanceof SystemSettingsPlace)
			return new SystemSettingsActivity((SystemSettingsPlace) place, clientFactory);

		else if (place instanceof SingleUserPlace)
			return new SingleUserActivity((SingleUserPlace) place, clientFactory);

		else if (place instanceof LoginPlace)
			return new LoginActivity((LoginPlace) place, clientFactory);

		else if (place instanceof PasswordResetPlace)
			return new PasswordResetActivity((PasswordResetPlace) place, clientFactory);

		else if (place instanceof NewUserSetupPlace)
			return new NewUserSetuptActivity((NewUserSetupPlace) place, clientFactory);

		else if (place instanceof UserProfilesPlace)
			return new UserProfilesActivity((UserProfilesPlace) place, clientFactory);

		else if (place instanceof MediaMessageSpeakerNewPlace)
			return new MediaMessageSpeakerNewActivity((MediaMessageSpeakerNewPlace) place, clientFactory);

		else if (place instanceof MediaMessageCustomersPlace)
			return new MediaMessageCustomersActivity((MediaMessageCustomersPlace) place, clientFactory);

		else if (place instanceof MediaMessageCustomerDetailsPlace)
			return new MediaMessageCustomerDetailsActivity((MediaMessageCustomerDetailsPlace) place, clientFactory);

		else if (place instanceof MediaMessageSpeakersPlace)
			return new MediaMessageSpeakersActivity((MediaMessageSpeakersPlace) place, clientFactory);

		else if (place instanceof MediaMessageCategoryNewPlace)
			return new MediaMessageCategoryNewActivity((MediaMessageCategoryNewPlace) place, clientFactory);

		else if (place instanceof MediaMessagesPlace)
			return new MediaMessagesActivity((MediaMessagesPlace) place, clientFactory);

		else if (place instanceof MediaMessageCategoriesPlace)
			return new MediaMessageCategoriesActivity((MediaMessageCategoriesPlace) place, clientFactory);

		else if (place instanceof MediaMessageNotificationsPlace)
			return new MediaMessageNotificationsActivity((MediaMessageNotificationsPlace) place, clientFactory);

		else if (place instanceof MediaMessageNewPlace)
			return new MediaMessageNewActivity((MediaMessageNewPlace) place, clientFactory);

		else if (place instanceof MediaMessagesLoadingPlace)
			return new MediaMessagesLoadingActivity((MediaMessagesLoadingPlace) place, clientFactory);

		else if (place instanceof MediaMessagePlayPlace)
			return new MediaMessagePlayActivity((MediaMessagePlayPlace) place, clientFactory);

		else if (place instanceof MediaMessageGalleryPlace)
			return new MediaMessageGalleryActivity((MediaMessageGalleryPlace) place, clientFactory);

		else if (place instanceof MediaMessageGalleryNewItemPlace)
			return new MediaMessageGalleryNewItemActivity((MediaMessageGalleryNewItemPlace) place, clientFactory);

		else if (place instanceof YoutubeVideoNewPlace)
			return new YoutubeVideoNewActivity((YoutubeVideoNewPlace) place, clientFactory);

		else if (place instanceof YoutubeVideosPlace)
			return new YoutubeVideosActivity((YoutubeVideosPlace) place, clientFactory);

		else if (place instanceof WebsitePlace)
			return new WebsiteActivity((WebsitePlace) place, clientFactory);

		else if (place instanceof DonationSearchPlace)
			return new DonationSearchActivity((DonationSearchPlace) place, clientFactory);

		else if (place instanceof GeneralChurchAppPlace)
			return new GeneralChurchAppActivity((GeneralChurchAppPlace) place, clientFactory);

		else if (place instanceof GetCurrentNoticesAndEventsHistoryPlace)
			return new GetCurrentNoticesAndEventsHistoryActivity((GetCurrentNoticesAndEventsHistoryPlace) place,
					clientFactory);

		else if (place instanceof PostNoticeOrEventChurchAppPlace)
			return new PostNoticeOrEventChurchAppActivity((PostNoticeOrEventChurchAppPlace) place, clientFactory);

		else if (place instanceof PreviewNoticeOrEventChurchAppPlace)
			return new PreviewNoticeOrEventChurchAppActivity((PreviewNoticeOrEventChurchAppPlace) place, clientFactory);

		// else if (place instanceof SignUpAccountPlace)
		// return new SignUpAccountActivity((SignUpAccountPlace) place,
		// clientFactory);
		//
		// else if (place instanceof ConfirmNewOrgAccountPlace)
		// return new ConfirmNewOrgAccountActivity(
		// (ConfirmNewOrgAccountPlace) place, clientFactory);
		//
		// else if (place instanceof BrowseTextMessagesPlace)
		// return new BrowseTextMessagesActivity(
		// (BrowseTextMessagesPlace) place, clientFactory);
		//
		// else if (place instanceof NotificationPlace)
		// return new NotificationActivity((NotificationPlace) place,
		// clientFactory);
		//
		// else if (place instanceof BrowseGuestSearchResultsPlace)
		// return new BrowseGuestSearchResultsActivity(
		// (BrowseGuestSearchResultsPlace) place, clientFactory);
		//
		// else if (place instanceof GuestPlace)
		// return new ManageGuestActivity((GuestPlace) place, clientFactory);
		//
		// else if (place instanceof AddMemberPlace)
		// return new AddMemberActivity((AddMemberPlace) place, clientFactory);
		//
		// else if (place instanceof AddGuestPlace)
		// return new AddGuestActivity((AddGuestPlace) place, clientFactory);
		//
		// else if (place instanceof SingleGuestPlace)
		// return new SingleGuestActivity((SingleGuestPlace) place,
		// clientFactory);
		//
		// else if (place instanceof DepartmentManagementPlace)
		// return new DepartmentManagementActivity(
		// (DepartmentManagementPlace) place, clientFactory);
		//
		// else if (place instanceof SingleDepartmentPlace)
		// return new SingleDepartmentActivity((SingleDepartmentPlace) place,
		// clientFactory);
		//
		// else if (place instanceof MemberInvitationPlace)
		// return new ReplyToMemberInvitationActivity(
		// (MemberInvitationPlace) place, clientFactory);
		//
		// else if (place instanceof SendMemberInvitationPlace)
		// return new SendMemberInvitationActivity(
		// (SendMemberInvitationPlace) place, clientFactory);
		//
		// else if (place instanceof SmsShopPlace)
		// return new SmsShopActivity((SmsShopPlace) place, clientFactory);
		//
		// else if (place instanceof SmsShoppingCartPlace)
		// return new SmsShoppingCartActivity((SmsShoppingCartPlace) place,
		// clientFactory);
		//
		// else if (place instanceof SmsCreditHistoryPlace)
		// return new SmsCreditHistoryActivity((SmsCreditHistoryPlace) place,
		// clientFactory);

		return null;
	}

}
