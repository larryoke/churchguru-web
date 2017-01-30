package com.laotek.churchguru.web.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.client.activity.SystemSettingsView;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PostNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.PreviewNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.dashboard.NewsLettersChartView;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartView;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartView;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchView;
import com.laotek.churchguru.web.client.activity.home.HomeView;
import com.laotek.churchguru.web.client.activity.password.PasswordResetView;
import com.laotek.churchguru.web.client.activity.user.AllUserView;
import com.laotek.churchguru.web.client.activity.user.LoginView;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupView;
import com.laotek.churchguru.web.client.activity.user.SingleUserView;
import com.laotek.churchguru.web.client.activity.user.UserProfilesView;
import com.laotek.churchguru.web.client.activity.website.WebsiteView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningCategoriesView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningCustomersView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningGalleryView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessageNewView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningNotificationsView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningSpeakersView;

public interface ClientFactory {
    EventBus getEventBus();

    PlaceController getPlaceController();

    PeopleChartView getPeopleChartView();

    NewsLettersChartView getNewsLettersChartView();

    AllUserView getAllUserView();

    SystemSettingsView getSystemSettingsView();

    SingleUserView getSingleUserView();

    UserProfilesView getUserProfilesView();

    LoginView getLoginView();

    HomeView getHomeView();

    GeneralChurchAppView getGeneralChurchAppView();

    PostNoticeOrEventChurchAppView getPostNoticeOrEventChurchAppView();

    PreviewNoticeOrEventChurchAppView getPreviewNoticeOrEventChurchAppView();

    WeeklyAttendanceChartView getWeeklyAttendanceChartView();

    PasswordResetView getPasswordResetView();

    NewUserSetupView getNewUserSetupView();

    ListeningCategoriesView getEStoreCategoriesView();

    ListeningCategoryNewView getEStoreCategoryNewView();

    ListeningCustomerDetailsView getEStoreCustomerDetailsView();

    ListeningCustomersView getEStoreCustomersView();

    ListeningGalleryNewView getEStoreGalleryNewView();

    ListeningGalleryView getEStoreGalleryView();

    ListeningMessageNewView getEStoreMessageNewView();

    ListeningMessagesView getEStoreMessagesView();

    ListeningNotificationsView getEStoreNotificationsView();

    ListeningSpeakerNewView getEStoreSpeakerNewView();

    ListeningSpeakersView getEStoreSpeakersView();

    WebsiteView getWebsiteView();

    DonationSearchView getDonationSearchView();

    GetCurrentNoticesAndEventsHistoryView getCurrentInstantMessagesHistoryView();

    // NotificationView getNotficationView();
    //
    // ConfirmNewOrgAccountView getConfirmNewOrgAccountView();
    //
    // SignUpAccountView getSignUpView();
    //
    // AddGuestView getAddGuestView();
    //
    // SingleGuestView getSingleGuestView();
    //
    // DepartmentManagementView getDepartmentManagementView();
    //
    // SingleDepartmentView getSingleDepartmentView();
    //
    // ManageGuestView getGuestView();
    // BrowseGuestSearchResultsView getGuestSearchResultsView();
    // ShopHomeView getShopHomeView();
    // SmsShopView getSmsShopView();
    // SmsShoppingCartView getSmsShoppingCartView();
    // SmsCreditHistoryView getSmsCreditHistoryView();
    // PaypalStartPaymentView getPaypalStartPaymentView();
    // PaypalExitPaymentView getPaypalExitPaymentView();

}
