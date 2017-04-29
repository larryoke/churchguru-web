package com.laotek.churchguru.web.clientm.activity.home;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;

public class MobileHomeViewImpl implements MobileHomeView {

    private RootFlexPanel main;
    private AboutImageButton aboutButton;
    private HeaderPanel headerPanel;
    private HeaderTitle headerPanelTitle = new HeaderTitle();
    private FlowPanel progressBarContainer = new FlowPanel();
    private ScrollPanel scrollPanel = new ScrollPanel();
    private FlowPanel container = new FlowPanel();

    private CellList<Topic> cellList;

    private FlexTable iconFlexTable = new FlexTable();
    private Presenter presenter;

    public MobileHomeViewImpl() {
	main = new RootFlexPanel();

	iconFlexTable.setStylePrimaryName("frontTable");

	Image logo = new Image("/uploadedphotos/photos/org/logo");
	logo.setHeight("25px");

	headerPanel = new HeaderPanel();
	headerPanel.add(new FixedSpacer(15));
	headerPanel.add(logo);
	headerPanel.add(new FixedSpacer());
	headerPanel.add(new FlexSpacer());
	headerPanel.add(headerPanelTitle);
	headerPanel.add(new FlexSpacer());

	aboutButton = new AboutImageButton();
	if (MGWT.getFormFactor().isPhone()) {
	    headerPanel.add(aboutButton);
	} else {
	    headerPanel.add(new FixedSpacer());
	}

	TopicCell<Topic> cell = new TopicCell<Topic>() {

	    @Override
	    public String getDescPicUrl(Topic model) {
		final ChurchAppTopicEnum current = model.getChurchAppTopicEnum();
		if (ChurchAppTopicEnum.PASTORS_DESK.equals(current)) {
		    return "/images/app/pastorDesk.png";
		}
		if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(current)) {
		    return "/images/app/prayerRequest.jpg";
		}
		if (ChurchAppTopicEnum.DONATION.equals(current)) {
		    return "/images/app/donate.png";
		}
		if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(current)) {
		    return "/images/app/calendar.png";
		}
		if (ChurchAppTopicEnum.LISTEN.equals(current)) {
		    return "/images/app/download.png";
		}
		if (ChurchAppTopicEnum.FACEBOOK.equals(current)) {
		    return "/images/app/facebook.png";
		}
		if (ChurchAppTopicEnum.TWITTER.equals(current)) {
		    return "/images/app/twitter.png";
		}
		if (ChurchAppTopicEnum.YOUTUBE.equals(current)) {
		    return "/images/app/youtube.png";
		}
		if (ChurchAppTopicEnum.ABOUT_US.equals(current)) {
		    return "/images/app/aboutUs.png";
		}
		return null;
	    }

	    @Override
	    public String getMediaType(Topic model) {
		return model.getChurchAppTopicEnum().getKey();
	    }

	    @Override
	    public String getDisplayString(Topic model) {
		return model.getName();
	    }
	};
	cellList = new CellList<Topic>(cell);
	cell.setStylename("headerLabel");
	container.add(cellList);

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
	Image givePic = new Image("/uploadedphotos/photos/org/home");
	givePic.setWidth("100%");
	container.add(givePic);
	container.add(cellList);
	scrollPanel.setWidget(container);

	main.add(headerPanel);

	initProgressBar();

	iconFlexTable.setWidth("100%");
	container.add(iconFlexTable);
	//
	// scrollPanel.setWidget(container);
	// scrollPanel.setScrollingEnabledX(false);
	main.add(scrollPanel);
    }

    private void initProgressBar() {
	HTML spacer = new HTML("");
	spacer.setHeight("15px");
	progressBarContainer.add(spacer);
	progressBarContainer.add(new ProgressBar());

	HTML html = new HTML("Loading topics...");
	html.getElement().setAttribute("style", "text-align: center; padding: 20px;");
	progressBarContainer.add(html);
    }

    @Override
    public void setTopics(List<Topic> createTopicsList) {
	cellList.render(createTopicsList);
    }

    // @Override
    // public void setTopics(List<Topic> createTopicsList, int width, final
    // MobileFactory clientFactory) {
    //
    // iconFlexTable.removeAllRows();
    //
    // int row = 0;
    // for (final Topic topic : createTopicsList) {
    //
    // IconWidget iconWidget = new IconWidget(topic.getName(), new IconHandler()
    // {
    //
    // @Override
    // public void open() {
    //
    // final ChurchAppTopicEnum current = topic.getChurchAppTopicEnum();
    //
    // if (ChurchAppTopicEnum.PASTORS_DESK.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // PastorDeskPlace(current.getDesc()));
    // return;
    // }
    // if (ChurchAppTopicEnum.LISTEN.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // MessageCategoryPlace("messageCategory"));
    // return;
    // }
    // if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // PrayerRequestPlace(current.getDesc()));
    //
    // return;
    // }
    // if (ChurchAppTopicEnum.DONATION.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // GivePlace(current.getDesc()));
    // return;
    // }
    // if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // NoticeAndEventTitlesPlace(current.getDesc()));
    // return;
    // }
    // if (ChurchAppTopicEnum.ABOUT_US.equals(current)) {
    // clientFactory.getPlaceController().goTo(new
    // AboutUsPlace(current.getDesc()));
    // return;
    // }
    //
    // }
    //
    // @Override
    // public Image getIcon() {
    //
    // final ChurchAppTopicEnum current = topic.getChurchAppTopicEnum();
    //
    // if (ChurchAppTopicEnum.PASTORS_DESK.equals(current)) {
    // return new Image("/images/app/pastorDesk.png");
    // }
    // if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(current)) {
    // return new Image("/images/app/prayerRequest.jpg");
    // }
    // if (ChurchAppTopicEnum.DONATION.equals(current)) {
    // return new Image("/images/app/donate.png");
    // }
    // if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(current)) {
    // return new Image("/images/app/calendar.png");
    // }
    // if (ChurchAppTopicEnum.LISTEN.equals(current)) {
    // return new Image("/images/app/download.png");
    // }
    // if (ChurchAppTopicEnum.FACEBOOK.equals(current)) {
    // return new Image("/images/app/facebook.png");
    // }
    // if (ChurchAppTopicEnum.TWITTER.equals(current)) {
    // return new Image("/images/app/twitter.png");
    // }
    // if (ChurchAppTopicEnum.YOUTUBE.equals(current)) {
    // return new Image("/images/app/youtube.png");
    // }
    // if (ChurchAppTopicEnum.ABOUT_US.equals(current)) {
    // return new Image("/images/app/aboutUs.png");
    // }
    // return null;
    //
    // }
    // });
    //
    // iconFlexTable.setWidget(row++, 0, iconWidget);
    // }
    // }

    @Override
    public void displayLoadingProgress() {
	main.add(progressBarContainer);
	main.remove(scrollPanel);
    }

    @Override
    public void onLoad() {
	main.add(scrollPanel);
	main.remove(progressBarContainer);
    }

    @Override
    public Widget asWidget() {
	return main;
    }

    @Override
    public void setTitle(String text) {
	headerPanelTitle.setText(text);
    }

    @Override
    public HasTapHandlers getAboutButton() {
	return aboutButton;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void onFinishLoad() {
	presenter.goToNoticesIf();
	handleOnLoad(container.getElement());
    }

    private void onFinishLoading() {
	scrollPanel.scrollTo(0, 0);
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.home.MobileHomeViewImpl::onFinishLoading()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

    @Override
    public HasCellSelectedHandler getCellSelectedHandler() {
	return cellList;
    }

}
