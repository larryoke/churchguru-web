package com.laotek.churchguru.web.shared.listening;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AudioMessageMessageDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private String identifier;
    private String title;
    private String description;
    private String location;
    private int salePoints;

    private Date messageDate;
    private String messageDateAsString;
    private String lastUpdatedDate;
    private AudioMessageSpeakerDto speakerDto;
    private AudioMessageCategoryDto categoryDto;
    private AudioMessageMessagePictureDto pictureDto;
    private List<AudioMessageNotificationDto> notiicationDtos;

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

    public AudioMessageSpeakerDto getSpeakerDto() {
	return speakerDto;
    }

    public void setSpeakerDto(AudioMessageSpeakerDto speakerDto) {
	this.speakerDto = speakerDto;
    }

    public AudioMessageCategoryDto getCategoryDto() {
	return categoryDto;
    }

    public void setCategoryDto(AudioMessageCategoryDto categoryDto) {
	this.categoryDto = categoryDto;
    }

    public AudioMessageMessagePictureDto getPictureDto() {
	return pictureDto;
    }

    public void setPictureDto(AudioMessageMessagePictureDto pictureDto) {
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

    public List<AudioMessageNotificationDto> getNotificationDtos() {
	return notiicationDtos;
    }

    public void setNotificationDtos(List<AudioMessageNotificationDto> notiicationDtos) {
	this.notiicationDtos = notiicationDtos;
    }

}
