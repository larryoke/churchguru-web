package com.laotek.churchguru.web.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.client.activity.SystemSettingsView;
import com.laotek.churchguru.web.client.activity.SystemSettingsViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppViewImpl;
import com.laotek.churchguru.web.client.activity.dashboard.NewsLettersChartView;
import com.laotek.churchguru.web.client.activity.dashboard.NewsLettersChartViewImpl;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartView;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartViewImpl;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartView;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartViewImpl;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchView;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchViewImpl;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewView;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewViewImpl;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosView;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosViewImpl;
import com.laotek.churchguru.web.client.activity.password.PasswordResetView;
import com.laotek.churchguru.web.client.activity.password.PasswordResetViewImpl;
import com.laotek.churchguru.web.client.activity.user.AllUserView;
import com.laotek.churchguru.web.client.activity.user.AllUserViewImpl;
import com.laotek.churchguru.web.client.activity.user.LoginView;
import com.laotek.churchguru.web.client.activity.user.LoginViewImpl;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupView;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupViewImpl;
import com.laotek.churchguru.web.client.activity.user.SingleUserView;
import com.laotek.churchguru.web.client.activity.user.SingleUserViewImpl;
import com.laotek.churchguru.web.client.activity.user.UserProfilesView;
import com.laotek.churchguru.web.client.activity.user.UserProfilesViewImpl;
import com.laotek.churchguru.web.client.activity.website.WebsiteView;
import com.laotek.churchguru.web.client.activity.website.WebsiteViewImpl;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryView;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewView;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessagesView;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessagesViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoriesView;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoriesViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.media.cat.MediaMessageCategoryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomerDetailsViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomersView;
import com.laotek.churchguru.web.client.activity.website.media.cust.MediaMessageCustomersViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingView;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.notif.MediaMessageNotificationsView;
import com.laotek.churchguru.web.client.activity.website.media.notif.MediaMessageNotificationsViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakerNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakersView;
import com.laotek.churchguru.web.client.activity.website.media.speaker.MediaMessageSpeakersViewImpl;

