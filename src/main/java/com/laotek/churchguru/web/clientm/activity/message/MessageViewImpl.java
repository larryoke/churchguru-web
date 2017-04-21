package com.laotek.churchguru.web.clientm.activity.message;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class MessageViewImpl extends DetailViewGwtImpl implements MessageView {
    private Presenter presenter;
    private FlowPanel formContainer = new FlowPanel();

    private HTML speakerName = new HTML();

    private Image speakerPic = new Image();
    private HTML speakerDesc = new HTML();

    private HTML mediaDesc = new HTML();

    private SimplePanel mediaDescPanel = new SimplePanel();
    private SimplePanel mediaPlayerPanel = new SimplePanel();

    public MessageViewImpl() {

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	addMedia(formContainer);

	addDesc(formContainer);

	addSpeaker(formContainer);

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
		speakerPic.setUrl(dto.getSpeakerPictureUrl());
		speakerPic.setWidth("100%");
		speakerName.setHTML(dto.getSpeakerFullname());
		speakerDesc.setHTML(dto.getSpeakerDesc());

		if (dto.getDescUrl() != null) {
		    Image mediaPic = new Image(dto.getDescUrl());
		    mediaPic.setWidth("100%");
		    mediaDescPanel.add(mediaPic);
		} else {
		    mediaDescPanel.clear();
		}

		String mediaUrl = dto.getMediaUrl();
		StringBuffer sb = new StringBuffer();
		if (mediaUrl.contains("mp3")) {
		    sb.append("<audio controls>");
		    sb.append("<source src=\"");
		    sb.append(mediaUrl);
		    sb.append("\" type=\"audio/mpeg\">");
		    sb.append("Your browser does not support the audio element.");
		    sb.append("</audio>");
		} else if (mediaUrl.contains("mp4")) {
		    sb.append("<video width=\"400\" controls>");
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
		mediaPlayerPanel.setWidget(player);
	    }
	});

    }

    private void addMedia(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Media"));
	widget.add(mediaPlayerPanel);
	container.add(widget);
    }

    private void addDesc(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("About this message"));
	widget.add(mediaDescPanel);
	mediaDesc.setStylePrimaryName("normalLabel");
	widget.add(mediaDesc);
	container.add(widget);
    }

    private void addSpeaker(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Speaker"));
	widget.add(speakerPic);
	speakerDesc.setStylePrimaryName("normalLabel");
	widget.add(speakerName);
	widget.add(speakerDesc);
	container.add(widget);
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

}
