package com.laotek.churchguru.web.clientm.activity.prayerrequest;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextArea;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class PrayerRequestViewImpl extends DetailViewGwtImpl implements
	PrayerRequestView {
    private static final int MAX_MESSAGE_WORDS = 250;
    private static final int MAX_MESSAGE_CHARS = 1000;
    private Presenter presenter;
    private FlowPanel formContainer = new FlowPanel();

    private MRadioButton missTitleRadio = new MRadioButton("title");
    private MRadioButton mrsTitleRadio = new MRadioButton("title");
    private MRadioButton mrTitleRadio = new MRadioButton("title");
    private HorizontalPanel titlePanel = new HorizontalPanel();
    private FormEntry titleFormEntry = new FormEntry("Title", titlePanel);

    private MTextBox forename = new MTextBox();
    private FormEntry forenameFormEntry = new FormEntry("Forename", forename);

    private MTextBox surname = new MTextBox();
    private FormEntry surnameFormEntry = new FormEntry("Surname", surname);

    private MEmailTextBox emailAddress = new MEmailTextBox();
    private FormEntry emailAddressFormEntry = new FormEntry("Email Address",
	    emailAddress);

    private MTextBox mobile = new MTextBox();
    private FormEntry mobileFormEntry = new FormEntry("Mobile No", mobile);

    private MTextArea message = new MTextArea();
    private FormEntry messageFormEntry = new FormEntry("Message", message);

    // private MTextBox messageSizeWarning = new MTextBox();

    private Button paypalButton = new Button("Submit Request");

    public PrayerRequestViewImpl() {

	HTML html = new HTML(
		"View is under construction, please try back later");
	html.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");
	html.setHeight("25px");

	scrollPanel.add(html);
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	message.setHeight("250px");
	addPicture(formContainer);
	addPrayerRequestDetailsPanel(formContainer);
	addButtons(formContainer);

	// messageSizeWarning.setEnabled(false);
	// messageSizeWarning.setReadOnly(false);

    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void showForm() {
	scrollPanel.setWidget(formContainer);

	forename.setEnabled(true);
	forename.setValue("");
	surname.setEnabled(true);
	surname.setValue("");
	emailAddress.setEnabled(true);
	emailAddress.setValue("");
	mobile.setEnabled(true);
	mobile.setValue("");
	message.setEnabled(true);
	message.setValue("");
	paypalButton.setDisabled(false);

	handleOnLoad(formContainer.getElement());
    }

    private void addPicture(FlowPanel container) {
	Image givePic = new Image(
		"/uploadedphotos/photos/org/prayerrequest?width="
			+ Window.getClientWidth());
	givePic.setWidth("100%");
	container.add(givePic);
	container.add(new HTML("&nbsp;"));
    }

    private void addPrayerRequestDetailsPanel(FlowPanel container) {
	Form prayerRequestForm = new Form();
	prayerRequestForm.setHeader(new HeaderLabel("Prayer Request Form"));

	titlePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	titlePanel.setWidth("100%");

	missTitleRadio.setText("Miss");
	mrsTitleRadio.setText("Mrs");
	mrTitleRadio.setText("Mr");

	missTitleRadio.setStylePrimaryName("normalLabel");
	mrsTitleRadio.setStylePrimaryName("normalLabel");
	mrTitleRadio.setStylePrimaryName("normalLabel");

	titlePanel.add(missTitleRadio);
	titlePanel.add(mrsTitleRadio);
	titlePanel.add(mrTitleRadio);
	prayerRequestForm.add(titleFormEntry);

	updateWidgetFromCookie(forename, "forename");
	prayerRequestForm.add(forenameFormEntry);
	forename.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(forename, "forename");
		    surname.setFocus(true);
		    scrollPanel.refresh();
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			updateCookie(forename, "forename");
			surname.setFocus(true);
			scrollPanel.refresh();
		    }
		}
	    }
	});

	updateWidgetFromCookie(surname, "surname");
	prayerRequestForm.add(surnameFormEntry);
	surname.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(surname, "surname");
		    mobile.setFocus(true);
		    scrollPanel.refresh();
		} else {
		    updateCookie(surname, "surname");
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			mobile.setFocus(true);
			scrollPanel.refresh();
		    }
		}
	    }
	});

	updateWidgetFromCookie(mobile, "mobile");
	prayerRequestForm.add(mobileFormEntry);

	mobile.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(emailAddress, "emailAddress");
		    emailAddress.setFocus(true);
		    scrollPanel.refresh();
		} else {
		    updateCookie(emailAddress, "emailAddress");
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			emailAddress.setFocus(true);
			scrollPanel.refresh();
		    }
		}
	    }
	});

	updateWidgetFromCookie(emailAddress, "emailAddress");
	prayerRequestForm.add(emailAddressFormEntry);
	emailAddress.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    message.setFocus(true);
		    scrollPanel.refresh();
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			message.setFocus(true);
			scrollPanel.refresh();
		    }
		}
	    }
	});

	message.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		scrollPanel.refresh();
	    }
	});

	message.addKeyUpHandler(new KeyUpHandler() {
	    @Override
	    public void onKeyUp(KeyUpEvent event) {
		final String text = message.getText();
		if (text != null) {
		    String words[] = text.split("\\s");

		    // messageSizeWarning.setText("W:"
		    // + (MAX_MESSAGE_WORDS - words.length) + " T:"
		    // + (MAX_MESSAGE_CHARS - text.length()));

		    if (text.length() > MAX_MESSAGE_CHARS
			    || words.length > MAX_MESSAGE_WORDS) {
			Dialogs.alert(
				"Prayer request message",
				"You have exceeded the limit of 250 words or 1000 characters. Please review your message.",
				new AlertCallback() {
				    @Override
				    public void onButtonPressed() {
					message.setText(text.substring(0, 1000));
					messageFormEntry.setValid(false);
				    }
				});
		    }
		}
	    }
	});

	prayerRequestForm.add(messageFormEntry);

	// prayerRequestForm.add(new FormEntry("", messageSizeWarning));

	container.add(prayerRequestForm);
    }

    private void updateCookie(MTextBox box, String label) {
	String value = box.getText();
	updateCookie(label, value);
    }

    private void updateCookie(String name, String value) {
	BigDecimal time = new BigDecimal((new Date()).getTime());
	time.add(new BigDecimal(1000).multiply(new BigDecimal(60)
		.multiply(new BigDecimal(60).multiply(new BigDecimal(24)
			.multiply(new BigDecimal(180))))));
	Cookies.setCookie(name, value);
    }

    private void updateWidgetFromCookie(MTextBox widget, String label) {
	String value = Cookies.getCookie(label);
	if (value != null) {
	    widget.setText(value);
	}
    }

    public void addButtons(FlowPanel container) {
	HTML spacer = new HTML();
	spacer.setHeight("15px");
	container.add(spacer);

	paypalButton.setRound(true);
	paypalButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {

		String title = null;
		if (missTitleRadio.getValue()) {
		    title = missTitleRadio.getText();
		} else if (mrsTitleRadio.getValue()) {
		    title = mrsTitleRadio.getText();
		} else if (mrTitleRadio.getValue()) {
		    title = mrTitleRadio.getText();
		} else {
		    Dialogs.alert("Prayer request details",
			    "Please select your title", new AlertCallback() {
				@Override
				public void onButtonPressed() {
				    titleFormEntry.setValid(false);
				}
			    });
		    return;
		}
		titleFormEntry.setValid(true);

		String value = forename.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Prayer request details",
			    "Please provide your forename",
			    new AlertCallback() {
				@Override
				public void onButtonPressed() {
				    forenameFormEntry.setValid(false);
				}
			    });
		    return;
		}
		forenameFormEntry.setValid(true);

		value = surname.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Prayer request details",
			    "Please provide your surname", new AlertCallback() {

				@Override
				public void onButtonPressed() {
				    surnameFormEntry.setValid(false);
				}
			    });
		    return;
		}
		surnameFormEntry.setValid(true);

		if (!isEmpty(emailAddress.getValue())
			&& !emailAddress
				.getValue()
				.matches(
					"^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$")) {
		    Dialogs.alert("Prayer request details",
			    "Please provide a valid email address",
			    new AlertCallback() {
				@Override
				public void onButtonPressed() {
				    emailAddressFormEntry.setValid(false);
				}
			    });
		    return;
		}
		emailAddressFormEntry.setValid(true);

		value = message.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Prayer request details",
			    "Please provide your prayer request message",
			    new AlertCallback() {
				@Override
				public void onButtonPressed() {
				    messageFormEntry.setValid(false);
				}
			    });
		    return;
		}
		messageFormEntry.setValid(true);

		final String text = message.getText();
		if (text != null) {
		    String words[] = text.split("\\s");
		    if (text.length() > MAX_MESSAGE_CHARS
			    || words.length > MAX_MESSAGE_WORDS) {
			Dialogs.alert(
				"Prayer request message",
				"You have exceeded the limit of 250 words or 1000 characters. Please review your message.",
				new AlertCallback() {
				    @Override
				    public void onButtonPressed() {
					message.setText(text.substring(0, 1000));
					messageFormEntry.setValid(false);
				    }
				});
			return;
		    }
		    messageFormEntry.setValid(true);
		}

		final String selectedTitle = title;
		Dialogs.confirm(
			"Submit prayer request now",
			"Are you sure you want to submit your prayer request now",
			new ConfirmCallback() {

			    @Override
			    public void onOk() {
				presenter.submit(selectedTitle,
					forename.getValue(),
					surname.getValue(), mobile.getValue(),
					emailAddress.getValue(),
					message.getValue());
			    }

			    @Override
			    public void onCancel() {

			    }
			});
	    }
	});

	HTML buttonSpacer = new HTML();
	buttonSpacer.setWidth("5px");

	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.setWidth("100%");
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.add(paypalButton);
	container.add(buttonPanel);

	spacer = new HTML();
	spacer.setHeight("15px");
	container.add(spacer);
    }

    private boolean isEmpty(String value) {
	return "".equals(value) || value == null;
    }

    @Override
    public void disableAndAlert() {
	forename.setEnabled(false);
	surname.setEnabled(false);
	emailAddress.setEnabled(false);
	mobile.setEnabled(false);
	message.setEnabled(false);
	paypalButton.setDisabled(true);

	Dialogs.alert("Prayer request details sent",
		"Prayer request message has been sent", new AlertCallback() {

		    @Override
		    public void onButtonPressed() {
			MobileContext.getInstance().getClientFactory()
				.getPlaceController()
				.goTo(new MobileHomePlace("home"));
		    }
		});
    }

    private void refreshPull() {
	scrollPanel.scrollTo(0, 0);
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{

							   var instance=this;

							   var func = function() {

							   instance.@com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestViewImpl::refreshPull()();

							   };

							   jso.addEventListener("load", func, true);

							   }-*/;
}
