package com.laotek.churchguru.web.client.activity.home;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.OrganisationDto;

public class HomeViewImpl implements HomeView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "100%";

    private Presenter presenter;
    private PlaceController placeController;

    private Anchor aboutUsAnchor = new Anchor("Update All Details");

    private FlexTable layout = new FlexTable();

    private Image logo = new Image("/uploadedphotos/photos/org/logo");

    public HomeViewImpl(PlaceController placeController) {
	this.placeController = placeController;
    }

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/dashboard.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>Home</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	aboutUsAnchor.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController()
			.goTo(new GeneralChurchAppPlace("aboutUs"));
	    }
	});
	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showDashboardPanel("Home");
    }

    @Override
    public void init() {
	Window.setTitle("ChurchGURU!");
    }

    @Override
    public void initWidgets() {
    }

    private void addRow(String label, String value, FlexTable panel) {
	int row = panel.getRowCount();
	panel.setHTML(row, 0, "<b>" + label + "</b>");
	panel.setHTML(row, 1, value);
    }

    private void addRow(String label, Widget widget, FlexTable panel) {
	int row = panel.getRowCount();
	panel.setHTML(row, 0, "<b>" + label + "</b>");
	panel.setWidget(row, 1, widget);
    }

    @Override
    public void initOrganisation(OrganisationDto dto) {
	FlexTable panel = new FlexTable();
	addRow("Church Name", dto.getOrgName(), panel);
	addRow("Church Admin Email", dto.getAdminEmail(), panel);
	addRow("Church Prayer Request Email", dto.getPrayerRequestEmail(),
		panel);
	addRow("Church Site Domain", dto.getOrgDomain(), panel);
	addRow("Application Lunch Date", dto.getAppLunchDate(), panel);
	addRow("Church Logo", logo, panel);
	addRow("", aboutUsAnchor, panel);

	layout.setWidget(7, 0, new RoundedCornerPanel(panel));
	layout.getCellFormatter().setHorizontalAlignment(7, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
    }

    class InsertPhotoAnchor extends Composite implements HasClickHandlers {
	private SimplePanel widget = new SimplePanel();
	private HTML message = new HTML("<u>Add Logo</u>");

	public HTML getMessage() {
	    return message;
	}

	public void setMessage(HTML message) {
	    this.message = message;
	    widget.setWidget(message);
	}

	public InsertPhotoAnchor() {
	    initWidget(widget);
	    message.setStylePrimaryName("handPointer");
	    widget.setStylePrimaryName("handPointer");
	    widget.setWidget(message);
	    widget.setSize("150px", "40px");

	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
    }

}
