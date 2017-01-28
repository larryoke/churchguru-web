package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.MessagePhotoAlignment;

public class MessagePhotoDto implements IsSerializable{
	
	private String photoIdentity;
	private String filename;
	private String caption;
	private MessagePhotoAlignment alignment;
	
	
	public String getPhotoIdentity() {
		return photoIdentity;
	}
	public void setPhotoIdentity(String photoIdentity) {
		this.photoIdentity = photoIdentity;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public MessagePhotoAlignment getAlignment() {
		return alignment;
	}
	public void setAlignment(MessagePhotoAlignment alignment) {
		this.alignment = alignment;
	}
	
	
}
