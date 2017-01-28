package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationConstants;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.PasswordItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;

public class NewUserSetupViewImpl implements NewUserSetupView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static TextItem username = new TextItem("User Name", true);
    private static PasswordItem passwd = new PasswordItem("New Password", true);
    private static PasswordItem passwdConfirm = new PasswordItem(
	    "Confirm Password", true);
    private static Button completeSetupButton = new Button("Complete setup");
    private static String userIdentifier;

    private static Form form = new Form();

    public NewUserSetupViewImpl(PlaceController placeController) {
    }

    @Override
    public Widget asWidget() {
	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	int row = 0;
	layout.setWidget(row, 0, new HTML("&nbsp;"));
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));

	HorizontalPanel orgDetailsHeader = new HorizontalPanel();
	orgDetailsHeader
		.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	orgDetailsHeader.add(new Image("/uploadedphotos/photos/org/logo"));
	orgDetailsHeader.add(new HTML("&nbsp;"));
	orgDetailsHeader.add(new Image("/uploadedphotos/photos/org/name"));
	layout.setWidget(++row, 0, orgDetailsHeader);
	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(++row, 0, resetPanel());
	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	return layout;
    }

    private static FlexTable resetPanel() {
	FlexTable innerLayout = new FlexTable();
	innerLayout.setCellSpacing(6);
	FlexCellFormatter cellFormatter = innerLayout.getFlexCellFormatter();

	cellFormatter.setColSpan(0, 0, 2);
	cellFormatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	cellFormatter.setHorizontalAlignment(4, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	innerLayout.setBorderWidth(0);

	innerLayout.setWidget(0, 0, new HTML(
		"<h2Please complete your account setup</h2>"));

	innerLayout.setWidget(1, 0, username);
	innerLayout.setWidget(2, 0, passwd);
	innerLayout.setWidget(3, 0, passwdConfirm);
	innerLayout.setWidget(4, 0, completeSetupButton);
	innerLayout.getCellFormatter().setHorizontalAlignment(4, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	username.setRangeMin(5);
	passwd.setOtherPasswordItem(passwdConfirm);

	form.addFormItem(username);
	form.addFormItem(passwd);
	form.addFormItem(passwdConfirm);
	addHandlers();

	FlexTable layout = new FlexTable();
	layout.setWidget(0, 0, new HTML("&nbsp;"));
	layout.setWidget(1, 0, new RoundedCornerPanel(innerLayout));
	layout.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(1, 0,
		HasVerticalAlignment.ALIGN_MIDDLE);
	return layout;
    }

    private static void handle() {
	if (form.validateForm()) {
	    NewUserSetupAction action = new NewUserSetupAction();
	    action.setIdentifier(userIdentifier);
	    action.setUsername(username.getValue());
	    action.setPassword(passwdConfirm.getValue());
	    DispatchServiceAsync dispatchService = GWT
		    .create(DispatchService.class);
	    DispatchClient dispatchClient = new DispatchClient(dispatchService,
		    null, new DefaultExceptionHandler());

	    UserContext.getInstance().initDispatchClient(dispatchClient);
	    UserContext.getInstance().getDispatchClient()
		    .execute(action, new AsyncCallback<NewUserSetupResult>() {
			@Override
			public void onFailure(Throwable throwable) {
			    ErrorMessageDialog.getInstance().handleError(
				    throwable);
			}

			@Override
			public void onSuccess(NewUserSetupResult result) {
			    Cookies.setCookie(ApplicationConstants.instance
				    .sessionIdName(), result
				    .getClientSessionId());
			    String protocol = Window.Location.getProtocol();
			    String host = Window.Location.getHost();
			    UrlBuilder builder = Window.Location
				    .createUrlBuilder().setProtocol(protocol)
				    .setHost(host).setPath("/communicator")
				    .setHash("#HomePlace:home");
			    String url = builder.buildString();
			    Window.Location.replace(url);
			}
		    });
	}
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

	passwdConfirm.getTextbox().addKeyPressHandler(new KeyPressHandler() {
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
	completeSetupButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		handle();
	    }
	});
    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public void init(String passwordResetIdentifier) {
	Window.setTitle("New account setup");
	NewUserSetupViewImpl.userIdentifier = passwordResetIdentifier;
    }

    @Override
    public void initWidgets() {
    }

    @Override
    public Widget asErrorPage() {

	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	int row = 0;
	layout.setWidget(row, 0, new HTML("&nbsp;"));
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));
	layout.setWidget(++row, 0, new HTML("&nbsp;"));

	layout.setWidget(++row, 0, new HTML(
		"<h2>New user account setup process may be out of date.</h2>"));
	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	return layout;
    }

}
