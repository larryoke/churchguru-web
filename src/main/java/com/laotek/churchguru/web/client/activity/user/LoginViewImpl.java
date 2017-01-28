package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationConstants;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;
import com.laotek.churchguru.web.client.widget.Form;
import com.laotek.churchguru.web.client.widget.PasswordItem;
import com.laotek.churchguru.web.client.widget.TextItem;

public class LoginViewImpl implements LoginView {

    private TextItem username = new TextItem("Email Address", true);
    private PasswordItem passwd = new PasswordItem("Password", true);
    private Button loginButton = new Button("Login");
    private Form form = new Form();

    @Override
    public Widget asWidget() {
	FlexTable layout = new FlexTable();
	layout.setCellSpacing(6);
	FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	layout.setHTML(0, 0, "Please Login");
	cellFormatter.setColSpan(0, 0, 2);
	cellFormatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidget(0, 0, new Image("images/app/logo.png"));

	layout.setWidget(1, 1, username);

	layout.setWidget(2, 1, passwd);

	layout.setWidget(3, 1, loginButton);

	form.addFormItem(username);
	form.addFormItem(passwd);

	DecoratorPanel decPanel = new DecoratorPanel();
	decPanel.setWidget(layout);
	return decPanel;
    }

    @Override
    public void setPresenter(Presenter presenter) {

    }

    @Override
    public void init() {
	Window.setTitle("ChurchGURU");
	addHandler();
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		username.focus();
	    }
	});
    }

    private void addHandler() {
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
    }

    private void handle() {
	if (form.validateForm()) {
	    LoginAction action = new LoginAction();
	    action.setUsername(username.getValue());
	    action.setPassword(passwd.getValue());
	    DispatchServiceAsync dispatchService = GWT
		    .create(DispatchService.class);
	    DispatchClient dispatchClient = new DispatchClient(dispatchService,
		    null, new DefaultExceptionHandler());
	    UserContext.getInstance().initDispatchClient(dispatchClient);
	    UserContext.getInstance().getDispatchClient()
		    .execute(action, new AsyncCallback<LoginResult>() {
			@Override
			public void onFailure(Throwable throwable) {
			    ErrorMessageDialog.getInstance().handleError(
				    throwable);
			}

			@Override
			public void onSuccess(LoginResult result) {
			    Cookies.setCookie(ApplicationConstants.instance
				    .sessionIdName(), result.getSessionId());
			    Window.Location.reload();
			}
		    });
	}
    }
}
