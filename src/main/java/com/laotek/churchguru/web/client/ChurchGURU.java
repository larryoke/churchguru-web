package com.laotek.churchguru.web.client;

import java.util.Date;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.password.PasswordResetPlace;
import com.laotek.churchguru.web.client.activity.user.UserReloadAction;
import com.laotek.churchguru.web.client.activity.user.UserReloadResult;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.UserDto;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

public class ChurchGURU implements EntryPoint {

    private ClientFactory clientFactory = GWT.create(ClientFactory.class);
    private Place defaultPlace = new GeneralChurchAppPlace("churchApp");
    private SimplePanel bodyPanel = new SimplePanel();
    private DispatchServiceAsync dispatchService = GWT.create(DispatchService.class);

    @Override
    public void onModuleLoad() {
	String href = Window.Location.getHref();
	if (href.contains("#MemberInvitationPlace")) {
	    load(Place.NOWHERE);

	} else if (href.contains("#AskMemberToVerifyDetailsPlace")) {
	    load(Place.NOWHERE);

	} else if (href.contains("#PasswordResetPlace")) {
	    String place = "#PasswordResetPlace:";
	    int index = href.indexOf(place);
	    int beginIndex = index + place.length();
	    String token = href.substring(beginIndex);
	    load(new PasswordResetPlace.Tokenizer().getPlace(token));

	} else if (href.contains("#NewUserSetupPlace")) {
	    String place = "#NewUserSetupPlace:";
	    int index = href.indexOf(place);
	    int beginIndex = index + place.length();
	    String token = href.substring(beginIndex);
	    load(new PasswordResetPlace.Tokenizer().getPlace(token));

	} else if (isLoggedIn()) {
	    reloadUser();

	} else {
	    LoginAccess.login();
	}

	// remove loading...
	// DOM.removeChild(RootPanel.getBodyElement(),
	// DOM.getElementById("loading"));
	RootPanel.getBodyElement().removeChild(DOM.getElementById("loading"));

	// Manage resizing of window
	Window.addResizeHandler(new ResizeHandler() {
	    Timer resizeTimer = new Timer() {
		@Override
		public void run() {
		    manageResizingOfWindow();
		}
	    };

	    @Override
	    public void onResize(ResizeEvent event) {
		resizeTimer.cancel();
		resizeTimer.schedule(250);
	    }
	});
    }

    private boolean isLoggedIn() {
	final String sessionId = Cookies.getCookie(ApplicationConstants.instance.sessionIdName());
	if (sessionId == null) {
	    return false;
	}
	return true;
    }

    private void load(OrganisationDto orgDto, UserDto userDto) {
	log("->load");
	UserContext.getInstance().setUserDto(userDto);
	MainLayout mainLayout = new MainLayout(bodyPanel, clientFactory);
	EventBus eventBus = clientFactory.getEventBus();
	PlaceController placeController = clientFactory.getPlaceController();
	ApplicationContext.getInstance().initPlaceController(placeController);
	ApplicationContext.getInstance().setMainScrollPanel(mainLayout.mainScrollPanel);
	ApplicationContext.getInstance().setOrgDto(orgDto);
	ApplicationContext.getInstance().setAdminEmail(orgDto.getAdminEmail());

	// Start ActivityManager for the main widget with our ActivityMapper
	ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
	ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
	activityManager.setDisplay(bodyPanel);

	// Start PlaceHistoryHandler with our PlaceHistoryMapper
	AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
	PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
	historyHandler.register(placeController, eventBus, defaultPlace);

	mainLayout.setHeaderUserName(userDto.getFullname());
	mainLayout.setHeaderOrgName(orgDto.getOrgName());
	RootLayoutPanel.get().add(mainLayout);
	// Goes to the place represented on URL else default place
	historyHandler.handleCurrentHistory();
	log("load->");
    }

    private void load(Place place) {
	SimplePanel appWidget = new SimplePanel();
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	EventBus eventBus = clientFactory.getEventBus();
	PlaceController placeController = clientFactory.getPlaceController();

	// Start ActivityManager for the main widget with our ActivityMapper
	ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
	ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
	activityManager.setDisplay(appWidget);

	// Start PlaceHistoryHandler with our PlaceHistoryMapper
	AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
	PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
	historyHandler.register(placeController, eventBus, place);

	RootLayoutPanel.get().add(new ScrollPanel(appWidget));
	// Goes to the place represented on URL else default place
	historyHandler.handleCurrentHistory();

    }

    private void reloadUser() {
	final String clientSessionId = Cookies.getCookie(ApplicationConstants.instance.sessionIdName());
	DispatchClient dispatchClient = new DispatchClient(dispatchService, clientSessionId,
		new DefaultExceptionHandler());
	UserContext.getInstance().initDispatchClient(dispatchClient);
	UserContext.getInstance().setClientSessionId(clientSessionId);

	UserReloadAction action = new UserReloadAction(clientSessionId);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<UserReloadResult>() {

	    @Override
	    public void onFailure(Throwable caught) {
		log("onFailure(Throwable caught)");
		Cookies.removeCookie(ApplicationConstants.instance.sessionIdName());
		Window.Location.reload();
	    }

	    @Override
	    public void onSuccess(final UserReloadResult result) {

		log("onSuccess(final UserReloadResult result)");
		load(result.getOrganisationDto(), result.getUserDto());
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		    @Override
		    public void execute() {
			UserContext.getInstance().setEmailAddress(result.getUserDto().getEmailAddress());
			Window.setTitle("Welcome " + result.getUserDto().getForenames() + "-"
				+ result.getOrganisationDto().getOrgName() + " ChurchGuru!");
			Date now = new Date();
			long nowLong = now.getTime();
			nowLong = nowLong + (1000 * 60 * 60 * 24 * 20);// thirty
								       // days
			now.setTime(nowLong);
			Cookies.setCookie("orgSubDomain", result.getOrganisationDto().getOrgDomain(), now);
		    }
		});
	    }
	});
    }

    private void manageResizingOfWindow() {
	if (Window.getClientWidth() < 600) {
	    HeaderLayout.getInstance().buildMinimal();
	    MainMenuContext.getInstance().hideMenu();

	} else if (Window.getClientWidth() < 800) {
	    HeaderLayout.getInstance().buildNearFull();
	    MainMenuContext.getInstance().hideMenu();

	} else {
	    HeaderLayout.getInstance().buildFull();
	    MainMenuContext.getInstance().showMenu();
	}
    }

    private static native void log(final String message)/*-{
							console.log(message);
							}-*/;
}
