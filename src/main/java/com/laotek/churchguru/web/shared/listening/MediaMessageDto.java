package com.laotek.churchguru.web.shared.listening;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.MediaMessageStatus;
import com.laotek.churchguru.model.shared.enums.MediaType;

public class MediaMessageDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private String identifier;
    private String title;
    private String description;
    private String descriptionPictureURL;
    private String location;
    private int salePoints;

    private MediaMessageStatus status;
    private MediaType mediaType;
    private String mediaMessageUrl;

    private Date messageDate;
    private String messageDateAsString;
    private String lastUpdatedDate;
    private MediaMessageSpeakerDto speakerDto;
    private MediaMessageCategoryDto categoryDto;

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public int getSalePoints() {
	return salePoints;
    }

    public void setSalePoints(int salePoints) {
	this.salePoints = salePoints;
    }

    public MediaMessageSpeakerDto getSpeakerDto() {
	return speakerDto;
    }

    public void setSpeakerDto(MediaMessageSpeakerDto speakerDto) {
	this.speakerDto = speakerDto;
    }

    public MediaMessageCategoryDto getCategoryDto() {
	return categoryDto;
    }

    public void setCategoryDto(MediaMessageCategoryDto categoryDto) {
	this.categoryDto = categoryDto;
    }

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    public String getMessageDateAsString() {
	return messageDateAsString;
    }

    public void setMessageDateAsString(String messageDateAsString) {
	this.messageDateAsString = messageDateAsString;
    }

    public String getDescriptionPictureURL() {
	return descriptionPictureURL;
    }

    public void setDescriptionPictureURL(String descriptionPictureURL) {
	this.descriptionPictureURL = descriptionPictureURL;
    }

    public MediaMessageStatus getStatus() {
	return status;
    }

    public void setStatus(MediaMessageStatus status) {
	this.status = status;
    }

    public MediaType getMediaType() {
	return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
	this.mediaType = mediaType;
    }

    public String getMediaMessageUrl() {
	return mediaMessageUrl;
    }

    public void setMediaMessageUrl(String mediaMessageUrl) {
	this.mediaMessageUrl = mediaMessageUrl;
    }

}
