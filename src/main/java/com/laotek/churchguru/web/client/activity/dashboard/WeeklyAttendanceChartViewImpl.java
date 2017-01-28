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
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.AreaChart;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.laotek.churchguru.web.client.MainMenuContext;

public class WeeklyAttendanceChartViewImpl implements
	WeeklyAttendanceChartView, Runnable {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "700px";

    private DashWidget weeklyAttendanceChartWidget = new DashWidget(
	    "Weekly Attendance", "More details", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {

		}
	    });

    private Presenter presenter;
    private PlaceController placeController;
    private HorizontalPanel mainPanel = new HorizontalPanel();

    public WeeklyAttendanceChartViewImpl(PlaceController placeController) {
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

	mainPanel.setWidth("400px");

	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, decPanel);
	layout.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	layout.setWidget(3, 0, mainPanel);
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
	MainMenuContext.getInstance().showDashboardPanel(
		"Weekly Attendance Chart");
    }

    @Override
    public void init() {
	Window.setTitle("Weekly Attendance Chart");
    }

    @Override
    public void initWidgets() {
	VisualizationUtils.loadVisualizationApi(this, PieChart.PACKAGE,
		Gauge.PACKAGE, BarChart.PACKAGE);

    }

    private AbstractDataTable createNewsletterTrackingChart() {
	DataTable data = DataTable.create();

	data.addColumn(ColumnType.STRING, "Weekly Attendance");
	data.addColumn(ColumnType.NUMBER, "Children");
	data.addColumn(ColumnType.NUMBER, "Men");
	data.addColumn(ColumnType.NUMBER, "Women");

	data.addRows(12);
	int row = 0;

	data.setValue(row, 0, "Sun 23-Sep-12");
	data.setValue(row, 1, 1053);
	data.setValue(row, 2, 343);
	data.setValue(row, 3, 100);
	row++;

	data.setValue(row, 0, "Sun 16-Sep-12");
	data.setValue(row, 1, 925);
	data.setValue(row, 2, 317);
	data.setValue(row, 3, 92);
	row++;

	data.setValue(row, 0, "Sun 09-Sep-12");
	data.setValue(row, 1, 567);
	data.setValue(row, 2, 180);
	data.setValue(row, 3, 68);
	row++;

	data.setValue(row, 0, "Sun 26-Aug-12");
	data.setValue(row, 1, 735);
	data.setValue(row, 2, 213);
	data.setValue(row, 3, 74);
	row++;

	data.setValue(row, 0, "Sun 19-Aug-12");
	data.setValue(row, 1, 887);
	data.setValue(row, 2, 296);
	data.setValue(row, 3, 82);
	row++;

	data.setValue(row, 0, "Sun 12-Aug-12");
	data.setValue(row, 1, 887);
	data.setValue(row, 2, 296);
	data.setValue(row, 3, 82);
	row++;

	data.setValue(row, 0, "Sun 05-Aug-12");
	data.setValue(row, 1, 1053);
	data.setValue(row, 2, 343);
	data.setValue(row, 3, 100);
	row++;

	data.setValue(row, 0, "Sun 29-Jul-12");
	data.setValue(row, 1, 925);
	data.setValue(row, 2, 317);
	data.setValue(row, 3, 92);
	row++;

	data.setValue(row, 0, "Sun 22-Jul-12");
	data.setValue(row, 1, 7);
	data.setValue(row, 2, 80);
	data.setValue(row, 3, 68);
	row++;

	data.setValue(row, 0, "Sun 15-Jul-12");
	data.setValue(row, 1, 35);
	data.setValue(row, 2, 13);
	data.setValue(row, 3, 74);
	row++;

	data.setValue(row, 0, "Sun 08-Jul-12");
	data.setValue(row, 1, 87);
	data.setValue(row, 2, 96);
	data.setValue(row, 3, 8);
	row++;

	data.setValue(row, 0, "Sun 01-Jul-12");
	data.setValue(row, 1, 87);
	data.setValue(row, 2, 29);
	data.setValue(row, 3, 82);
	row++;

	return data;

    }

    private AreaChart.Options createNewsletterChartOptions() {
	AreaChart.Options opts = AreaChart.Options.create();
	opts.setWidth(700);
	opts.setHeight(450);
	opts.setLegend(LegendPosition.RIGHT);
	opts.setColors("#B3AA00", "#008E8E", "#7591a6");
	return opts;

    }

    @Override
    public void run() {

	final AreaChart newsletterAreaChart = new AreaChart();
	weeklyAttendanceChartWidget.setWidget(newsletterAreaChart);
	mainPanel.add(weeklyAttendanceChartWidget);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		newsletterAreaChart.draw(createNewsletterTrackingChart(),
			createNewsletterChartOptions());
	    }
	});
    }

}
