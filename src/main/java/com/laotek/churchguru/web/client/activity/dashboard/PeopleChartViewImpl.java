package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.laotek.churchguru.web.client.MainMenuContext;

public class PeopleChartViewImpl implements PeopleChartView, Runnable {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "700px";
    private DashWidget peopleChartWidget = new DashWidget("People Makeup",
	    "More details", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {

		}
	    });

    private Presenter presenter;
    private PlaceController placeController;
    private HorizontalPanel makeupFlowPanel = new HorizontalPanel();

    public PeopleChartViewImpl(PlaceController placeController) {
	this.placeController = placeController;
    }

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/dashboard.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>Dashboard</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	DecoratorPanel decPanel = new DecoratorPanel();
	decPanel.setWidget(headerPanel);

	makeupFlowPanel.setWidth("400px");

	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, decPanel);
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	layout.setWidget(3, 0, makeupFlowPanel);
	layout.getCellFormatter().setHorizontalAlignment(3, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showDashboardPanel("People Chart");
    }

    @Override
    public void init() {
	Window.setTitle("Dashboard");
    }

    @Override
    public void initWidgets() {
	VisualizationUtils.loadVisualizationApi(this, PieChart.PACKAGE,
		Gauge.PACKAGE, BarChart.PACKAGE);

    }

    private PieChart.Options createPeoplePieChartOptions() {
	PieChart.Options opts = PieChart.Options.create();
	opts.setWidth(320);
	opts.setHeight(250);
	opts.set3D(true);
	opts.setTitle("The Makeup");
	return opts;
    }

    private AbstractDataTable createPeoplePieChartTable() {
	DataTable data = DataTable.create();
	data.addColumn(ColumnType.STRING, "Task");
	data.addColumn(ColumnType.NUMBER, "Hours per Day");
	data.addRows(3);
	data.setValue(0, 0, "Members");
	data.setValue(0, 1, 104);
	data.setValue(1, 0, "Guests");
	data.setValue(1, 1, 80);
	data.setValue(2, 0, "Users");
	data.setValue(2, 1, 10);
	return data;
    }

    @Override
    public void run() {
	final PieChart peoplePieChart = new PieChart();
	peopleChartWidget.setWidget(peoplePieChart);
	makeupFlowPanel.add(peopleChartWidget);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		peoplePieChart.draw(createPeoplePieChartTable(),
			createPeoplePieChartOptions());
	    }
	});
    }

}
