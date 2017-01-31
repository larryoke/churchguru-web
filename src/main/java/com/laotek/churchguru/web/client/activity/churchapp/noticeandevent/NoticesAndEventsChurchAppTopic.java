package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;

public class NoticesAndEventsChurchAppTopic {

    private String websiteLabel;
    private Image image;
    private SingleUploader singleUploader;
    private InsertPhotoAnchor anchor;
    private ChurchAppTopicEnum churchAppTopicEnum;

    public NoticesAndEventsChurchAppTopic(String websiteLabel, String imageUrl,
	    String servletPath, ChurchAppTopicEnum churchAppTopicEnum) {
	this.websiteLabel = websiteLabel;
	anchor = new InsertPhotoAnchor();
	anchor.setStylePrimaryName("handPointer");
	this.churchAppTopicEnum = churchAppTopicEnum;
	image = new Image(imageUrl);
	singleUploader = new SingleUploader(FileInputType.CUSTOM.with(anchor));
	singleUploader.setValidExtensions(new String[] { "png", "jpg", "gif" });
	singleUploader.setAutoSubmit(true);
	singleUploader.setServletPath(servletPath);
	singleUploader
		.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
		    public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
			    UploadedInfo info = uploader.getServerInfo();
			    image.setUrl(image.getUrl() + "?random="
				    + info.size);
			    Window.Location.reload();
			}
		    }
		});
    }

    public Image getImage() {
	return image;
    }

    public SingleUploader getSingleUploader() {
	return singleUploader;
    }

    public String getWebsiteLabel() {
	return websiteLabel;
    }

    public InsertPhotoAnchor getInsertPhotoAnchor() {
	return anchor;
    }

    public ChurchAppTopicEnum getChurchAppTopicEnum() {
	return churchAppTopicEnum;
    }

    class InsertPhotoAnchor extends Composite implements HasClickHandlers {
	private SimplePanel widget = new SimplePanel();
	private HTML message = new HTML("<u>Change Picture</u>");

	public InsertPhotoAnchor() {
	    initWidget(widget);
	    message.setStylePrimaryName("handPointer");
	    widget.setStylePrimaryName("handPointer");
	    widget.setWidget(message);
	    widget.setSize("150px", "40px");

	}

	public HTML getMessage() {
	    return message;
	}

	public void setMessage(HTML message) {
	    this.message = message;
	    widget.setWidget(message);
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
    }
}
