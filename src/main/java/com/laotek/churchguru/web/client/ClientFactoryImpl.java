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
import com.laotek.churchguru.web.client.activity.home.HomeView;
import com.laotek.churchguru.web.client.activity.home.HomeViewImpl;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessageNewView;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessageNewViewImpl;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessagesView;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessagesViewImpl;
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
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryView;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoriesView;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoriesViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.listening.cat.ListeningCategoryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersView;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsView;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersView;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersViewImpl;

public class ClientFactoryImpl implements ClientFactory {
    private EventBus eventBus = new SimpleEventBus();
    private PlaceController placeController = new PlaceController(eventBus);

    private HomeView homeView = null;
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

    private ListeningCategoriesView eStoreCategoriesView = new ListeningCategoriesViewImpl();

    private ListeningCategoryNewView eStoreCategoryNewView = new ListeningCategoryNewViewImpl();

    private ListeningCustomerDetailsView eStoreCustomerDetailsView = new ListeningCustomerDetailsViewImpl();

    private ListeningCustomersView eStoreCustomersView = new ListeningCustomersViewImpl();

    private ListeningGalleryNewView eStoreGalleryNewView = new ListeningGalleryNewViewImpl();

    private ListeningGalleryView eStoreGalleryView = new ListeningGalleryViewImpl();

    private ListeningMessageNewView eStoreMessageNewView = new ListeningMessageNewViewImpl();

    private ListeningMessagesView eStoreMessagesView = new ListeningMessagesViewImpl();

    private ListeningNotificationsView eStoreNotificationsView = new ListeningNotificationsViewImpl();

    private ListeningSpeakerNewView eStoreSpeakerNewView = new ListeningSpeakerNewViewImpl();

    private ListeningSpeakersView eStoreSpeakersView = new ListeningSpeakersViewImpl();

    private WebsiteView websiteView = new WebsiteViewImpl();

    private DonationSearchView donationSearchView = new DonationSearchViewImpl(placeController);

    private GeneralChurchAppView churchAppView = new GeneralChurchAppViewImpl(placeController);

    private GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView = new GetCurrentNoticesAndEventsHistoryViewImpl(
	    placeController);

    private PostNoticeOrEventChurchAppView postNoticeOrEventChurchAppView = new PostNoticeOrEventChurchAppViewImpl();

    private PreviewNoticeOrEventChurchAppView previewNoticeOrEventChurchAppView = new PreviewNoticeOrEventChurchAppViewImpl();

    private WatchingMessageNewView watchingMessageNewView = new WatchingMessageNewViewImpl();

    private WatchingMessagesView watchingMessagesView = new WatchingMessagesViewImpl();

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
    public HomeView getHomeView() {
	if (homeView == null) {
	    homeView = new HomeViewImpl(placeController);
	}
	return homeView;
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
    public ListeningCategoriesView getEStoreCategoriesView() {
	if (eStoreCategoriesView == null) {
	}
	return eStoreCategoriesView;
    }

    @Override
    public ListeningCategoryNewView getEStoreCategoryNewView() {
	if (eStoreCategoryNewView == null) {
	}
	return eStoreCategoryNewView;
    }

    @Override
    public ListeningCustomerDetailsView getEStoreCustomerDetailsView() {
	if (eStoreCustomerDetailsView == null) {
	}
	return eStoreCustomerDetailsView;
    }

    @Override
    public ListeningCustomersView getEStoreCustomersView() {
	if (eStoreCustomersView == null) {
	}
	return eStoreCustomersView;
    }

    @Override
    public ListeningGalleryNewView getEStoreGalleryNewView() {
	if (eStoreGalleryNewView == null) {
	}
	return eStoreGalleryNewView;
    }

    @Override
    public ListeningGalleryView getEStoreGalleryView() {
	if (eStoreGalleryView == null) {
	}
	return eStoreGalleryView;
    }

    @Override
    public ListeningMessageNewView getEStoreMessageNewView() {
	if (eStoreMessageNewView == null) {
	}
	return eStoreMessageNewView;
    }

    @Override
    public ListeningMessagesView getEStoreMessagesView() {
	if (eStoreMessagesView == null) {
	}
	return eStoreMessagesView;
    }

    @Override
    public ListeningNotificationsView getEStoreNotificationsView() {
	if (eStoreNotificationsView == null) {
	}
	return eStoreNotificationsView;
    }

    @Override
    public ListeningSpeakerNewView getEStoreSpeakerNewView() {
	if (eStoreSpeakerNewView == null) {
	}
	return eStoreSpeakerNewView;
    }

    @Override
    public ListeningSpeakersView getEStoreSpeakersView() {
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
    public WatchingMessageNewView getWatchingMessageNewView() {
	return watchingMessageNewView;
    }

    @Override
    public WatchingMessagesView getWatchingMessagesView() {
	return watchingMessagesView;
    }

}