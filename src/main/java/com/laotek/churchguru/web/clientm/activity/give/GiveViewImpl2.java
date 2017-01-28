package com.laotek.churchguru.web.clientm.activity.give;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.input.checkbox.MCheckBox;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.input.radio.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class GiveViewImpl2 extends DetailViewGwtImpl implements GiveView {
    private Presenter presenter;
    private MRadioButton offering = new MRadioButton("givingType");
    private MRadioButton tithe = new MRadioButton("givingType");
    private MRadioButton buildingFund = new MRadioButton("givingType");
    private MRadioButton thanksGiving = new MRadioButton("givingType");
    private MRadioButton specialGiving = new MRadioButton("givingType");
    private MRadioButton otherGiving = new MRadioButton("givingType");

    private MRadioButton choice25Box = new MRadioButton("amountChoice");
    private MRadioButton choice50Box = new MRadioButton("amountChoice");
    private MRadioButton choice75Box = new MRadioButton("amountChoice");
    private MRadioButton choice100Box = new MRadioButton("amountChoice");
    private MRadioButton choiceOtherBox = new MRadioButton("amountChoice");
    private MNumberTextBox amountBox = new MNumberTextBox();

    private MTextBox otherGivingType = new MTextBox();
    private MCheckBox today = new MCheckBox();
    private MCheckBox past4Years = new MCheckBox();
    private MCheckBox future = new MCheckBox();
    private MEmailTextBox emailBox = new MEmailTextBox();
    private MListBox title = new MListBox();
    private MTextBox forename = new MTextBox();
    private MTextBox surname = new MTextBox();
    private MTextBox addressLine1 = new MTextBox();
    private MTextBox addressLine2 = new MTextBox();
    private MTextBox postcode = new MTextBox();
    private MRadioButton inMailingList = new MRadioButton("mailingList");
    private MRadioButton notInMailingList = new MRadioButton("mailingList");
    private MRadioButton notMember = new MRadioButton("member");
    private MRadioButton member = new MRadioButton("member");

    private FlowPanel formContainer = new FlowPanel();
    private FlowPanel thanksContainer = new FlowPanel();
    private FlowPanel progressBarContainer = new FlowPanel();

    public GiveViewImpl2() {

	HTML spacer = new HTML("");
	spacer.setHeight("15px");
	progressBarContainer.add(spacer);
	progressBarContainer.add(new ProgressBar());

	HTML html = new HTML("Loading Paypal, please wait...");
	html.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");
	progressBarContainer.add(html);

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	addThanksPanel(thanksContainer);

	addPicture(formContainer);

	addGivingPanel(formContainer);

	addAmountPanel(formContainer);

	addMembershipPanel(formContainer);

	addGiftAidDeclarationPanel(formContainer);

	donorDetailsPanel(formContainer);

	addPayButton(formContainer);

    }

    private void addPicture(FlowPanel container) {
	Image givePic = new Image("/uploadedphotos/photos/org/give");
	givePic.setWidth("100%");
	givePic.setHeight("100px");
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
    }

    @Override
    public void showThanks() {
	scrollPanel.remove(progressBarContainer);
	scrollPanel.remove(formContainer);
	// clearBrowserHistory();
	scrollPanel.setWidget(thanksContainer);
    }

    @Override
    public void goTo(String approvalUrl) {
	Window.Location.replace(approvalUrl);
    }

    private void addThanksPanel(FlowPanel thanksContainer) {
	HTML spacer = new HTML("");
	spacer.setHeight("15px");
	thanksContainer.add(spacer);

	HTML html = new HTML("Thanks for giving.");
	html.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");
	thanksContainer.add(html);

	Button roundButton = new Button("Go home");
	roundButton.setRound(true);
	roundButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		MobileContext.getInstance().getClientFactory()
			.getPlaceController().goTo(new MobileHomePlace("home"));
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

	Button roundButton = new Button("Pay with PayPal");
	roundButton.setRound(true);
	roundButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		final Map<String, String> giveDetails = GiveDto.getInstance()
			.getGiveDetails();

		// offering
		if (!offering.getValue() && !buildingFund.getValue()
			&& !tithe.getValue() && !otherGiving.getValue()
			&& !thanksGiving.getValue()
			&& !specialGiving.getValue()) {
		    Dialogs.alert("Offering ",
			    "Please choose the offering type", null);
		    return;

		} else if (offering.getValue()) {
		    giveDetails.put("offeringType",
			    DonationType.OFFERING.name());

		} else if (tithe.getValue()) {
		    giveDetails.put("offeringType", DonationType.TITHE.name());

		} else if (buildingFund.getValue()) {
		    giveDetails.put("offeringType",
			    DonationType.BUILDING_FUND.name());

		} else if (thanksGiving.getValue()) {
		    giveDetails.put("offeringType",
			    DonationType.THANKSGIVING.name());

		} else if (specialGiving.getValue()) {
		    giveDetails.put("offeringType",
			    DonationType.SPECIAL_OFFERING.name());

		} else if (otherGiving.getValue()) {
		    String otherGivingTypeText = otherGivingType.getValue();
		    if (isEmpty(otherGivingTypeText)) {
			Dialogs.alert("Offering ",
				"Please specify the other offering", null);
			return;
		    } else {
			giveDetails.put("offeringType",
				DonationType.OTHER.name());
			giveDetails.put("otherGivingType", otherGivingTypeText);
		    }
		}

		// amount
		if (!choice25Box.getValue() && !choice50Box.getValue()
			&& !choice50Box.getValue() && !choice75Box.getValue()
			&& !choice100Box.getValue()
			&& !choiceOtherBox.getValue()) {
		    Dialogs.alert("Amount ", "Please choose your amount", null);
		    return;

		} else if (choice25Box.getValue()) {
		    giveDetails.put("amount", "25.00");

		} else if (choice50Box.getValue()) {
		    giveDetails.put("amount", "50.00");

		} else if (choice75Box.getValue()) {
		    giveDetails.put("amount", "75.00");

		} else if (choice100Box.getValue()) {
		    giveDetails.put("amount", "100.00");

		} else if (choiceOtherBox.getValue()) {

		    // amount
		    String amount = amountBox.getValue();
		    if (isEmpty(amount)) {
			Dialogs.alert("Amount",
				"Please specify the other amount", null);
			return;
		    } else if (!validateAmount(amount)) {
			Dialogs.alert(
				"Amount",
				"Invalid format specified. Please use format ##.##",
				null);
			return;

		    } else {
			giveDetails.put("amount", amount);
		    }
		}

		giveDetails.put("gift-aid-today",
			String.valueOf(today.getValue()));
		giveDetails.put("gift-aid-past4Years",
			String.valueOf(past4Years.getValue()));
		giveDetails.put("gift-aid-future",
			String.valueOf(future.getValue()));

		if (!notMember.getValue() && !member.getValue()) {
		    Dialogs.alert("Membership",
			    "Please tell us if you're a member", null);
		    return;
		} else if (notMember.getValue()) {
		    giveDetails.put("member", "false");
		} else if (member.getValue()) {
		    giveDetails.put("member", "true");
		}

		if (!inMailingList.getValue() && !notInMailingList.getValue()) {
		    Dialogs.alert("Mailing list ",
			    "Please tell us if you're on our mailing list",
			    null);
		    return;

		} else if (inMailingList.getValue()) {
		    giveDetails.put("inMailingList", "true");
		} else if (notInMailingList.getValue()) {
		    giveDetails.put("inMailingList", "false");
		}

		String value = emailBox.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details",
			    "Please provide your email address", null);
		    return;
		} else {
		    giveDetails.put("emailAddress", value);
		}

		value = title.getValue(title.getSelectedIndex());
		if (value.equalsIgnoreCase("title")) {
		    Dialogs.alert("Donor details", "Please provide your title",
			    null);
		    return;
		} else {
		    giveDetails.put("title", value);
		}

		value = forename.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details",
			    "Please provide your forename", null);
		    return;
		} else {
		    giveDetails.put("forenames", value);
		}

		value = surname.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details",
			    "Please provide your surname", null);
		    return;
		} else {
		    giveDetails.put("surname", value);
		}

		value = addressLine1.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details",
			    "Please provide your address line 1", null);
		    return;
		} else {
		    giveDetails.put("addressLine1", value);
		}

		value = addressLine2.getValue();
		giveDetails.put("addressLine2", value);

		value = postcode.getValue();
		if (isEmpty(value)) {
		    Dialogs.alert("Donor details",
			    "Please provide your postcode", null);
		    return;

		} else {
		    giveDetails.put("postcode", value);
		}

		Dialogs.confirm("Confirm submission",
			"Are you ready to pay with Paypal?",
			new ConfirmCallback() {

			    @Override
			    public void onOk() {
				presenter.submit(giveDetails, null);
			    }

			    @Override
			    public void onCancel() {
				// TODO Auto-generated method stub

			    }
			});

	    }
	});

	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.setWidth("100%");
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.add(roundButton);
	container.add(buttonPanel);

	spacer = new HTML();
	spacer.setHeight("15px");
	container.add(spacer);
    }

    private void addAmountPanel(FlowPanel container) {
	WidgetList amountList = new WidgetList();
	amountList.setHeader(new HeaderLabel("Amount"));

	choice25Box.setText("\u00A325 GBP");
	style(choice25Box);
	amountList.add(choice25Box);

	choice50Box.setText("\u00A350 GBP");
	style(choice50Box);
	amountList.add(choice50Box);

	choice75Box.setText("\u00A375 GBP");
	style(choice75Box);
	amountList.add(choice75Box);

	choice100Box.setText("\u00A3100 GBP");
	style(choice100Box);
	amountList.add(choice100Box);

	choiceOtherBox.setText("Other");
	style(choiceOtherBox);
	amountList.add(choiceOtherBox);

	choiceOtherBox.addTapHandler(new TapHandler() {

	    @Override
	    public void onTap(TapEvent event) {
		boolean value = ((MRadioButton) event.getSource()).getValue();
		if (value) {
		    amountBox.setEnabled(true);
		    amountBox.setReadOnly(false);
		    amountBox.setPlaceHolder("0.00");
		} else {
		    amountBox.setEnabled(false);
		    amountBox.setReadOnly(true);
		    amountBox.setPlaceHolder("0.00");
		}

	    }
	});

	amountBox.setPlaceHolder("0.00");
	amountList.add(amountBox);
	container.add(amountList);
    }

    private void addGivingPanel(FlowPanel container) {
	WidgetList giveTypeList = new WidgetList();
	giveTypeList.setHeader(new HeaderLabel("Giving"));

	offering.setText("Offering");
	style(offering);
	giveTypeList.add(offering);

	tithe.setText("Tithe");
	style(tithe);
	giveTypeList.add(tithe);

	buildingFund.setText("Building Fund");
	style(buildingFund);
	giveTypeList.add(buildingFund);

	thanksGiving.setText("Thanksgiving");
	style(thanksGiving);
	giveTypeList.add(thanksGiving);

	specialGiving.setText("Special Offering");
	style(specialGiving);
	giveTypeList.add(specialGiving);

	otherGiving.setText("Other(Please specify)");
	style(otherGiving);
	giveTypeList.add(otherGiving);

	otherGivingType.setPlaceHolder("Other");
	style(otherGivingType);
	giveTypeList.add(otherGivingType);

	otherGiving.addTapHandler(new TapHandler() {

	    @Override
	    public void onTap(TapEvent event) {
		boolean value = ((MRadioButton) event.getSource()).getValue();
		if (value) {
		    otherGivingType.setEnabled(true);
		} else {
		    otherGivingType.setEnabled(false);
		    otherGivingType.setValue("");
		}

	    }
	});
	otherGiving.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

	    @Override
	    public void onValueChange(ValueChangeEvent<Boolean> event) {
		if (event.getValue()) {
		    otherGivingType.setEnabled(true);
		} else {
		    otherGivingType.setEnabled(false);
		    otherGivingType.setValue("");
		}
	    }
	});

	container.add(giveTypeList);
    }

    private void addGiftAidDeclarationPanel(FlowPanel container) {
	WidgetList giveTypeList = new WidgetList();
	giveTypeList.setHeader(new HeaderLabel("Gift Aid Declaration"));

	giveTypeList
		.add(new Label(
			"Please treat as Gift Aid donations all qualifiying gifts of money made"));

	today.setTitle("today");
	today.setValue(false);
	updateWidgetFromCookie(today, "today");
	today.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(today, "today");
	    }
	});
	HorizontalPanel hp = new HorizontalPanel();
	hp.add(today);
	hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	hp.add(new HTML("&nbsp;Today"));
	giveTypeList.add(hp);

	past4Years.setTitle("inThePast4Years");
	past4Years.setValue(false);
	updateWidgetFromCookie(past4Years, "past4Years");
	past4Years.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(past4Years, "past4Years");
	    }
	});
	hp = new HorizontalPanel();
	hp.add(past4Years);
	hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	hp.add(new HTML("&nbsp; In the past 4 years"));
	giveTypeList.add(hp);

	future.setTitle("future");
	future.setValue(false);
	updateWidgetFromCookie(future, "future");
	future.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		updateCookie(future, "future");
	    }
	});
	hp = new HorizontalPanel();
	hp.add(future);
	hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	hp.add(new HTML("&nbsp; In the future"));
	giveTypeList.add(hp);

	container.add(giveTypeList);
    }

    private void donorDetailsPanel(FlowPanel container) {
	WidgetList donorPanel = new WidgetList();
	donorPanel.setHeader(new HeaderLabel("Donor Details"));

	emailBox.setPlaceHolder("email address");
	updateWidgetFromCookie(emailBox, "emailBox");
	donorPanel.add(emailBox);
	emailBox.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    updateCookie(emailBox, "emailBox");
		    title.setFocus(true);
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			updateCookie(emailBox, "emailBox");
			title.setFocus(true);
		    }
		}
	    }
	});

	title.addItem("Title");
	title.addItem("Miss");
	title.addItem("Mr");
	title.addItem("Mrs");
	donorPanel.add(title);

	forename.setPlaceHolder("Forename");
	updateWidgetFromCookie(forename, "forename");
	style(forename);
	donorPanel.add(forename);
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

	surname.setPlaceHolder("Surname");
	updateWidgetFromCookie(surname, "surname");
	style(surname);
	donorPanel.add(surname);
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

	addressLine1.setPlaceHolder("Address Line 1");
	updateWidgetFromCookie(addressLine1, "addressLine1");
	style(addressLine1);
	donorPanel.add(addressLine1);
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

	addressLine2.setPlaceHolder("Address Line 2");
	updateWidgetFromCookie(addressLine2, "addressLine2");
	style(addressLine2);
	donorPanel.add(addressLine2);
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
	style(postcode);
	donorPanel.add(postcode);
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

	container.add(donorPanel);
    }

    private void addMembershipPanel(FlowPanel container) {
	WidgetList membershipPanel = new WidgetList();
	membershipPanel.setHeader(new HeaderLabel("Membership"));

	member.setText("Member");
	style(member);
	membershipPanel.add(member);

	notMember.setText("Not Member");
	style(notMember);
	membershipPanel.add(notMember);
	container.add(membershipPanel);

	WidgetList mailingListPanel = new WidgetList();
	mailingListPanel.setHeader(new HeaderLabel("Mailing List"));

	inMailingList.setText("In mailing list");
	style(inMailingList);
	mailingListPanel.add(inMailingList);

	notInMailingList.setText("Not in mailing list");
	style(notInMailingList);
	mailingListPanel.add(notInMailingList);

	container.add(mailingListPanel);
    }

    private boolean isEmpty(String value) {
	return "".equals(value) || value == null;
    }

    private boolean validateAmount(String value) {
	if (isEmpty(value)) {
	    return false;
	} else {
	    return value
		    .matches("^[+-]?[0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{2})?|(?:,[0-9]{3})*(?:\\.[0-9]{2})?|(?:\\.[0-9]{3})*(?:,[0-9]{2})?)$");
	}
    }

    @Override
    public void showErrorMessage(String message) {
	Dialogs.alert("Error ", message, null);
    }

    private void style(MRadioButton widget) {
	widget.labelElement.addClassName("normalLabel");
    }

    private void style(MTextBox widget) {
	// widget.getElement().setClassName("normalLabel");
	widget.setStylePrimaryName("normalLabel");
	widget.getElement().addClassName("normalLabel");
	widget.addStyleName("normalLabel");
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

    private void updateWidgetFromCookie(MCheckBox widget, String label) {
	String value = Cookies.getCookie(label);
	if (value != null) {
	    widget.setValue(Boolean.valueOf(value));
	}
    }
}
