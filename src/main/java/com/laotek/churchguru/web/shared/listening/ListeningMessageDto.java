package com.laotek.churchguru.web.shared.estore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EStoreMessageDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private String identifier;
    private String title;
    private String description;
    private String location;
    private int salePoints;

    private Date messageDate;
    private String messageDateAsString;
    private String lastUpdatedDate;
    private EStoreSpeakerDto speakerDto;
    private EStoreCategoryDto categoryDto;
    private EStoreMessagePictureDto pictureDto;
    private List<EStoreNotificationDto> notiicationDtos;

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

    public EStoreSpeakerDto getSpeakerDto() {
	return speakerDto;
    }

    public void setSpeakerDto(EStoreSpeakerDto speakerDto) {
	this.speakerDto = speakerDto;
    }

    public EStoreCategoryDto getCategoryDto() {
	return categoryDto;
    }

    public void setCategoryDto(EStoreCategoryDto categoryDto) {
	this.categoryDto = categoryDto;
    }

    public EStoreMessagePictureDto getPictureDto() {
	return pictureDto;
    }

    public void setPictureDto(EStoreMessagePictureDto pictureDto) {
	this.pictureDto = pictureDto;
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

    public List<EStoreNotificationDto> getNotificationDtos() {
	return notiicationDtos;
    }

    public void setNotificationDtos(List<EStoreNotificationDto> notiicationDtos) {
	this.notiicationDtos = notiicationDtos;
    }

}
