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
import com.laotek.churchguru.web.client.activity.home.HomeActivity;
import com.laotek.churchguru.web.client.activity.home.HomePlace;
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
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryActivity;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewItemActivity;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewActivity;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesActivity;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoriesActivity;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoryNewActivity;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsActivity;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersActivity;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsActivity;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewActivity;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersActivity;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersPlace;

public class AppActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
	super();
	this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
	if (place instanceof HomePlace)
	    return new HomeActivity((HomePlace) place, clientFactory);

	else if (place instanceof PeopleChartPlace)
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

	else if (place instanceof ListeningSpeakerNewPlace)
	    return new ListeningSpeakerNewActivity((ListeningSpeakerNewPlace) place, clientFactory);

	else if (place instanceof ListeningCustomersPlace)
	    return new ListeningCustomersActivity((ListeningCustomersPlace) place, clientFactory);

	else if (place instanceof ListeningCustomerDetailsPlace)
	    return new ListeningCustomerDetailsActivity((ListeningCustomerDetailsPlace) place, clientFactory);

	else if (place instanceof ListeningSpeakersPlace)
	    return new ListeningSpeakersActivity((ListeningSpeakersPlace) place, clientFactory);

	else if (place instanceof ListeningCategoryNewPlace)
	    return new ListeningCategoryNewActivity((ListeningCategoryNewPlace) place, clientFactory);

	else if (place instanceof ListeningMessagesPlace)
	    return new ListeningMessagesActivity((ListeningMessagesPlace) place, clientFactory);

	else if (place instanceof ListeningCategoriesPlace)
	    return new ListeningCategoriesActivity((ListeningCategoriesPlace) place, clientFactory);

	else if (place instanceof ListeningNotificationsPlace)
	    return new ListeningNotificationsActivity((ListeningNotificationsPlace) place, clientFactory);

	else if (place instanceof ListeningMessageNewPlace)
	    return new ListeningMessageNewActivity((ListeningMessageNewPlace) place, clientFactory);

	else if (place instanceof ListeningGalleryPlace)
	    return new ListeningGalleryActivity((ListeningGalleryPlace) place, clientFactory);

	else if (place instanceof ListeningGalleryNewItemPlace)
	    return new ListeningGalleryNewItemActivity((ListeningGalleryNewItemPlace) place, clientFactory);

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
