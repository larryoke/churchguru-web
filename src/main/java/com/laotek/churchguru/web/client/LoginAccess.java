package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.laotek.churchguru.daos.shared.exceptions.DisabledUserAccountException;
import com.laotek.churchguru.daos.shared.exceptions.LoginException;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.user.ForgotPasswordDialog;
import com.laotek.churchguru.web.client.activity.user.ForgotUsernameDialog;
import com.laotek.churchguru.web.client.activity.user.LoginAction;
import com.laotek.churchguru.web.client.activity.user.LoginResult;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.PasswordItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

public class LoginAccess {

    private static TextItem username = new TextItem("User Name", true);
    private static PasswordItem passwd = new PasswordItem("Password", true);
    private static Button loginButton = new Button("Login");
    private static Form form = new Form();
    private static ForgotUsernameDialog forgotUsernameDialog = new ForgotUsernameDialog();
    private static ForgotPasswordDialog forgotPasswordDialog = new ForgotPasswordDialog();
    private static Anchor forgotUsernameAnchor = new Anchor("username");
    private static Anchor forgotPasswordAnchor = new Anchor("password");

    public static void login() {
	FlexTable innerLayout = new FlexTable();
	innerLayout.setBorderWidth(0);
	innerLayout.setCellPadding(0);
	innerLayout.setCellSpacing(0);
	innerLayout.setWidth("100%");
	innerLayout.setHeight("100%");
	FlexCellFormatter cellFormatter = innerLayout.getFlexCellFormatter();

	// innerLayout.setWidget(0, 0, new Image("images/app/logo.png"));
	Window.setTitle("ChurchGURU! Communicator");
	innerLayout.setWidget(0, 0, new HTML("&nbsp"));
	cellFormatter.setStylePrimaryName(0, 0, "loginScreenHeader");

	innerLayout.setWidget(1, 0, loginPanel());

	cellFormatter.setHeight(0, 0, "75px");

	cellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	cellFormatter.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	addHandlers();

	FlexTable layout = new FlexTable();
	layout.setBorderWidth(0);
	layout.setSize("100%", "80%");
	layout.setWidget(0, 0, innerLayout);
	cellFormatter = layout.getFlexCellFormatter();
	cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	cellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	RootLayoutPanel.get().add(layout);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		String cookieUsername = Cookies.getCookie("cookieUsername");
		if (cookieUsername != null && cookieUsername.length() > 0) {
		    username.setValue(cookieUsername);
		    passwd.focus();
		} else {
		    username.focus();
		}
	    }
	});
    }

    private static FlexTable loginPanel() {
	FlexTable innerLayout = new FlexTable();
	innerLayout.setCellSpacing(6);
	FlexCellFormatter cellFormatter = innerLayout.getFlexCellFormatter();

	cellFormatter.setColSpan(0, 0, 2);
	cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

	innerLayout.setBorderWidth(0);

	HorizontalPanel loginHeaderPanel = new HorizontalPanel();
	loginHeaderPanel.add(new Image("images/app/padlock_open.png"));
	loginHeaderPanel.add(new HTML("<h2>Please Login</h2>"));
	innerLayout.setWidget(0, 0, loginHeaderPanel);

	innerLayout.setWidget(1, 0, new Image("images/app/loginPerson.png"));
	innerLayout.setWidget(2, 0, username);
	innerLayout.setWidget(3, 0, passwd);
	innerLayout.setWidget(4, 0, loginButton);
	HorizontalPanel forgotPanel = new HorizontalPanel();
	forgotPanel.add(new HTML("Forgotten your &nbsp;"));
	forgotPanel.add(forgotUsernameAnchor);
	forgotPanel.add(new HTML("&nbsp;or&nbsp;"));
	forgotPanel.add(forgotPasswordAnchor);
	forgotPanel.add(new HTML("?"));
	innerLayout.setWidget(5, 0, forgotPanel);
	innerLayout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	innerLayout.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
	innerLayout.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
	innerLayout.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
	innerLayout.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
	innerLayout.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);

	form.addFormItem(username);
	form.addFormItem(passwd);

	// DecoratorPanel decPanel = new DecoratorPanel();
	// decPanel.setWidget(innerLayout);
	Image home = new Image("/uploadedphotos/photos/org/name");
	home.setStylePrimaryName("handPointer");
	home.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		String protocol = Window.Location.getProtocol();
		String host = Window.Location.getHost();
		UrlBuilder builder = Window.Location.createUrlBuilder().setProtocol(protocol).setHost(host)
			.setPath("/");
		String url = builder.buildString();
		Window.Location.replace(url);
	    }
	});

	FlexTable layout = new FlexTable();
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	VerticalPanel vpanel = new VerticalPanel();
	vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	vpanel.add(home);
	vpanel.add(new HTML("<h2>Communicator</h2>"));
	layout.setWidget(0, 0, vpanel);
	layout.setWidget(1, 0, new RoundedCornerPanel(innerLayout));
	layout.setWidget(2, 0, new Image("images/app/logoSmall.png"));

	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	layout.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	layout.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	return layout;
    }

    private static void addHandlers() {

	username.getTextbox().addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    handle();
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			handle();
		    }
		}
	    }
	});

	passwd.getTextbox().addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    handle();
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			handle();
		    }
		}
	    }
	});
	loginButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		handle();
	    }
	});
	forgotUsernameAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		forgotUsernameDialog.center();
	    }
	});
	forgotPasswordAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		forgotPasswordDialog.center();
	    }
	});
    }

    private static void handle() {
	if (form.validateForm()) {
	    LoginAction action = new LoginAction();
	    action.setUsername(username.getValue());
	    action.setPassword(passwd.getValue());
	    DispatchServiceAsync dispatchService = GWT.create(DispatchService.class);
	    DispatchClient dispatchClient = new DispatchClient(dispatchService, null, new DefaultExceptionHandler());
	    UserContext.getInstance().initDispatchClient(dispatchClient);
	    UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<LoginResult>() {
		@Override
		public void onFailure(Throwable throwable) {
		    log("Failed");
		    String message = null;
		    if (throwable instanceof LoginException) {
			message = ((LoginException) throwable).getMessage();

		    } else if (throwable instanceof DisabledUserAccountException) {
			message = "Account is disabled";

		    } else {
			message = "Access denied";
		    }
		    Window.alert(message);
		    username.setValue("");
		    passwd.setValue("");
		    username.focus();
		}

		@Override
		public void onSuccess(LoginResult result) {
		    log("Success");
		    Cookies.setCookie(ApplicationConstants.instance.sessionIdName(), result.getSessionId());
		    Window.Location.reload();
		}
	    });
	}
    }

    private static native void log(final String message)/*-{
							console.log('*********LoginAccess: ' + message + '**********');
							}-*/;
}
