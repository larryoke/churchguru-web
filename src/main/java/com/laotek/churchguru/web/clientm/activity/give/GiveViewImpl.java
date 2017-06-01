package com.laotek.churchguru.web.clientm.activity.give;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

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
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs.AlertCallback;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class GiveViewImpl extends DetailViewGwtImpl implements GiveView {
    private Presenter presenter;

    private MCheckBox showMoreGivingOptionsCheckBox = new MCheckBox();

    private MNumberTextBox offeringOtherEntry = new MNumberTextBox();
    private MNumberTextBox titheOtherEntry = new MNumberTextBox();
    private MNumberTextBox buildingFundOtherEntry = new MNumberTextBox();
    private MNumberTextBox thanksgivingOtherEntry = new MNumberTextBox();
    private MNumberTextBox specialGivingEntry = new MNumberTextBox();
    private MNumberTextBox otherGivingEntry = new MNumberTextBox();
    private MTextBox givingTotalBox = new MTextBox();

    private BigDecimal totalGiving;

    private FormEntry offeringFormEntry = new FormEntry("Offering", offeringOtherEntry);

    private FormEntry titheFormEntry = new FormEntry("Tithe", titheOtherEntry);

    private FormEntry buildingFundFormEntry = new FormEntry("Building Fund", buildingFundOtherEntry);

    private FormEntry thanksgivingFormEntry = new FormEntry("Thanksgiving", thanksgivingOtherEntry);

    private FormEntry specialOfferingFormEntry = new FormEntry("Special Offering", specialGivingEntry);

    private FormEntry otherOfferingFormEntry = new FormEntry("Other(Amount)", otherGivingEntry);

    private MTextBox otherGivingDescEntry = new MTextBox();
    private FormEntry otherGivingDescFormEntry = new FormEntry("Other(Please specify)", otherGivingDescEntry);

    private FormEntry givingTotalBoxFormEntry = new FormEntry("Total", givingTotalBox);

    private MCheckBox treatAsGiftAidCheckBox = new MCheckBox();
    private MCheckBox today = new MCheckBox();
    private MCheckBox past4Years = new MCheckBox();
    private MCheckBox future = new MCheckBox();

    private HorizontalPanel giftAidTodayPanel = new HorizontalPanel();
    private HorizontalPanel giftAidPast4YearsPanel = new HorizontalPanel();
    private HorizontalPanel giftAidFuturePanel = new HorizontalPanel();

    private MCheckBox isMember = new MCheckBox();
    private MCheckBox inMailingList = new MCheckBox();

    private MEmailTextBox emailBox = new MEmailTextBox();
    private FormEntry emailBoxFormEntry = new FormEntry("Email Address", emailBox);

    private MRadioButton missTitleRadio = new MRadioButton("title");
    private MRadioButton mrsTitleRadio = new MRadioButton("title");
    private MRadioButton mrTitleRadio = new MRadioButton("title");
    private HorizontalPanel titlePanel = new HorizontalPanel();
    private FormEntry titleFormEntry = new FormEntry("Title", titlePanel);

    private MTextBox forename = new MTextBox();
    private FormEntry forenameFormEntry = new FormEntry("Forename", forename);

    private MTextBox surname = new MTextBox();
    private FormEntry surnameFormEntry = new FormEntry("Surname", surname);

    private HTML giftAidTodayLabel = new HTML("&nbsp;&nbsp;&nbsp; Today");
    private HTML giftAidPast4YearsLabel = new HTML("&nbsp;&nbsp;&nbsp; In the past 4 years");
    private HTML giftAidInTheFutureLabel = new HTML("&nbsp;&nbsp;&nbsp; In the future");

    private Form addressLines = new Form();
    private MTextBox addressLine1 = new MTextBox();
    private FormEntry addressLine1FormEntry = new FormEntry("Address Line1", addressLine1);

    private MTextBox addressLine2 = new MTextBox();
    private FormEntry addressLine2FormEntry = new FormEntry("Address Line2", addressLine2);

    private MTextBox postcode = new MTextBox();
    private FormEntry postcodeFormEntry = new FormEntry("Postcode", postcode);

    private FlowPanel formContainer = new FlowPanel();
    private FlowPanel thanksContainer = new FlowPanel();
    private FlowPanel progressBarContainer = new FlowPanel();

    public GiveViewImpl() {

	super(!MGWT.getOsDetection().isIPhone());
	HTML spacer = new HTML("");
	spacer.setHeight("15px");
	progressBarContainer.add(spacer);
	progressBarContainer.add(new ProgressBar());

	HTML html = new HTML("Loading Paypal, please wait...");
	html.getElement().setAttribute("style", "text-align: center; padding: 20px;");
	progressBarContainer.add(html);

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	addThanksPanel(thanksContainer);

	addPicture(formContainer);

	addGivingPanel(formContainer);

	donorDetailsPanel(formContainer);

	addMembershipPanel(formContainer);

	addGiftAidDeclarationPanel(formContainer);

	addPayButton(formContainer);

    }

    private void addPicture(FlowPanel container) {
	Image givePic = new Image("/uploadedphotos/photos/org/give?width=" + Window.getClientWidth());
	givePic.setWidth("100%");
	// givePic.setHeight("100px");
	container.add(givePic);
	container.add(new HTML("&nbsp;"));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void pleaseWaitLoadingPaypal() {
	scrollPanel.remove(formContainer);
	scrollPanel.remove(thanksContainer);
	scrollPanel.setWidget(progressBarContainer);
    }

    @Override
    public void showForm() {
	scrollPanel.remove(progressBarContainer);
	scrollPanel.setWidget(formContainer);
	handleOnLoad(formContainer.getElement());
    }

    @Override
    public void showThanks() {
	scrollPanel.remove(progressBarContainer);
	scrollPanel.remove(formContainer);
	// clearBrowserHistory();
	scrollPanel.setWidget(thanksContainer);
    }

    @Override
    public native void goTo(String approvalUrl) /*-{
						window.location = 'trinitychapel://somehost?approvalUrl=' + approvalUrl;				       
						}-*/;

    private void addThanksPanel(FlowPanel thanksContainer) {
	HTML spacer = new HTML("");
	spacer.setHeight("15px");
	thanksContainer.add(spacer);

	HTML html = new HTML("Thanks for giving.");
	html.getElement().setAttribute("style", "text-align: center; padding: 20px;");
	thanksContainer.add(html);

	Button roundButton = new Button("Go home");
	roundButton.setRound(true);
	roundButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		MobileContext.getInstance().getClientFactory().getPlaceController().goTo(new MobileHomePlace("home"));
	    }
	});

	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.setWidth("100%");
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.add(roundButton);
	thanksContainer.add(buttonPanel);
    }

    private native void clearBrowserHistory() /*-{
					       if(typeof $wnd.androidAppProxy !== "undefined")
					       $wnd.androidAppProxy.clearBrowserHistory();
					       else
					       alert("Running outside Android app");					       
					       }-*/;

    public void addPayButton(FlowPanel container) {
	HTML spacer = new HTML();
	spacer.setHeight("15px");
	container.add(spacer);

	Button paypalButton = new Button("Pay with PayPal");
	paypalButton.setRound(true);
	paypalButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		final Map<String, String> giveDetails = GiveDto.getInstance().getGiveDetails();

		final Map<DonationType, BigDecimal> payments = GiveDto.getInstance().getDonateDetails();

		updateTotal();

		if (totalGiving.compareTo(new BigDecimal(0)) == 0) {
		    Dialogs.alert("Amout", "No amount entered. \nPlease select a payment type and enter an amount",
			    null);
		    return;
		} else {
		    payments.put(DonationType.OFFERING, getSubTotal(offeringOtherEntry));
		    payments.put(DonationType.TITHE, getSubTotal(titheOtherEntry));
		    payments.put(DonationType.THANKSGIVING, getSubTotal(thanksgivingOtherEntry));
		    payments.put(DonationType.SPECIAL_OFFERING, getSubTotal(specialGivingEntry));
		    payments.put(DonationType.BUILDING_FUND, getSubTotal(buildingFundOtherEntry));
		    payments.put(DonationType.OTHER, getSubTotal(otherGivingEntry));
		}

		if (getSubTotal(otherGivingEntry).compareTo(new BigDecimal(0)) != 0
			&& isEmpty(otherGivingDescEntry.getValue())) {
		    Dialogs.alert("Other giving",
			    "Please provide a description of the other type of payment to which you're paying \u00A3"
				    + getSubTotal(otherGivingEntry).setScale(2).toString() + " GPB",
			    new AlertCallback() {

				@Override
				public void onButtonPressed() {
				    otherGivingDescFormEntry.setValid(false);
				}
			    });
		    return;
		}
		otherGivingDescFormEntry.setValid(true);

		if (getSubTotal(otherGivingEntry).compareTo(new BigDecimal(0)) == 0
			&& !isEmpty(otherGivingDescEntry.getValue())) {
		    Dialogs.alert("Other giving", "Please enter an amount for the payment described as '"
			    + otherGivingDescEntry.getValue() + "'", new AlertCallback() {

				@Override
				public void onButtonPressed() {
				    otherOfferingFormEntry.setValid(false);
				}
			    });
		    return;
		}
		otherOfferingFormEntry.setValid(true);

		if (treatAsGiftAidCheckBox.getValue()) {
		    if (today.getValue() || past4Years.getValue() || future.getValue()) {
			giveDetails.put("gift-aid-today", String.valueOf(today.getValue()));
			giveDetails.put("gift-aid-past4Years", String.valueOf(past4Years.getValue()));
			giveDetails.put("gift-aid-future", String.valueOf(future.getValue()));
			setGiftAidDatesDeclarationValid(true);

		    } else {
			Dialogs.alert("Gift Aid Declaration",
				"Please select one or more period for the gift aid declaration", new AlertCallback() {

				    @Override
				    public void onButtonPressed() {
					setGiftAidDatesDeclarationValid(false);
				    }
				});
			return;
		    }

		    String value = addressLine1.getValue();
		    if (isEmpty(value)) {
			Dialogs.alert("Gift Aid Declaration", "Please provide your address line 1",
				new AlertCallback() {

				    @Override
				    public void onButtonPressed() {
					addressLine1FormEntry.setValid(false);
				    }
				});
			return;
		    } else {
			addressLine1FormEntry.setValid(true);
			giveDetails.put("addressLine1", value);
		    }

		    value = addressLine2.getValue();
		    giveDetails.put("addressLine2", value);

		    value = postcode.getValue();
		    if (isEmpty(value)) {
			Dialogs.alert("Gift Aid Declaration", "Please provide your postcode", new AlertCallback() {

			    @Override
			    public void onButtonPressed() {
				postcodeFormEntry.setValid(false);
			    }
			});
			return;

		    } else {
			postcodeFormEntry.setValid(true);
			giveDetails.put("postcode", value);
		    }
		}

		giveDetails.put("member", String.valueOf(isMember.getValue()));
		giveDetails.put("inMailingList", String.valueOf(inMailingList.getValue()));

		String value = emailBox.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details", "Please provide your email address", new AlertCallback() {

			@Override
			public void onButtonPressed() {
			    emailBoxFormEntry.setValid(false);
			    emailBox.setInvalid(true);
			    emailBox.setFocus(true);
			}
		    });
		    emailBox.setInvalid(true);
		    return;

		} else if (!value.matches("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$")) {
		    Dialogs.alert("Donor details", "Please provide a valid email address", new AlertCallback() {

			@Override
			public void onButtonPressed() {
			    emailBoxFormEntry.setValid(false);
			    emailBox.setInvalid(true);
			    emailBox.setFocus(true);
			}
		    });
		    return;

		} else {
		    emailBoxFormEntry.setValid(true);
		    emailBox.setInvalid(false);
		    giveDetails.put("emailAddress", value);
		}

		if (missTitleRadio.getValue()) {
		    value = missTitleRadio.getText();
		} else if (mrsTitleRadio.getValue()) {
		    value = mrsTitleRadio.getText();
		} else if (mrTitleRadio.getValue()) {
		    value = mrTitleRadio.getText();
		} else {

		    Dialogs.alert("Donor details", "Please provide your title", new AlertCallback() {
			@Override
			public void onButtonPressed() {
			    titleFormEntry.setValid(false);
			}
		    });
		    return;
		}
		titleFormEntry.setValid(true);

		if (value.equals("Miss") || value.equals("Mrs") || value.equals("Mr")) {
		    giveDetails.put("title", value);
		}

		value = forename.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details", "Please provide your forename", new AlertCallback() {

			@Override
			public void onButtonPressed() {
			    forenameFormEntry.setValid(false);
			}
		    });

		    return;
		} else {
		    forenameFormEntry.setValid(true);
		    giveDetails.put("forenames", value);
		}

		value = surname.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details", "Please provide your surname", new AlertCallback() {

			@Override
			public void onButtonPressed() {
			    surnameFormEntry.setValid(false);
			}
		    });
		    return;
		} else {
		    surnameFormEntry.setValid(true);
		    giveDetails.put("surname", value);
		}

		StringBuffer finalMessage = new StringBuffer();
		for (Map.Entry<DonationType, BigDecimal> entry : payments.entrySet()) {
		    if (!entry.getValue().equals(new BigDecimal(0))) {
			finalMessage.append(entry.getKey().getDesc());
			finalMessage.append(": ");
			finalMessage.append("\u00A3");
			finalMessage.append(entry.getValue());
			finalMessage.append(" ");
			finalMessage.append(" GBP");
			finalMessage.append("\n");
		    }
		}
		finalMessage.append("Total: ");
		finalMessage.append("\u00A3");
		finalMessage.append(totalGiving);
		finalMessage.append(" ");
		finalMessage.append(" GBP");
		finalMessage.append("\n");
		finalMessage.append("Are you ready to pay with Paypal?");

		giveDetails.put("isIOs", String.valueOf(MGWT.getOsDetection().isIOs()));

		Dialogs.confirm("Confirm submission", finalMessage.toString(), new ConfirmCallback() {
		    @Override
		    public void onOk() {
			presenter.submit(giveDetails, payments);
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

    private void addGivingPanel(FlowPanel container) {
	final Form giveTypeList = new Form();
	giveTypeList.setHeader(new HeaderLabel("Giving"));

	createFormEntry(true, "Offering", giveTypeList, offeringFormEntry, offeringOtherEntry);

	createFormEntry(true, "Tithe", giveTypeList, titheFormEntry, titheOtherEntry);

	showMoreGivingOptionsCheckBox.setValue(false);

	createFormEntry(showMoreGivingOptionsCheckBox.getValue(), "Building Fund", giveTypeList, buildingFundFormEntry,

		buildingFundOtherEntry);

	createFormEntry(showMoreGivingOptionsCheckBox.getValue(), "Thanksgiving", giveTypeList, thanksgivingFormEntry,

		thanksgivingOtherEntry);

	createFormEntry(showMoreGivingOptionsCheckBox.getValue(), "Special Offering", giveTypeList,
		specialOfferingFormEntry, specialGivingEntry);

	createFormEntry(showMoreGivingOptionsCheckBox.getValue(), "Other Giving", giveTypeList, otherOfferingFormEntry,
		otherGivingEntry);

	if (showMoreGivingOptionsCheckBox.getValue()) {
	    giveTypeList.add(otherGivingDescFormEntry);
	}

	givingTotalBox.setReadOnly(true);
	giveTypeList.add(givingTotalBoxFormEntry);

	showMoreGivingOptionsCheckBox.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		handleOtherGivingOptionsCheckBox(giveTypeList);
	    }
	});

	showMoreGivingOptionsCheckBox.addSwipeEndHandler(new SwipeEndHandler() {
	    @Override
	    public void onSwipeEnd(SwipeEndEvent event) {
		handleOtherGivingOptionsCheckBox(giveTypeList);
	    }
	});

	container.add(giveTypeList);

	HorizontalPanel moreGivingOptionsPanel = new HorizontalPanel();
	moreGivingOptionsPanel.add(showMoreGivingOptionsCheckBox);
	HTML label = new HTML("&nbsp;Show more options");
	label.setStylePrimaryName("normalLabel");
	moreGivingOptionsPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	moreGivingOptionsPanel.add(label);
	WidgetList optionsWidget = new WidgetList();
	optionsWidget.add(moreGivingOptionsPanel);
	container.add(optionsWidget);
    }

    private void createFormEntry(final boolean show, final String label, Form widgetList, final FormEntry formEntry,
	    final MNumberTextBox otherEntry) {
	if (show) {
	    widgetList.add(formEntry);
	}

	otherEntry.addKeyUpHandler(new KeyUpHandler() {
	    @Override
	    public void onKeyUp(KeyUpEvent event) {
		updateTotal();
	    }
	});
    }

    private void updateTotal() {
	BigDecimal offering = getSubTotal(offeringOtherEntry);
	BigDecimal specialGiving = getSubTotal(specialGivingEntry);
	BigDecimal thanksgiving = getSubTotal(thanksgivingOtherEntry);
	BigDecimal building = getSubTotal(buildingFundOtherEntry);
	BigDecimal tithe = getSubTotal(titheOtherEntry);
	BigDecimal otherGiving = getSubTotal(otherGivingEntry);

	totalGiving = offering.add(specialGiving).add(thanksgiving).add(building).add(tithe).add(otherGiving);

	givingTotalBox.setReadOnly(true);
	givingTotalBox.setEnabled(false);
	givingTotalBox.setText("\u00A3" + totalGiving.setScale(2).toPlainString() + " GBP");
    }

    private BigDecimal getSubTotal(MNumberTextBox textBox) {
	String currentValue = textBox.getText();

	if (!"".equals(currentValue) && currentValue != null && currentValue.matches("\\d+(\\.\\d+)*")) {
	    return new BigDecimal(currentValue);
	}

	return new BigDecimal(0);
    }

    private void addGiftAidDeclarationPanel(FlowPanel container) {
	final WidgetList giveTypeList = new WidgetList();
	giveTypeList.setHeader(new HeaderLabel("Gift Aid Declaration"));
	treatAsGiftAidCheckBox.setValue(false);
	treatAsGiftAidCheckBox.addTapHandler(new TapHandler() {

	    @Override
	    public void onTap(TapEvent event) {
		handleTreatAsGiftAidCheckBox(giveTypeList);
	    }
	});
	treatAsGiftAidCheckBox.addSwipeEndHandler(new SwipeEndHandler() {

	    @Override
	    public void onSwipeEnd(SwipeEndEvent event) {
		handleTreatAsGiftAidCheckBox(giveTypeList);
	    }
	});
	HorizontalPanel giftAidPanel = new HorizontalPanel();
	HTML html = new HTML("&nbsp;Please treat as Gift Aid donations all qualifiying gifts of money made");
	html.setStylePrimaryName("normalLabel");
	giftAidPanel.add(treatAsGiftAidCheckBox);
	giftAidPanel.add(html);
	giveTypeList.add(giftAidPanel);

	today.setTitle("today");
	today.setValue(false);
	updateWidgetFromCookie(today, "today");
	today.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(today, "today");
	    }
	});
	today.addSwipeEndHandler(new SwipeEndHandler() {
	    @Override
	    public void onSwipeEnd(SwipeEndEvent event) {
		updateCookie(today, "today");
	    }
	});

	giftAidTodayPanel.add(new HTML("&nbsp;"));
	giftAidTodayPanel.add(today);
	giftAidTodayPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

	giftAidTodayLabel.setStylePrimaryName("normalLabel");
	giftAidTodayPanel.add(giftAidTodayLabel);

	past4Years.setTitle("inThePast4Years");
	past4Years.setValue(false);
	updateWidgetFromCookie(past4Years, "past4Years");
	past4Years.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(past4Years, "past4Years");
	    }
	});
	past4Years.addSwipeEndHandler(new SwipeEndHandler() {
	    @Override
	    public void onSwipeEnd(SwipeEndEvent event) {
		updateCookie(past4Years, "past4Years");
	    }
	});

	giftAidPast4YearsPanel.add(new HTML("&nbsp;"));
	giftAidPast4YearsPanel.add(past4Years);
	giftAidPast4YearsPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	giftAidPast4YearsLabel.setStylePrimaryName("normalLabel");
	giftAidPast4YearsPanel.add(giftAidPast4YearsLabel);

	future.setTitle("future");
	future.setValue(false);
	updateWidgetFromCookie(future, "future");
	future.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(future, "future");
	    }
	});
	future.addSwipeEndHandler(new SwipeEndHandler() {
	    @Override
	    public void onSwipeEnd(SwipeEndEvent event) {
		updateCookie(future, "future");
	    }
	});

	giftAidFuturePanel.add(new HTML("&nbsp;"));
	giftAidFuturePanel.add(future);
	giftAidFuturePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	giftAidInTheFutureLabel.setStylePrimaryName("normalLabel");
	giftAidFuturePanel.add(giftAidInTheFutureLabel);

	updateWidgetFromCookie(addressLine1, "addressLine1");
	addressLine1.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(addressLine1, "addressLine1");
		    addressLine2.setFocus(true);
		} else {
		    updateCookie(addressLine1, "addressLine1");
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			addressLine2.setFocus(true);
		    }
		}
	    }
	});

	updateWidgetFromCookie(addressLine2, "addressLine2");
	addressLine2.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(addressLine2, "addressLine2");
		    postcode.setFocus(true);
		} else {
		    updateCookie(addressLine2, "addressLine2");
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			postcode.setFocus(true);
		    }
		}
	    }
	});

	postcode.setPlaceHolder("Postcode");
	updateWidgetFromCookie(postcode, "postcode");
	postcode.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(postcode, "postcode");
		    postcode.setFocus(false);
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			updateCookie(postcode, "postcode");
			postcode.setFocus(false);
		    }
		}
	    }
	});

	addressLines.add(addressLine1FormEntry);
	addressLines.add(addressLine2FormEntry);
	addressLines.add(postcodeFormEntry);
	container.add(giveTypeList);
    }

    private void donorDetailsPanel(FlowPanel container) {
	Form donorForm = new Form();
	donorForm.setHeader(new HeaderLabel("Donor Details"));

	updateWidgetFromCookie(emailBox, "emailBox");
	donorForm.add(emailBoxFormEntry);
	emailBox.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(emailBox, "emailBox");
		    forename.setFocus(true);
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			updateCookie(emailBox, "emailBox");
			forename.setFocus(true);
		    }
		}
	    }
	});

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
	donorForm.add(titleFormEntry);

	updateWidgetFromCookie(forename, "forename");
	donorForm.add(forenameFormEntry);
	forename.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(forename, "forename");
		    surname.setFocus(true);
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			updateCookie(forename, "forename");
			surname.setFocus(true);
		    }
		}
	    }
	});

	updateWidgetFromCookie(surname, "surname");
	donorForm.add(surnameFormEntry);
	surname.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(surname, "surname");
		    addressLine1.setFocus(true);
		} else {
		    updateCookie(surname, "surname");
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			addressLine1.setFocus(true);
		    }
		}
	    }
	});

	container.add(donorForm);
    }

    private void addMembershipPanel(FlowPanel container) {
	WidgetList membershipPanel = new WidgetList();
	membershipPanel.setHeader(new HeaderLabel("Membership"));

	isMember.setTitle("is member");
	isMember.setValue(false);
	updateWidgetFromCookie(isMember, "isMember");
	isMember.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(isMember, "isMember");
	    }
	});
	HorizontalPanel hp = new HorizontalPanel();
	hp.add(isMember);
	hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	HTML html = new HTML("&nbsp;I'm a member");
	html.setStylePrimaryName("normalLabel");
	hp.add(html);
	membershipPanel.add(hp);

	inMailingList.setTitle("in mailing list");
	inMailingList.setValue(false);
	updateWidgetFromCookie(inMailingList, "inMailingList");
	inMailingList.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(inMailingList, "inMailingList");
	    }
	});
	hp = new HorizontalPanel();
	hp.add(inMailingList);
	hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	html = new HTML("&nbsp;I'm in the mailing list");
	html.setStylePrimaryName("normalLabel");
	hp.add(html);
	membershipPanel.add(hp);
	container.add(membershipPanel);
    }

    private boolean isEmpty(String value) {
	return "".equals(value) || value == null;
    }

    private boolean validateAmount(String value) {
	if (isEmpty(value)) {
	    return false;
	} else {
	    return value.matches(
		    "^[+-]?[0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{2})?|(?:,[0-9]{3})*(?:\\.[0-9]{2})?|(?:\\.[0-9]{3})*(?:,[0-9]{2})?)$");
	}
    }

    @Override
    public void showErrorMessage(String message) {
	Dialogs.alert("Error ", message, null);
    }

    private void style(MRadioButton widget) {
	widget.labelElement.addClassName("normalLabel");
    }

    private void updateCookie(MCheckBox box, String label) {
	boolean value = box.getValue();
	updateCookie(label, String.valueOf(value));
    }

    private void updateCookie(MTextBox box, String label) {
	String value = box.getText();
	updateCookie(label, value);
    }

    private void updateCookie(String name, String value) {
	BigDecimal time = new BigDecimal((new Date()).getTime());
	time.add(new BigDecimal(1000).multiply(new BigDecimal(60)
		.multiply(new BigDecimal(60).multiply(new BigDecimal(24).multiply(new BigDecimal(180))))));
	Cookies.setCookie(name, value);
    }

    private void updateWidgetFromCookie(MTextBox widget, String label) {
	String value = Cookies.getCookie(label);
	if (value != null) {
	    widget.setText(value);
	}
    }

    private void updateWidgetFromCookie(MCheckBox widget, String label) {
	String value = Cookies.getCookie(label);
	if (value != null) {
	    widget.setValue(Boolean.valueOf(value));
	}
    }

    private void handleOtherGivingOptionsCheckBox(final Form giveTypeList) {
	if (showMoreGivingOptionsCheckBox.getValue()) {
	    giveTypeList.remove(otherGivingDescFormEntry);
	    giveTypeList.remove(givingTotalBoxFormEntry);
	    giveTypeList.add(buildingFundFormEntry);
	    giveTypeList.add(thanksgivingFormEntry);
	    giveTypeList.add(specialOfferingFormEntry);
	    giveTypeList.add(otherOfferingFormEntry);
	    giveTypeList.add(otherGivingDescFormEntry);
	    giveTypeList.add(givingTotalBoxFormEntry);
	} else {
	    giveTypeList.remove(buildingFundFormEntry);
	    giveTypeList.remove(thanksgivingFormEntry);
	    giveTypeList.remove(specialOfferingFormEntry);
	    giveTypeList.remove(otherOfferingFormEntry);
	    giveTypeList.remove(otherGivingDescFormEntry);

	}
	scrollPanel.refresh();
    }

    private void handleTreatAsGiftAidCheckBox(final WidgetList giveTypeList) {
	if (treatAsGiftAidCheckBox.getValue()) {
	    giveTypeList.add(giftAidTodayPanel);
	    giveTypeList.add(giftAidPast4YearsPanel);
	    giveTypeList.add(giftAidFuturePanel);
	    giveTypeList.add(addressLines);

	} else {
	    giveTypeList.remove(giftAidTodayPanel);
	    giveTypeList.remove(giftAidPast4YearsPanel);
	    giveTypeList.remove(giftAidFuturePanel);
	    giveTypeList.remove(addressLines);
	}
	scrollPanel.refresh();
    }

    private void setGiftAidDatesDeclarationValid(boolean valid) {
	if (!valid) {
	    giftAidTodayLabel.addStyleName("errorLabel");
	    giftAidPast4YearsLabel.addStyleName("errorLabel");
	    giftAidInTheFutureLabel.addStyleName("errorLabel");
	} else {
	    giftAidTodayLabel.removeStyleName("errorLabel");
	    giftAidPast4YearsLabel.removeStyleName("errorLabel");
	    giftAidInTheFutureLabel.removeStyleName("errorLabel");
	}
    }

    public void refreshPull() {
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.give.GiveViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;
}
