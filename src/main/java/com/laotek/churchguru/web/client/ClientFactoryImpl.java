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
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessageNewView;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessageNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessagesView;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessagesViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoriesView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoriesViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomerDetailsViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomersView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomersViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.notif.AudioMessageNotificationsView;
import com.laotek.churchguru.web.client.activity.website.audio.notif.AudioMessageNotificationsViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakerNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakersView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakersViewImpl;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryView;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryViewImpl;

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

    private AudioMessageCategoriesView eStoreCategoriesView = new AudioMessageCategoriesViewImpl();

    private AudioMessageCategoryNewView eStoreCategoryNewView = new AudioMessageCategoryNewViewImpl();

    private AudioMessageCustomerDetailsView eStoreCustomerDetailsView = new AudioMessageCustomerDetailsViewImpl();

    private AudioMessageCustomersView eStoreCustomersView = new AudioMessageCustomersViewImpl();

    private AudioMessageGalleryNewView eStoreGalleryNewView = new AudioMessageGalleryNewViewImpl();

    private AudioMessageGalleryView eStoreGalleryView = new AudioMessageGalleryViewImpl();

    private AudioMessageNewView eStoreMessageNewView = new AudioMessageNewViewImpl();

    private AudioMessagesView eStoreMessagesView = new AudioMessagesViewImpl();

    private AudioMessageNotificationsView eStoreNotificationsView = new AudioMessageNotificationsViewImpl();

    private AudioMessageSpeakerNewView eStoreSpeakerNewView = new AudioMessageSpeakerNewViewImpl();

    private AudioMessageSpeakersView eStoreSpeakersView = new AudioMessageSpeakersViewImpl();

    private WebsiteView websiteView = new WebsiteViewImpl();

    private DonationSearchView donationSearchView = new DonationSearchViewImpl(placeController);

    private GeneralChurchAppView churchAppView = new GeneralChurchAppViewImpl(placeController);

    private GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView = new GetCurrentNoticesAndEventsHistoryViewImpl(
	    placeController);

    private PostNoticeOrEventChurchAppView postNoticeOrEventChurchAppView = new PostNoticeOrEventChurchAppViewImpl();

    private PreviewNoticeOrEventChurchAppView previewNoticeOrEventChurchAppView = new PreviewNoticeOrEventChurchAppViewImpl();

    private YoutubeVideoNewView youtubeVideoNewView = new YoutubeVideoNewViewImpl();

    private YoutubeVideosView youtubeVideosView = new YoutubeVideosViewImpl();

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
    public AudioMessageCategoriesView getEStoreCategoriesView() {
	if (eStoreCategoriesView == null) {
	}
	return eStoreCategoriesView;
    }

    @Override
    public AudioMessageCategoryNewView getEStoreCategoryNewView() {
	if (eStoreCategoryNewView == null) {
	}
	return eStoreCategoryNewView;
    }

    @Override
    public AudioMessageCustomerDetailsView getEStoreCustomerDetailsView() {
	if (eStoreCustomerDetailsView == null) {
	}
	return eStoreCustomerDetailsView;
    }

    @Override
    public AudioMessageCustomersView getEStoreCustomersView() {
	if (eStoreCustomersView == null) {
	}
	return eStoreCustomersView;
    }

    @Override
    public AudioMessageGalleryNewView getEStoreGalleryNewView() {
	if (eStoreGalleryNewView == null) {
	}
	return eStoreGalleryNewView;
    }

    @Override
    public AudioMessageGalleryView getEStoreGalleryView() {
	if (eStoreGalleryView == null) {
	}
	return eStoreGalleryView;
    }

    @Override
    public AudioMessageNewView getEStoreMessageNewView() {
	if (eStoreMessageNewView == null) {
	}
	return eStoreMessageNewView;
    }

    @Override
    public AudioMessagesView getEStoreMessagesView() {
	if (eStoreMessagesView == null) {
	}
	return eStoreMessagesView;
    }

    @Override
    public AudioMessageNotificationsView getEStoreNotificationsView() {
	if (eStoreNotificationsView == null) {
	}
	return eStoreNotificationsView;
    }

    @Override
    public AudioMessageSpeakerNewView getEStoreSpeakerNewView() {
	if (eStoreSpeakerNewView == null) {
	}
	return eStoreSpeakerNewView;
    }

    @Override
    public AudioMessageSpeakersView getEStoreSpeakersView() {
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

}