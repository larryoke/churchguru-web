package com.laotek.churchguru.web.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.client.activity.SystemSettingsView;
import com.laotek.churchguru.web.client.activity.SystemSettingsViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppViewImpl;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppViewImpl;
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
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoriesView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoriesViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCategoryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomerDetailsViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomersView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreCustomersViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreGalleryViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessageNewView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessageNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessagesView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreMessagesViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreNotificationsView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreNotificationsViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakerNewViewImpl;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakersView;
import com.laotek.churchguru.web.client.activity.website.estore.EStoreSpeakersViewImpl;

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

    private EStoreCategoriesView eStoreCategoriesView = new EStoreCategoriesViewImpl();

    private EStoreCategoryNewView eStoreCategoryNewView = new EStoreCategoryNewViewImpl();

    private EStoreCustomerDetailsView eStoreCustomerDetailsView = new EStoreCustomerDetailsViewImpl();

    private EStoreCustomersView eStoreCustomersView = new EStoreCustomersViewImpl();

    private EStoreGalleryNewView eStoreGalleryNewView = new EStoreGalleryNewViewImpl();

    private EStoreGalleryView eStoreGalleryView = new EStoreGalleryViewImpl();

    private EStoreMessageNewView eStoreMessageNewView = new EStoreMessageNewViewImpl();

    private EStoreMessagesView eStoreMessagesView = new EStoreMessagesViewImpl();

    private EStoreNotificationsView eStoreNotificationsView = new EStoreNotificationsViewImpl();

    private EStoreSpeakerNewView eStoreSpeakerNewView = new EStoreSpeakerNewViewImpl();

    private EStoreSpeakersView eStoreSpeakersView = new EStoreSpeakersViewImpl();

    private WebsiteView websiteView = new WebsiteViewImpl();

    private DonationSearchView donationSearchView = new DonationSearchViewImpl(placeController);

    private GeneralChurchAppView churchAppView = new GeneralChurchAppViewImpl(placeController);

    private GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView = new GetCurrentNoticesAndEventsHistoryViewImpl(
	    placeController);

    private PostNoticeOrEventChurchAppView postNoticeOrEventChurchAppView = new PostNoticeOrEventChurchAppViewImpl();

    private PreviewNoticeOrEventChurchAppView previewNoticeOrEventChurchAppView = new PreviewNoticeOrEventChurchAppViewImpl();

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
    public EStoreCategoriesView getEStoreCategoriesView() {
	if (eStoreCategoriesView == null) {
	}
	return eStoreCategoriesView;
    }

    @Override
    public EStoreCategoryNewView getEStoreCategoryNewView() {
	if (eStoreCategoryNewView == null) {
	}
	return eStoreCategoryNewView;
    }

    @Override
    public EStoreCustomerDetailsView getEStoreCustomerDetailsView() {
	if (eStoreCustomerDetailsView == null) {
	}
	return eStoreCustomerDetailsView;
    }

    @Override
    public EStoreCustomersView getEStoreCustomersView() {
	if (eStoreCustomersView == null) {
	}
	return eStoreCustomersView;
    }

    @Override
    public EStoreGalleryNewView getEStoreGalleryNewView() {
	if (eStoreGalleryNewView == null) {
	}
	return eStoreGalleryNewView;
    }

    @Override
    public EStoreGalleryView getEStoreGalleryView() {
	if (eStoreGalleryView == null) {
	}
	return eStoreGalleryView;
    }

    @Override
    public EStoreMessageNewView getEStoreMessageNewView() {
	if (eStoreMessageNewView == null) {
	}
	return eStoreMessageNewView;
    }

    @Override
    public EStoreMessagesView getEStoreMessagesView() {
	if (eStoreMessagesView == null) {
	}
	return eStoreMessagesView;
    }

    @Override
    public EStoreNotificationsView getEStoreNotificationsView() {
	if (eStoreNotificationsView == null) {
	}
	return eStoreNotificationsView;
    }

    @Override
    public EStoreSpeakerNewView getEStoreSpeakerNewView() {
	if (eStoreSpeakerNewView == null) {
	}
	return eStoreSpeakerNewView;
    }

    @Override
    public EStoreSpeakersView getEStoreSpeakersView() {
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

}