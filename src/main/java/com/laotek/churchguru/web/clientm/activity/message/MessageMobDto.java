package com.laotek.churchguru.web.clientm.activity.message;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageMobDto implements IsSerializable {

    private String title;
    private String eventDateAsStr;
    private String identifier;

    private String speakerFullname;
    private String speakerPictureUrl;
    private String speakerDesc;

    private String desc;
    private String descUrl;

    private String mediaUrl;

    private String categoryName;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getEventDateAsStr() {
	return eventDateAsStr;
    }

    public void setEventDateAsStr(String eventDateAsStr) {
	this.eventDateAsStr = eventDateAsStr;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getSpeakerFullname() {
	return speakerFullname;
    }

    public void setSpeakerFullname(String speakerFullname) {
	this.speakerFullname = speakerFullname;
    }

    public String getSpeakerPictureUrl() {
	return speakerPictureUrl;
    }

    public void setSpeakerPictureUrl(String speakerPictureUrl) {
	this.speakerPictureUrl = speakerPictureUrl;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

    public String getDescUrl() {
	return descUrl;
    }

    public void setDescUrl(String descUrl) {
	this.descUrl = descUrl;
    }

    public String getMediaUrl() {
	return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
	this.mediaUrl = mediaUrl;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    public String getSpeakerDesc() {
	return speakerDesc;
    }

    public void setSpeakerDesc(String speakerDesc) {
	this.speakerDesc = speakerDesc;
    }
}
