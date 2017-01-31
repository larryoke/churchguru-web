package com.laotek.churchguru.web.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.client.activity.SystemSettingsView;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PreviewNoticeOrEventChurchAppView;
import com.laotek.churchguru.web.client.activity.dashboard.NewsLettersChartView;
import com.laotek.churchguru.web.client.activity.dashboard.PeopleChartView;
import com.laotek.churchguru.web.client.activity.dashboard.WeeklyAttendanceChartView;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchView;
import com.laotek.churchguru.web.client.activity.home.HomeView;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessageNewView;
import com.laotek.churchguru.web.client.activity.media.watching.WatchingMessagesView;
import com.laotek.churchguru.web.client.activity.password.PasswordResetView;
import com.laotek.churchguru.web.client.activity.user.AllUserView;
import com.laotek.churchguru.web.client.activity.user.LoginView;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupView;
import com.laotek.churchguru.web.client.activity.user.SingleUserView;
import com.laotek.churchguru.web.client.activity.user.UserProfilesView;
import com.laotek.churchguru.web.client.activity.website.WebsiteView;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.ListeningGalleryView;
import com.laotek.churchguru.web.client.activity.website.listening.AudioMessageMessageNewView;
import com.laotek.churchguru.web.client.activity.website.listening.AudioMessageMessagesView;
import com.laotek.churchguru.web.client.activity.website.listening.cat.AudioMessageCategoriesView;
import com.laotek.churchguru.web.client.activity.website.listening.cat.AudioMessageCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.listening.cust.ListeningCustomersView;
import com.laotek.churchguru.web.client.activity.website.listening.notif.ListeningNotificationsView;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.listening.speaker.ListeningSpeakersView;

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

    AudioMessageCategoriesView getEStoreCategoriesView();

    AudioMessageCategoryNewView getEStoreCategoryNewView();

    ListeningCustomerDetailsView getEStoreCustomerDetailsView();

    ListeningCustomersView getEStoreCustomersView();

    ListeningGalleryNewView getEStoreGalleryNewView();

    ListeningGalleryView getEStoreGalleryView();

    AudioMessageMessageNewView getEStoreMessageNewView();

    AudioMessageMessagesView getEStoreMessagesView();

    ListeningNotificationsView getEStoreNotificationsView();

    ListeningSpeakerNewView getEStoreSpeakerNewView();

    ListeningSpeakersView getEStoreSpeakersView();

    WatchingMessageNewView getWatchingMessageNewView();

    WatchingMessagesView getWatchingMessagesView();

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
