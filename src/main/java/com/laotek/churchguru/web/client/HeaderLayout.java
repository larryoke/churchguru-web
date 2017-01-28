package com.laotek.churchguru.web.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.activity.user.GiveFeedbackDialog;

public class HeaderLayout extends Composite {
    private HTML orgName = new HTML();
    private HTML userName = new HTML();
    private FlexTable layout = new FlexTable();

    private static HeaderLayout headerLayout = new HeaderLayout();

    public static HeaderLayout getInstance() {
	return headerLayout;
    }

    private HeaderLayout() {
	initWidget(layout);
    }

    public void buildMinimal() {
	layout.removeAllRows();
	layout.setBorderWidth(0);
	layout.setWidth("100%");
	layout.setHeight("35px");

	FlexCellFormatter fcf = layout.getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT);

	int row = 0;

	Image imageMenu = createSmallDeviceMenu();

	layout.setWidget(0, row, imageMenu);

	Image logo = new Image("/uploadedphotos/photos/org/logo");
	logo.setHeight("40px");
	layout.setWidget(0, ++row, logo);
	layout.getFlexCellFormatter().setWidth(0, row, "1%");

	layout.setWidget(0, ++row, getCell("images/app/add.png", new AddMenu()));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/homeMenu.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {

			String protocol = Window.Location.getProtocol();
			String host = Window.Location.getHost();
			UrlBuilder builder = Window.Location.createUrlBuilder()
				.setProtocol(protocol).setHost(host)
				.setPath("/").setHash(null);
			String url = builder.buildString();
			Window.open(url, "_blank", "");

		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/help.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {

			String protocol = Window.Location.getProtocol();
			String host = Window.Location.getHost();
			UrlBuilder builder = Window.Location.createUrlBuilder()
				.setProtocol(protocol).setHost(host)
				.setPath("/help").setHash(null);
			String url = builder.buildString();
			Window.open(url, "_blank", "");

		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "7%");

	layout.setWidget(0, ++row,
		getCell("images/app/feedback.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			GiveFeedbackDialog dialog = new GiveFeedbackDialog();
			dialog.center();
		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/logout.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			Cookies.removeCookie(ApplicationConstants.instance
				.sessionIdName());
			Window.Location.reload();
		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "8%");

	layout.setWidget(0, ++row, new HTML("&nbsp;"));

    }

    private Image createSmallDeviceMenu() {
	final SmallDeviceMenu2 menuDropDown = new SmallDeviceMenu2();
	Image imageMenu = new Image("images/app/menu.png");
	imageMenu.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		menuDropDown.show();
	    }
	});
	imageMenu.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		menuDropDown.show();
	    }
	});
	menuDropDown.addMouseOutHandler(new MouseOutHandler() {

	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		menuDropDown.hide();
	    }
	});
	menuDropDown.setPopupPosition(0, 100);
	return imageMenu;
    }

    public void buildNearFull() {
	layout.removeAllRows();
	layout.setBorderWidth(0);
	layout.setWidth("100%");
	layout.setHeight("35px");

	FlexCellFormatter fcf = layout.getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT);

	int row = 0;

	Image imageMenu = createSmallDeviceMenu();

	layout.setWidget(0, row, imageMenu);

	Image logo = new Image("/uploadedphotos/photos/org/logo");
	logo.setHeight("40px");
	layout.setWidget(0, ++row, logo);
	layout.getFlexCellFormatter().setWidth(0, row, "1%");

	layout.setWidget(0, ++row, getCell("images/app/add.png", new AddMenu()));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/homeMenu.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {

			String protocol = Window.Location.getProtocol();
			String host = Window.Location.getHost();
			UrlBuilder builder = Window.Location.createUrlBuilder()
				.setProtocol(protocol).setHost(host)
				.setPath("/").setHash(null);
			String url = builder.buildString();
			Window.open(url, "_blank", "");

		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/help.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {

			String protocol = Window.Location.getProtocol();
			String host = Window.Location.getHost();
			UrlBuilder builder = Window.Location.createUrlBuilder()
				.setProtocol(protocol).setHost(host)
				.setPath("/help").setHash(null);
			String url = builder.buildString();
			Window.open(url, "_blank", "");

		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "7%");

	layout.setWidget(0, ++row,
		getCell("images/app/feedback.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			GiveFeedbackDialog dialog = new GiveFeedbackDialog();
			dialog.center();
		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(0, ++row,
		getCell("images/app/logout.png", new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			Cookies.removeCookie(ApplicationConstants.instance
				.sessionIdName());
			Window.Location.reload();
		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "8%");

	layout.setWidget(0, ++row, new HTML("&nbsp;"));

    }

    public void buildFull() {
	layout.removeAllRows();
	layout.setBorderWidth(0);
	layout.setWidth("100%");
	layout.setHeight("35px");

	FlexCellFormatter fcf = layout.getFlexCellFormatter();
	fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
	fcf.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);
	fcf.setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT);

	int row = 0;

	Image logo = new Image("/uploadedphotos/photos/org/logo");
	logo.setHeight("40px");
	layout.setWidget(0, row, logo);
	layout.getFlexCellFormatter().setWidth(0, row, "1%");
	Image name = new Image("/uploadedphotos/photos/org/name");
	name.setWidth("200px");
	layout.setWidget(0, ++row, name);
	layout.getFlexCellFormatter().setWidth(0, row, "30%");

	layout.setWidget(0, ++row, getCell("images/app/add.png", new AddMenu()));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	Anchor siteAnchor = new Anchor("Site Home");
	layout.setWidget(
		0,
		++row,
		getCell("images/app/homeMenu.png", siteAnchor,
			new ClickHandler() {
			    @Override
			    public void onClick(ClickEvent event) {

				String protocol = Window.Location.getProtocol();
				String host = Window.Location.getHost();
				UrlBuilder builder = Window.Location
					.createUrlBuilder()
					.setProtocol(protocol).setHost(host)
					.setPath("/").setHash(null);
				String url = builder.buildString();
				Window.open(url, "_blank", "");

			    }
			}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	Anchor helpAnchor = new Anchor("Help");
	layout.setWidget(0, ++row,
		getCell("images/app/help.png", helpAnchor, new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {

			String protocol = Window.Location.getProtocol();
			String host = Window.Location.getHost();
			UrlBuilder builder = Window.Location.createUrlBuilder()
				.setProtocol(protocol).setHost(host)
				.setPath("/help").setHash(null);
			String url = builder.buildString();
			Window.open(url, "_blank", "");

		    }
		}));
	layout.getFlexCellFormatter().setWidth(0, row, "7%");

	Anchor feedbackAnchor = new Anchor("Give Feedback");
	layout.setWidget(
		0,
		++row,
		getCell("images/app/feedback.png", feedbackAnchor,
			new ClickHandler() {
			    @Override
			    public void onClick(ClickEvent event) {
				GiveFeedbackDialog dialog = new GiveFeedbackDialog();
				dialog.center();
			    }
			}));
	layout.getFlexCellFormatter().setWidth(0, row, "15%");

	layout.setWidget(
		0,
		++row,
		getCell("images/app/operator.png", new HTML(UserContext
			.getInstance().getUserDto().getFullname())));
	layout.getFlexCellFormatter().setWidth(0, row, "30%");

	Anchor logoutAnchor = new Anchor("Logout");
	layout.setWidget(
		0,
		++row,
		getCell("images/app/logout.png", logoutAnchor,
			new ClickHandler() {
			    @Override
			    public void onClick(ClickEvent event) {
				Cookies.removeCookie(ApplicationConstants.instance
					.sessionIdName());
				Window.Location.reload();
			    }
			}));
	layout.getFlexCellFormatter().setWidth(0, row, "8%");

	layout.setWidget(0, ++row, new HTML("&nbsp;"));

    }

    private FlexTable getCell(String imagePath, Anchor anchor,
	    ClickHandler clickHandler) {
	anchor.addClickHandler(clickHandler);
	Image image = new Image(imagePath);
	image.addClickHandler(clickHandler);
	image.setStylePrimaryName("handPointer");
	FlexTable cell = new FlexTable();
	cell.setWidget(0, 0, image);
	cell.setWidget(0, 1, anchor);
	return cell;
    }

    private FlexTable getCell(String imagePath, ClickHandler clickHandler) {
	Image image = new Image(imagePath);
	image.addClickHandler(clickHandler);
	image.setStylePrimaryName("handPointer");
	FlexTable cell = new FlexTable();
	cell.setWidget(0, 0, image);
	return cell;
    }

    private FlexTable getCell(String imagePath, Widget widget) {
	Image image = new Image(imagePath);
	FlexTable cell = new FlexTable();
	cell.setWidget(0, 0, image);
	cell.setWidget(0, 1, widget);
	return cell;
    }

    public HTML getUserName() {
	return userName;
    }

    public void setUserName(String name) {
	userName.setHTML(name);
    }

    public HTML getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName.setHTML("<b><big><big>" + orgName + "</big></big></b>");
    }
}
