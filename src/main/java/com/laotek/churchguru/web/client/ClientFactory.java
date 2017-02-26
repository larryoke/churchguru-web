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
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewView;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideosView;
import com.laotek.churchguru.web.client.activity.password.PasswordResetView;
import com.laotek.churchguru.web.client.activity.user.AllUserView;
import com.laotek.churchguru.web.client.activity.user.LoginView;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupView;
import com.laotek.churchguru.web.client.activity.user.SingleUserView;
import com.laotek.churchguru.web.client.activity.user.UserProfilesView;
import com.laotek.churchguru.web.client.activity.website.WebsiteView;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessageNewView;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessagesView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoriesView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.AudioMessageCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.AudioMessageCustomersView;
import com.laotek.churchguru.web.client.activity.website.audio.notif.AudioMessageNotificationsView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakersView;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryView;

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

    GeneralChurchAppView getGeneralChurchAppView();

    PostNoticeOrEventChurchAppView getPostNoticeOrEventChurchAppView();

    PreviewNoticeOrEventChurchAppView getPreviewNoticeOrEventChurchAppView();

    WeeklyAttendanceChartView getWeeklyAttendanceChartView();

    PasswordResetView getPasswordResetView();

    NewUserSetupView getNewUserSetupView();

    AudioMessageCategoriesView getEStoreCategoriesView();

    AudioMessageCategoryNewView getEStoreCategoryNewView();

    AudioMessageCustomerDetailsView getEStoreCustomerDetailsView();

    AudioMessageCustomersView getEStoreCustomersView();

    AudioMessageGalleryNewView getEStoreGalleryNewView();

    AudioMessageGalleryView getEStoreGalleryView();

    AudioMessageNewView getEStoreMessageNewView();

    AudioMessagesView getEStoreMessagesView();

    AudioMessageNotificationsView getEStoreNotificationsView();

    AudioMessageSpeakerNewView getEStoreSpeakerNewView();

    AudioMessageSpeakersView getEStoreSpeakersView();

    YoutubeVideoNewView getYoutubeNewView();

    YoutubeVideosView getYoutubeView();

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
