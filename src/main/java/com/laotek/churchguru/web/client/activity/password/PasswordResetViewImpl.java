package com.laotek.churchguru.web.client.activity.password;

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
import com.google.gwt.user.client.DOM;
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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.daos.shared.exceptions.DisabledUserAccountException;
import com.laotek.churchguru.daos.shared.exceptions.LoginException;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.user.ForgotPasswordDialog;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.PasswordItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;

public class PasswordResetViewImpl implements PasswordResetView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static TextItem readonlyUsername = new TextItem("Username", false);
    private static PasswordItem passwd = new PasswordItem("New Password", true);
    private static PasswordItem passwdConfirm = new PasswordItem(
	    "Confirm Password", true);
    private static Button resetPasswrdButton = new Button("Reset Password");

    private static String username;

    private static String orgName;

    private static String passwordResetIdentifier;

    private static Form form = new Form();

    public PasswordResetViewImpl(PlaceController placeController) {
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

	innerLayout.setWidget(0, 0, new HTML("<h2>Reset Your Password</h2>"));

	readonlyUsername.getTextbox().setReadOnly(true);
	readonlyUsername.setValue(username);

	innerLayout.setWidget(1, 0, readonlyUsername);
	innerLayout.setWidget(2, 0, passwd);
	innerLayout.setWidget(3, 0, passwdConfirm);
	innerLayout.setWidget(4, 0, resetPasswrdButton);
	innerLayout.getCellFormatter().setHorizontalAlignment(4, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	passwd.setOtherPasswordItem(passwdConfirm);
	form.addFormItem(passwd);
	form.addFormItem(passwdConfirm);
	addHandlers();

	FlexTable layout = new FlexTable();
	layout.setWidget(0, 0, new HTML("&nbsp;"));
	HorizontalPanel orgDetailsHeader = new HorizontalPanel();
	orgDetailsHeader
		.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	orgDetailsHeader.add(new Image("/uploadedphotos/photos/org/logo"));
	orgDetailsHeader.add(new HTML("&nbsp;"));
	orgDetailsHeader.add(new Image("/uploadedphotos/photos/org/name"));
	layout.setWidget(1, 0, orgDetailsHeader);
	layout.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidget(2, 0, new RoundedCornerPanel(innerLayout));
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.getCellFormatter().setVerticalAlignment(1, 0,
		HasVerticalAlignment.ALIGN_MIDDLE);
	layout.getCellFormatter().setVerticalAlignment(2, 0,
		HasVerticalAlignment.ALIGN_MIDDLE);
	return layout;
    }

    private static void handle() {
	if (form.validateForm()) {
	    PasswordResetAction action = new PasswordResetAction();
	    action.setPasswordIdentifier(passwordResetIdentifier);
	    action.setPassword(passwdConfirm.getValue());
	    DispatchServiceAsync dispatchService = GWT
		    .create(DispatchService.class);
	    DispatchClient dispatchClient = new DispatchClient(dispatchService,
		    null, new DefaultExceptionHandler());
	    UserContext.getInstance().initDispatchClient(dispatchClient);
	    UserContext.getInstance().getDispatchClient()
		    .execute(action, new AsyncCallback<PasswordResetResult>() {
			@Override
			public void onFailure(Throwable throwable) {
			    String message = null;
			    if (throwable instanceof LoginException) {
				message = ((LoginException) throwable)
					.getMessage();

			    } else if (throwable instanceof DisabledUserAccountException) {
				message = "Account is disabled";

			    } else {
				message = "Access denied";
			    }
			    Window.alert(message);
			}

			@Override
			public void onSuccess(PasswordResetResult result) {
			    String protocol = Window.Location.getProtocol();
			    String host = Window.Location.getHost();
			    UrlBuilder builder = Window.Location
				    .createUrlBuilder().setProtocol(protocol)
				    .setHost(host).setPath("communicator")
				    .setHash("#HomePlace:home");
			    String url = builder.buildString();
			    Window.Location.replace(url);
			}
		    });
	}
    }

    private static void addHandlers() {

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
	resetPasswrdButton.addClickHandler(new ClickHandler() {
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
    public void initTab() {
	// MainMenuContext.getInstance().showDashboardPanel("Home");
    }

    @Override
    public void init(String username, String orgName,
	    String passwordResetIdentifier) {
	Window.setTitle("ChurchGURU! Communicator  - " + orgName);
	PasswordResetViewImpl.passwordResetIdentifier = passwordResetIdentifier;
	PasswordResetViewImpl.orgName = orgName;
	PasswordResetViewImpl.username = username;
    }

    @Override
    public void initWidgets() {
    }

    @Override
    public Widget asErrorPage() {
	final ForgotPasswordDialog forgotPasswordDialog = new ForgotPasswordDialog();
	Anchor forgotPasswordAnchor = new Anchor("Click here to reset again.");
	forgotPasswordAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		forgotPasswordDialog.center();
	    }
	});
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
		"<h2>Password reset process may be out of date.</h2>"));
	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidget(++row, 0, forgotPasswordAnchor);
	layout.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	return layout;
    }
}
