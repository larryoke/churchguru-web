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
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class NewsLettersChartViewImpl implements NewsLettersChartView, Runnable {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "700px";

    private DashWidget newsletterChartWidget = new DashWidget("Newsletter Recipients", "More details",
	    new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {

		}
	    });

    private Presenter presenter;
    private PlaceController placeController;
    private HorizontalPanel makeupFlowPanel = new HorizontalPanel();

    public NewsLettersChartViewImpl(PlaceController placeController) {
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
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	DecoratorPanel decPanel = new DecoratorPanel();
	decPanel.setWidget(headerPanel);

	makeupFlowPanel.setWidth("400px");

	FlexTable layout = new FlexTable();
	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, decPanel);
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	layout.setWidget(3, 0, makeupFlowPanel);
	layout.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showDashboardPanel("Newsletters
	// Chart");
    }

    @Override
    public void init() {
	Window.setTitle("Dashboard");
    }

    @Override
    public void initWidgets() {
	VisualizationUtils.loadVisualizationApi(this, PieChart.PACKAGE, Gauge.PACKAGE, BarChart.PACKAGE);

    }

    private AbstractDataTable createNewsletterTrackingChart() {
	DataTable data = DataTable.create();

	data.addColumn(ColumnType.STRING, "Customer");
	data.addColumn(ColumnType.NUMBER, "Opened");
	data.addColumn(ColumnType.NUMBER, "Shared by Email");
	data.addColumn(ColumnType.NUMBER, "Liked in Facebook");

	data.addRows(5);
	int row = 0;

	data.setValue(row, 0, "Sep '12");
	data.setValue(row, 1, 1053);
	data.setValue(row, 2, 343);
	data.setValue(row, 3, 100);
	row++;

	data.setValue(row, 0, "Aug '12");
	data.setValue(row, 1, 925);
	data.setValue(row, 2, 317);
	data.setValue(row, 3, 92);
	row++;

	data.setValue(row, 0, "Jul '12");
	data.setValue(row, 1, 567);
	data.setValue(row, 2, 180);
	data.setValue(row, 3, 68);
	row++;

	data.setValue(row, 0, "Jun '12");
	data.setValue(row, 1, 735);
	data.setValue(row, 2, 213);
	data.setValue(row, 3, 74);
	row++;

	data.setValue(row, 0, "May '12");
	data.setValue(row, 1, 887);
	data.setValue(row, 2, 296);
	data.setValue(row, 3, 82);

	return data;

    }

    private BarChart.Options createNewsletterChartOptions() {
	BarChart.Options opts = BarChart.Options.create();
	opts.setWidth(420);
	opts.setHeight(250);
	opts.set3D(false);
	opts.setLegend(LegendPosition.RIGHT);
	opts.setColors("#B3AA00", "#008E8E", "#7591a6");
	return opts;

    }

    @Override
    public void run() {

	final BarChart newsletterBarChart = new BarChart();
	newsletterChartWidget.setWidget(newsletterBarChart);
	makeupFlowPanel.add(newsletterChartWidget);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		newsletterBarChart.draw(createNewsletterTrackingChart(), createNewsletterChartOptions());
	    }
	});
    }

}
