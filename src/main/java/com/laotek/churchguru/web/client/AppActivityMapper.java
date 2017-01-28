package com.laotek.churchguru.web.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.laotek.churchguru.web.client.activity.LoginActivity;
import com.laotek.churchguru.web.client.activity.SystemSettingsActivity;
import com.laotek.churchguru.web.client.activity.SystemSettingsPlace;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryActivity;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppActivity;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppPlace;
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
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoriesActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoryNewActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoryNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomerDetailsActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomerDetailsPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomersActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomersPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryNewItemActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryNewItemPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessageNewActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessagesActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreNotificationsActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreNotificationsPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakerNewActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakerNewPlace;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakersActivity;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakersPlace;

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

	else if (place instanceof EStoreSpeakerNewPlace)
	    return new EStoreSpeakerNewActivity((EStoreSpeakerNewPlace) place, clientFactory);

	else if (place instanceof EStoreCustomersPlace)
	    return new EStoreCustomersActivity((EStoreCustomersPlace) place, clientFactory);

	else if (place instanceof EStoreCustomerDetailsPlace)
	    return new EStoreCustomerDetailsActivity((EStoreCustomerDetailsPlace) place, clientFactory);

	else if (place instanceof EStoreSpeakersPlace)
	    return new EStoreSpeakersActivity((EStoreSpeakersPlace) place, clientFactory);

	else if (place instanceof EStoreCategoryNewPlace)
	    return new EStoreCategoryNewActivity((EStoreCategoryNewPlace) place, clientFactory);

	else if (place instanceof EStoreMessagesPlace)
	    return new EStoreMessagesActivity((EStoreMessagesPlace) place, clientFactory);

	else if (place instanceof EStoreCategoriesPlace)
	    return new EStoreCategoriesActivity((EStoreCategoriesPlace) place, clientFactory);

	else if (place instanceof EStoreNotificationsPlace)
	    return new EStoreNotificationsActivity((EStoreNotificationsPlace) place, clientFactory);

	else if (place instanceof EStoreMessageNewPlace)
	    return new EStoreMessageNewActivity((EStoreMessageNewPlace) place, clientFactory);

	else if (place instanceof EStoreGalleryPlace)
	    return new EStoreGalleryActivity((EStoreGalleryPlace) place, clientFactory);

	else if (place instanceof EStoreGalleryNewItemPlace)
	    return new EStoreGalleryNewItemActivity((EStoreGalleryNewItemPlace) place, clientFactory);

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
