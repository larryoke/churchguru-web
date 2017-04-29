package com.laotek.churchguru.web.clientm.activity.home;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsPlace;
import com.laotek.churchguru.web.clientm.activity.give.GivePlace;
import com.laotek.churchguru.web.clientm.activity.message.category.MessageCategoryPlace;
import com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventTitlesActivity;
import com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventTitlesPlace;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskPlace;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestPlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class MobileHomeActivity extends MGWTAbstractActivity implements MobileHomeView.Presenter {

    private MobileFactory clientFactory;
    private MobileHomePlace place;

    public static boolean IS_GONE_TO_NOTICES_AND_EVENTS;

    public MobileHomeActivity(MobileHomePlace place, MobileFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	MobileHomeView view = clientFactory.getMobileHomeView();

	view.setPresenter(this);

	NoticeAndEventTitlesActivity.MESSAGE_INDEX = 0;

	getMobileProperties(containerWidget, view);

	view.onFinishLoad();
    }

    @Override
    public void goToNoticesIf() {
	if (!IS_GONE_TO_NOTICES_AND_EVENTS && "NoticesAndEvents".equals(place.getName())) {
	    IS_GONE_TO_NOTICES_AND_EVENTS = true;

	    Timer timer = new Timer() {
		@Override
		public void run() {
		    clientFactory.getPlaceController().goTo(new NoticeAndEventTitlesPlace("noticesAndEvents"));
		    cancel();
		}
	    };
	    timer.schedule(1000);
	}
    }

    private void getMobileProperties(final AcceptsOneWidget containerWidget, final MobileHomeView view) {
	GetMobilePropertiesAction action = new GetMobilePropertiesAction();
	MobileContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMobilePropertiesResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetMobilePropertiesResult result) {

		view.onLoad();

		final MobileProperties mobileProperties = result.getMobileProperties();

		view.setTitle(mobileProperties.getChurchName());

		view.setTopics(mobileProperties.getTopics());

		addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(new CellSelectedHandler() {

		    @Override
		    public void onCellSelected(CellSelectedEvent event) {
			int index = event.getIndex();

			Topic topic = mobileProperties.getTopics().get(index);

			final ChurchAppTopicEnum current = topic.getChurchAppTopicEnum();

			if (ChurchAppTopicEnum.PASTORS_DESK.equals(current)) {
			    clientFactory.getPlaceController().goTo(new PastorDeskPlace(current.getKey()));
			    return;
			}
			if (ChurchAppTopicEnum.LISTEN.equals(current)) {
			    clientFactory.getPlaceController().goTo(new MessageCategoryPlace("messageCategory"));
			    return;
			}
			if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(current)) {
			    clientFactory.getPlaceController().goTo(new PrayerRequestPlace(current.getKey()));
			    return;
			}
			if (ChurchAppTopicEnum.DONATION.equals(current)) {
			    clientFactory.getPlaceController().goTo(new GivePlace(current.getKey()));
			    return;
			}
			if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(current)) {
			    clientFactory.getPlaceController().goTo(new NoticeAndEventTitlesPlace(current.getKey()));
			    return;
			}
			if (ChurchAppTopicEnum.ABOUT_US.equals(current)) {
			    clientFactory.getPlaceController().goTo(new AboutUsPlace(current.getKey()));
			    return;
			}
		    }
		}));

		addHandlerRegistration(view.getAboutButton().addTapHandler(new TapHandler() {

		    @Override
		    public void onTap(TapEvent event) {
			clientFactory.getPlaceController()
				.goTo(new AboutUsPlace(ChurchAppTopicEnum.ABOUT_US.getKey()));

		    }
		}));

	    }
	});

	containerWidget.setWidget(view);
	view.displayLoadingProgress();
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
	return null;
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }
}
