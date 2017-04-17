package com.laotek.churchguru.web.clientm.activity.message.title;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class MessageTitleViewImpl extends DetailViewGwtImpl implements MessageTitleView {
    private FlowPanel container = new FlowPanel();

    private CellList<MessageTitleMobDto> cellList;

    public MessageTitleViewImpl() {
	BasicCell<MessageTitleMobDto> cell = new BasicCell<MessageTitleMobDto>() {
	    @Override
	    public String getDisplayString(MessageTitleMobDto model) {
		return model.getName();
	    }

	    @Override
	    public boolean canBeSelected(MessageTitleMobDto model) {
		return true;
	    }
	};
	cellList = new CellList<MessageTitleMobDto>(cell);
	cell.setStylename("headerLabel");
	container.add(cellList);

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
	Image givePic = new Image("/uploadedphotos/photos/org/listen?width=" + Window.getClientWidth());
	givePic.setWidth("100%");
	container.add(givePic);
	container.add(cellList);
	scrollPanel.setWidget(container);
    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public HasCellSelectedHandler getCellSelectedHandler() {
	return cellList;
    }

    @Override
    public void refresh() {
	handleOnLoad(container.getElement());
    }

    @Override
    public void render(List<MessageTitleMobDto> dtos) {
	cellList.render(dtos);

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
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.message.title.MessageTitleViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

}
