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
import com.laotek.churchguru.web.client.activity.website.audio.MediaMessageNewView;
import com.laotek.churchguru.web.client.activity.website.audio.MediaMessagesView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.MediaMessageCategoriesView;
import com.laotek.churchguru.web.client.activity.website.audio.cat.MediaMessageCategoryNewView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.MediaMessageCustomerDetailsView;
import com.laotek.churchguru.web.client.activity.website.audio.cust.MediaMessageCustomersView;
import com.laotek.churchguru.web.client.activity.website.audio.notif.MediaMessageNotificationsView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakerNewView;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.AudioMessageSpeakersView;
import com.laotek.churchguru.web.client.activity.website.gal.AudioMessageGalleryNewView;
import com.laotek.churchguru.web.client.activity.website.gal.MediaMessageGalleryView;

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

    MediaMessageCategoriesView getEStoreCategoriesView();

    MediaMessageCategoryNewView getEStoreCategoryNewView();

    MediaMessageCustomerDetailsView getEStoreCustomerDetailsView();

    MediaMessageCustomersView getEStoreCustomersView();

    AudioMessageGalleryNewView getEStoreGalleryNewView();

    MediaMessageGalleryView getEStoreGalleryView();

    MediaMessageNewView getEStoreMessageNewView();

    MediaMessagesView getEStoreMessagesView();

    MediaMessageNotificationsView getEStoreNotificationsView();

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
