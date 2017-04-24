package com.laotek.churchguru.web.clientm.activity.message;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class MessageViewImpl extends DetailViewGwtImpl implements MessageView {
    private Presenter presenter;
    private FlowPanel formContainer = new FlowPanel();

    public MessageViewImpl() {

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	formContainer.add(new HTML("&nbsp;"));

	scrollPanel.setWidget(formContainer);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void showForm(final MessageMobDto dto) {

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {

		formContainer.clear();

		// Media
		// player.setStylePrimaryName("centeredWidget");
		WidgetList widget = new WidgetList();
		widget.setHeader(new HeaderLabel("Media"));
		formContainer.add(widget);
		String mediaUrl = dto.getMediaUrl();
		HTML player = getPlayer(mediaUrl);
		formContainer.add(player);

		// About message
		widget = new WidgetList();
		widget.setHeader(new HeaderLabel("About this message"));
		formContainer.add(widget);

		if (dto.getDescUrl() != null) {
		    Image mediaPic = new Image(dto.getDescUrl());
		    mediaPic.setWidth("100%");
		    formContainer.add(mediaPic);
		}

		if (dto.getDesc() != null) {
		    widget = new WidgetList();
		    widget.add(new HTML(dto.getDesc()));
		    formContainer.add(widget);
		}

		// Speaker
		widget = new WidgetList();
		widget.setHeader(new HeaderLabel("Speaker"));
		formContainer.add(widget);
		if (dto.getSpeakerPictureUrl() != null) {
		    Image image = new Image(dto.getSpeakerPictureUrl());
		    image.setWidth("100%");
		    formContainer.add(image);
		}
		if (dto.getSpeakerFullname() != null) {
		    widget = new WidgetList();
		    widget.add(new HTML(dto.getSpeakerFullname()));
		    formContainer.add(widget);
		}
		if (dto.getSpeakerDesc() != null) {
		    widget = new WidgetList();
		    widget.add(new HTML(dto.getSpeakerDesc()));
		    formContainer.add(widget);
		}
	    }
	});

    }

    @Override
    public void refresh() {
	handleOnLoad(formContainer.getElement());
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
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.message.MessageViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

    private HTML getPlayer(String mediaUrl) {
	StringBuffer sb = new StringBuffer();
	if (mediaUrl.contains("mp3")) {
	    sb.append("<audio width=\"100%\" controls>");
	    sb.append("<source src=\"");
	    sb.append(mediaUrl);
	    sb.append("\" type=\"audio/mpeg\">");
	    sb.append("Your browser does not support the audio element.");
	    sb.append("</audio>");
	} else if (mediaUrl.contains("mp4")) {
	    sb.append("<video width=\"100%\" controls>");
	    sb.append("<source src=\"");
	    sb.append(mediaUrl);
	    sb.append("\" type=\"video/mp4\">");
	    sb.append("Your browser does not support HTML5 video.");
	    sb.append("</video>");
	} else {
	    sb.append("&nbsp;");
	}
	HTML player = new HTML();
	player.setHTML(sb.toString());
	return player;
    }

}