public class ClientFactoryImpl implements ClientFactory {
    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);

    private PeopleChartView peopleChartView = null;
    private NewsLettersChartView newsLettersChartView = null;
    private WeeklyAttendanceChartView weeklyAttendanceChartView = null;

    private AllUserView allAdministratorView = null;
    private SystemSettingsView systemSettingsView = null;
    private SingleUserView singleAdministratorView = null;
    private LoginView loginView = new LoginViewImpl();
    private PasswordResetView passwordResetView = new PasswordResetViewImpl(placeController);
    private NewUserSetupView newUserSetupView = new NewUserSetupViewImpl(placeController);
    private UserProfilesView userProfilesView = new UserProfilesViewImpl();

    private MediaMessageCategoriesView eStoreCategoriesView = new MediaMessageCategoriesViewImpl();

    private MediaMessageCategoryNewView eStoreCategoryNewView = new MediaMessageCategoryNewViewImpl();

    private MediaMessageCustomerDetailsView eStoreCustomerDetailsView = new MediaMessageCustomerDetailsViewImpl();

    private MediaMessageCustomersView eStoreCustomersView = new MediaMessageCustomersViewImpl();

    private MediaMessageGalleryNewView eStoreGalleryNewView = new MediaMessageGalleryNewViewImpl();

    private MediaMessageGalleryView eStoreGalleryView = new MediaMessageGalleryViewImpl();

    private MediaMessageNewView eStoreMessageNewView = new MediaMessageNewViewImpl();

    private MediaMessagesView eStoreMessagesView = new MediaMessagesViewImpl();

    private MediaMessageNotificationsView eStoreNotificationsView = new MediaMessageNotificationsViewImpl();

    private MediaMessageSpeakerNewView eStoreSpeakerNewView = new MediaMessageSpeakerNewViewImpl();

    private MediaMessageSpeakersView eStoreSpeakersView = new MediaMessageSpeakersViewImpl();

    private WebsiteView websiteView = new WebsiteViewImpl();

    private DonationSearchView donationSearchView = new DonationSearchViewImpl(placeController);

    private GeneralChurchAppView churchAppView = new GeneralChurchAppViewImpl(placeController);

    private GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView = new GetCurrentNoticesAndEventsHistoryViewImpl(
	    placeController);

    private PostNoticeOrEventChurchAppView postNoticeOrEventChurchAppView = new PostNoticeOrEventChurchAppViewImpl();

    private PreviewNoticeOrEventChurchAppView previewNoticeOrEventChurchAppView = new PreviewNoticeOrEventChurchAppViewImpl();

    private YoutubeVideoNewView youtubeVideoNewView = new YoutubeVideoNewViewImpl();

    private YoutubeVideosView youtubeVideosView = new YoutubeVideosViewImpl();

    private MediaMessagesLoadingView mediaMessagesLoadingView = new MediaMessagesLoadingViewImpl();

    @Override
    public EventBus getEventBus() {
	return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
	return placeController;
    }

    @Override
    public AllUserView getAllUserView() {
	if (allAdministratorView == null) {
	    allAdministratorView = new AllUserViewImpl();
	}
	return allAdministratorView;
    }

    @Override
    public SystemSettingsView getSystemSettingsView() {
	if (systemSettingsView == null) {
	    systemSettingsView = new SystemSettingsViewImpl(placeController);
	}
	return systemSettingsView;
    }

    @Override
    public SingleUserView getSingleUserView() {
	if (singleAdministratorView == null) {
	    singleAdministratorView = new SingleUserViewImpl();
	}
	return singleAdministratorView;
    }

    @Override
    public LoginView getLoginView() {
	return loginView;
    }

    @Override
    public PeopleChartView getPeopleChartView() {
	if (peopleChartView == null) {
	    peopleChartView = new PeopleChartViewImpl(placeController);
	}
	return peopleChartView;
    }

    @Override
    public NewsLettersChartView getNewsLettersChartView() {
	if (newsLettersChartView == null) {
	    newsLettersChartView = new NewsLettersChartViewImpl(placeController);
	}
	return newsLettersChartView;
    }

    @Override
    public WeeklyAttendanceChartView getWeeklyAttendanceChartView() {
	if (weeklyAttendanceChartView == null) {
	    weeklyAttendanceChartView = new WeeklyAttendanceChartViewImpl(placeController);
	}
	return weeklyAttendanceChartView;
    }

    @Override
    public PasswordResetView getPasswordResetView() {
	if (passwordResetView == null) {
	}
	return passwordResetView;
    }

    @Override
    public NewUserSetupView getNewUserSetupView() {
	if (newUserSetupView == null) {
	}
	return newUserSetupView;
    }

    @Override
    public UserProfilesView getUserProfilesView() {
	if (userProfilesView == null) {
	}
	return userProfilesView;
    }

    @Override
    public MediaMessageCategoriesView getEStoreCategoriesView() {
	if (eStoreCategoriesView == null) {
	}
	return eStoreCategoriesView;
    }

    @Override
    public MediaMessageCategoryNewView getEStoreCategoryNewView() {
	if (eStoreCategoryNewView == null) {
	}
	return eStoreCategoryNewView;
    }

    @Override
    public MediaMessageCustomerDetailsView getEStoreCustomerDetailsView() {
	if (eStoreCustomerDetailsView == null) {
	}
	return eStoreCustomerDetailsView;
    }

    @Override
    public MediaMessageCustomersView getEStoreCustomersView() {
	if (eStoreCustomersView == null) {
	}
	return eStoreCustomersView;
    }

    @Override
    public MediaMessageGalleryNewView getEStoreGalleryNewView() {
	if (eStoreGalleryNewView == null) {
	}
	return eStoreGalleryNewView;
    }

    @Override
    public MediaMessageGalleryView getEStoreGalleryView() {
	if (eStoreGalleryView == null) {
	}
	return eStoreGalleryView;
    }

    @Override
    public MediaMessageNewView getEStoreMessageNewView() {
	if (eStoreMessageNewView == null) {
	}
	return eStoreMessageNewView;
    }

    @Override
    public MediaMessagesView getEStoreMessagesView() {
	if (eStoreMessagesView == null) {
	}
	return eStoreMessagesView;
    }

    @Override
    public MediaMessageNotificationsView getEStoreNotificationsView() {
	if (eStoreNotificationsView == null) {
	}
	return eStoreNotificationsView;
    }

    @Override
    public MediaMessageSpeakerNewView getEStoreSpeakerNewView() {
	if (eStoreSpeakerNewView == null) {
	}
	return eStoreSpeakerNewView;
    }

    @Override
    public MediaMessageSpeakersView getEStoreSpeakersView() {
	if (eStoreSpeakersView == null) {
	}
	return eStoreSpeakersView;
    }

    @Override
    public WebsiteView getWebsiteView() {
	if (websiteView == null) {
	}
	return websiteView;
    }

    @Override
    public DonationSearchView getDonationSearchView() {
	if (donationSearchView == null) {
	}
	return donationSearchView;
    }

    @Override
    public GeneralChurchAppView getGeneralChurchAppView() {
	if (churchAppView == null) {
	}
	return churchAppView;
    }

    @Override
    public GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView() {
	return getCurrentInstantMessagesHistoryView;
    }

    @Override
    public PostNoticeOrEventChurchAppView getPostNoticeOrEventChurchAppView() {
	return postNoticeOrEventChurchAppView;
    }

    @Override
    public PreviewNoticeOrEventChurchAppView getPreviewNoticeOrEventChurchAppView() {
	return previewNoticeOrEventChurchAppView;
    }

    @Override
    public YoutubeVideoNewView getYoutubeNewView() {
	return youtubeVideoNewView;
    }

    @Override
    public YoutubeVideosView getYoutubeView() {
	return youtubeVideosView;
    }

    @Override
    public MediaMessagesLoadingView getMediaMessagesLoadingView() {
	// TODO Auto-generated method stub
	return mediaMessagesLoadingView;
    }

}