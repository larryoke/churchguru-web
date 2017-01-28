package com.laotek.churchguru.web.clientm;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.dispatch.DispatchMobileClient;
import com.laotek.churchguru.web.clientm.dispatch.DispatchMobileService;
import com.laotek.churchguru.web.clientm.dispatch.DispatchMobileServiceAsync;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

public class MobileEntry implements EntryPoint {

    private DispatchMobileServiceAsync dispatchMobileService = GWT.create(DispatchMobileService.class);

    private Place defaultPlace = new MobileHomePlace("mobileHome");

    @Override
    public void onModuleLoad() {

	RootPanel.getBodyElement().removeChild(DOM.getElementById("loading"));

	SuperDevModeUtil.showDevMode();

	MGWTStyle.injectStyleSheet("mobile.css");

	ViewPort viewPort = new MGWTSettings.ViewPort();
	viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

	MGWTSettings settings = new MGWTSettings();
	settings.setViewPort(viewPort);
	settings.setIconUrl("logo.png");
	settings.setFullscreen(true);
	settings.setFixIOS71BodyBug(true);
	settings.setPreventScrolling(true);
	MGWT.applySettings(settings);

	{
	    final String clientSessionId = Cookies.getCookie("sessionId");
	    DispatchMobileClient dispatchClient = new DispatchMobileClient(dispatchMobileService, clientSessionId,
		    new DefaultExceptionHandler());
	    MobileContext.getInstance().initDispatchClient(dispatchClient);
	}

	final MobileFactory clientFactory = new MobileFactoryImpl();
	MobileContext.getInstance().setClientFactory(clientFactory);

	// Start PlaceHistoryHandler with our PlaceHistoryMapper
	MobilePlaceHistoryMapper historyMapper = GWT.create(MobilePlaceHistoryMapper.class);

	{
	    AnimationWidget display = new AnimationWidget();
	    // Start ActivityManager for the main widget with our ActivityMapper
	    ActivityMapper activityMapper = new MobileActivityMapper(clientFactory);
	    PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
	    AnimatingActivityManager activityManager = new AnimatingActivityManager(activityMapper, appAnimationMapper,
		    clientFactory.getEventBus());
	    activityManager.setDisplay(display);
	    RootPanel.get().add(display);
	}
	PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

	historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), defaultPlace);
	historyHandler.handleCurrentHistory();

	AnimationHelper animationHelper = new AnimationHelper();
	RootFlexPanel rootFlexPanel = new RootFlexPanel();
	animationHelper.goTo(rootFlexPanel, Animations.SLIDE);

    }

}
