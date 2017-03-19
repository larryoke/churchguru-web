package com.laotek.churchguru.web.client.activity.donation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;

public class DonationSearchViewImpl implements DonationSearchView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "100%";

    private Presenter presenter;

    private PlaceController placeController;

    private FlexTable layout = new FlexTable();

    private FlexTable searchPanel = new FlexTable();
    private FlexTable searchResultPanel = new FlexTable();

    private TextItem surname = new TextItem("Surname", false);

    private TextItem forename = new TextItem("Forenames", false);

    private CheckBoxItem offeringItem = new CheckBoxItem("Offering", false);
    private CheckBoxItem titheItem = new CheckBoxItem("Tithe", false);
    private CheckBoxItem buildingOfferingItem = new CheckBoxItem("Building", false);
    private CheckBoxItem thanksGivingOfferingItem = new CheckBoxItem("Thanksgiving", false);
    private CheckBoxItem specialOfferingItem = new CheckBoxItem("Special Offering", false);
    private CheckBoxItem otherOfferingItem = new CheckBoxItem("Other Offering", false);

    private CheckBoxItem cancelleditem = new CheckBoxItem("Cancelled", false);
    private CheckBoxItem createdItem = new CheckBoxItem("Created", false);
    private CheckBoxItem approvedItem = new CheckBoxItem("Building", false);
    private CheckBoxItem completedItem = new CheckBoxItem("Completed", false);

    private SelectItem maxResultSelect = new SelectItem("Maximum Result", false);

    private DateBox fromDate = new DateBox();

    private DateBox toDate = new DateBox();

    private Button searchButton = new Button("Search");

    public DonationSearchViewImpl(PlaceController placeController) {
	this.placeController = placeController;
	initSearchPanel();
    }

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/search.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>Search Donations</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	layout.setWidget(2, 0, new RoundedCornerPanel(searchPanel));
	layout.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);

	return layout;
    }

    private KeyPressHandler handleSearch() {
	return new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    search();
		} else {
		    int keyCode = event.getNativeEvent().getKeyCode();
		    if (keyCode == KeyCodes.KEY_ENTER) {
			search();
		    }
		}
	    }
	};
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showDonationPanel("Manage Donations");
    }

    @Override
    public void init() {
	Window.setTitle("ChurchGURU!");
    }

    @Override
    public void initWidgets() {
    }

    private void initSearchPanel() {

	offeringItem.check();
	otherOfferingItem.check();
	specialOfferingItem.check();
	thanksGivingOfferingItem.check();
	titheItem.check();
	buildingOfferingItem.check();
	approvedItem.check();
	cancelleditem.check();
	completedItem.check();
	createdItem.check();

	searchResultPanel.setCellSpacing(2);
	searchPanel.setWidget(0, 0, surname);
	searchPanel.setWidget(0, 1, forename);

	searchPanel.setWidget(1, 0, new HTML("&nbsp"));

	VerticalPanel donationTypesBody = new VerticalPanel();
	donationTypesBody.add(offeringItem);
	donationTypesBody.add(titheItem);
	donationTypesBody.add(buildingOfferingItem);
	donationTypesBody.add(thanksGivingOfferingItem);
	donationTypesBody.add(specialOfferingItem);
	donationTypesBody.add(otherOfferingItem);
	ScrollPanel donationTypeScrollPanel = new ScrollPanel(donationTypesBody);
	donationTypeScrollPanel.setWidth("150px");
	donationTypeScrollPanel.setHeight("100px");
	VerticalPanel donationTypesHederAndBody = new VerticalPanel();
	donationTypesHederAndBody.add(new HTML("<h4>Donation Types</h4>"));
	donationTypesHederAndBody.add(donationTypeScrollPanel);
	donationTypesHederAndBody.add(new HTML("&nbsp;"));
	searchPanel.setWidget(4, 0, donationTypesHederAndBody);

	VerticalPanel donationStatusesBody = new VerticalPanel();
	donationStatusesBody.add(cancelleditem);
	donationStatusesBody.add(createdItem);
	donationStatusesBody.add(approvedItem);
	donationStatusesBody.add(completedItem);
	ScrollPanel donationStatusScrollPanel = new ScrollPanel(donationStatusesBody);
	donationStatusScrollPanel.setWidth("150px");
	donationStatusScrollPanel.setHeight("100px");
	VerticalPanel donationStatusesHederAndBody = new VerticalPanel();
	donationStatusesHederAndBody.add(new HTML("<h4>Donation Statuses</h4>"));
	donationStatusesHederAndBody.add(donationStatusScrollPanel);
	donationStatusesHederAndBody.add(new HTML("&nbsp;"));
	searchPanel.setWidget(4, 1, donationStatusesHederAndBody);

	fromDate.setTitle("From Date");
	searchPanel.setText(5, 0, "From Date");
	searchPanel.setWidget(6, 0, fromDate);

	toDate.setTitle("To Date");
	searchPanel.setText(5, 1, "To Date");
	searchPanel.setWidget(6, 1, toDate);

	searchPanel.setWidget(7, 1, new HTML("&nbsp;"));

	maxResultSelect.addItem("150");
	maxResultSelect.addItem("200");
	maxResultSelect.addItem("350");
	searchPanel.setWidget(8, 0, maxResultSelect);

	searchButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		search();
	    }
	});
	searchPanel.setWidget(8, 1, searchButton);

	surname.getTextbox().addKeyPressHandler(handleSearch());
	forename.getTextbox().addKeyPressHandler(handleSearch());
    }

    @Override
    public void setDonationResult(List<DonationDto> dtos) {
	searchResultPanel.removeAllRows();

	final FlexCellFormatter formatter = searchResultPanel.getFlexCellFormatter();

	if (dtos.size() == 0) {
	    if (layout.getWidget(4, 0) != null) {
		layout.clearCell(4, 0);
	    }
	    Window.alert("No result found. Please refine your search");
	} else {
	    layout.setWidget(4, 0, new RoundedCornerPanel(searchResultPanel));
	    layout.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);

	    int col = 0;
	    searchResultPanel.setHTML(0, col, "<b>Email Address</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Fullname</b>");

	    searchResultPanel.setHTML(0, ++col, "<b>Created Date</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Status</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Currency</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Offering</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Tithe</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Thanksgiving</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Building</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Special</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Other</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Other (Specified)</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Total</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Address</b>");

	    searchResultPanel.setHTML(0, ++col, "<b>Member</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>In Mailing List</b>");

	    searchResultPanel.setHTML(0, ++col, "<b>Gift Aid Today</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Gift Aid In Past 4 Years</b>");
	    searchResultPanel.setHTML(0, ++col, "<b>Gift Aid In the Future</b>");
	    for (DonationDto dto : dtos) {
		col = 0;
		int rowIndex = dtos.indexOf(dto);

		searchResultPanel.setHTML(rowIndex + 1, col, dto.getEmailAddress());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getFullnameDto().getFullname());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getDonationDateAsString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getDonationTransactionStatus().getLabel());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getCurrency());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getOffering().toString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getTithe().toString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getThanksGiving().toString());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getBuildingFund().toString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getSpecialOffering().toString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getOtherOffering().toString());
		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getOtherOffering().toPlainString());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getAmountTotal().toString());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.getCellFormatter().setHorizontalAlignment(rowIndex + 1, col,
			HasHorizontalAlignment.ALIGN_LEFT);
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, dto.getFullAddress());
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, findYesOrNo(dto.isMember()));
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, findYesOrNo(dto.isInMailingList()));
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, findYesOrNo(dto.isGiftAidToday()));
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, findYesOrNo(dto.isGiftAidPast4Years()));
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

		searchResultPanel.setHTML(rowIndex + 1, ++col, findYesOrNo(dto.isGiftAidFuture()));
		formatter.setStyleName(rowIndex + 1, col, "dottedBorder");

	    }
	}
    }

    private void search() {
	DonationSearchAction action = new DonationSearchAction();
	action.setForename(forename.getValue());
	action.setSurname(surname.getValue());
	action.setFromDate(fromDate.getValue());
	action.setToDate(toDate.getValue());

	action.setMaxResult(Integer.parseInt(maxResultSelect.getValue()));

	// donation trans status
	List<DonationTransactionStatus> donationTransactionStatuses = new ArrayList<DonationTransactionStatus>();
	if (approvedItem.isChecked()) {
	    donationTransactionStatuses.add(DonationTransactionStatus.APPROVED);
	}
	if (cancelleditem.isChecked()) {
	    donationTransactionStatuses.add(DonationTransactionStatus.CANCELLED);
	}
	if (completedItem.isChecked()) {
	    donationTransactionStatuses.add(DonationTransactionStatus.COMPLETED);
	}
	if (createdItem.isChecked()) {
	    donationTransactionStatuses.add(DonationTransactionStatus.CREATED);
	}
	action.setDonationTransactionStatuses(donationTransactionStatuses);

	// donation types
	List<DonationType> donationTypes = new ArrayList<DonationType>();
	if (offeringItem.isChecked()) {
	    donationTypes.add(DonationType.OFFERING);
	}
	if (thanksGivingOfferingItem.isChecked()) {
	    donationTypes.add(DonationType.THANKSGIVING);
	}
	if (buildingOfferingItem.isChecked()) {
	    donationTypes.add(DonationType.BUILDING_FUND);
	}
	if (titheItem.isChecked()) {
	    donationTypes.add(DonationType.TITHE);
	}
	if (specialOfferingItem.isChecked()) {
	    donationTypes.add(DonationType.SPECIAL_OFFERING);
	}
	if (otherOfferingItem.isChecked()) {
	    donationTypes.add(DonationType.OTHER);
	}
	action.setDonationTypes(donationTypes);

	presenter.search(action);
    }

    private String findYesOrNo(boolean value) {
	if (value) {
	    return "Yes";
	}
	return "No";
    }
}
