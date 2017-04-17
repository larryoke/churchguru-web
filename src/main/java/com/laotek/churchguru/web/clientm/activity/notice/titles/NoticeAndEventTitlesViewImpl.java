package com.laotek.churchguru.web.clientm.activity.notice.titles;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.button.image.NextitemImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class NoticeAndEventTitlesViewImpl extends DetailViewGwtImpl implements NoticeAndEventTitlesView {

    private CellList<NoticeAndEventDto> cellList;
    private Image img = new Image("/uploadedphotos/photos/org/messages?width=" + Window.getClientWidth());

    private PreviousitemImageButton newer = new PreviousitemImageButton();
    private NextitemImageButton older = new NextitemImageButton();

    private SimplePanel buttonPanel = new SimplePanel();

    private HorizontalPanel buttonPanelSections = new HorizontalPanel();

    private FlowPanel container = new FlowPanel();

    private Presenter presenter;

    public NoticeAndEventTitlesViewImpl() {

	BasicCell<NoticeAndEventDto> cell = new BasicCell<NoticeAndEventDto>() {
	    @Override
	    public String getDisplayString(NoticeAndEventDto model) {
		if ("".equals(model.getEventDate()) || model.getEventDate() == null) {
		    return model.getTitle();
		}
		return model.getTitle() + " - " + model.getEventDate();
	    }

	    @Override
	    public boolean canBeSelected(NoticeAndEventDto model) {
		return true;
	    }
	};
	cellList = new CellList<NoticeAndEventDto>(cell);
	cell.setStylename("headerLabel");

	img.setWidth("100%");
	container.add(img);
	container.add(cellList);

	newer.setText("Newer");
	newer.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		presenter.getNewer();
	    }
	});
	older.setText("Older");
	older.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		presenter.getOlder();
	    }
	});

	container.add(new HTML("&nbsp;"));

	// initButtonPanel();
	container.add(buttonPanel);

	scrollPanel.setWidget(container);
	scrollPanel.setScrollingEnabledX(false);
	main.add(scrollPanel);

    }

    // private void initButtonPanel() {
    // HorizontalPanel hpanel = new HorizontalPanel();
    // hpanel.setWidth("100%");
    // hpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    // hpanel.add(newer);
    // hpanel.add(new HTML("&nbsp;"));
    // hpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    // hpanel.add(older);
    // hpanel.add(new HTML("&nbsp;"));
    // hpanel.add(new HTML("&nbsp;"));
    // buttonPanel.add(hpanel);
    // }

    @Override
    public void displayNavigationButtons(boolean previous, boolean next) {
	buttonPanelSections.setWidth("100%");
	buttonPanelSections.clear();
	if (previous && next) {
	    buttonPanelSections.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	    buttonPanelSections.add(newer);
	    buttonPanelSections.add(new HTML("&nbsp;"));
	    buttonPanelSections.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    buttonPanelSections.add(older);
	    buttonPanelSections.add(new HTML("&nbsp;"));
	    buttonPanelSections.add(new HTML("&nbsp;"));
	} else if (previous) {
	    buttonPanelSections.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    buttonPanelSections.add(newer);
	} else if (next) {
	    buttonPanelSections.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    buttonPanelSections.add(older);
	}
	buttonPanel.add(buttonPanelSections);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void render(List<NoticeAndEventDto> messages) {
	cellList.render(messages);
    }

    @Override
    public HasCellSelectedHandler getCellSelectedHandler() {
	return cellList;
    }

    @Override
    public void displayError() {
	final HorizontalPanel hpanel = new HorizontalPanel();
	hpanel.setWidth("100%");
	hpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	hpanel.add(new HTML("<br><b>The End...</b>"));
	container.add(hpanel);
	Timer timer = new Timer() {
	    @Override
	    public void run() {
		container.remove(hpanel);
		cancel();
	    }
	};
	timer.schedule(1500);
    }

    @Override
    public void refresh() {
	handleOnLoad(container.getElement());
    }

    /**
     * called from native method
     */
    private void refreshPull() {
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventTitlesViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;
}
