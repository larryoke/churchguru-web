package com.laotek.churchguru.web.shared.listening;

import java.io.Serializable;

import com.laotek.churchguru.web.shared.FullnameDto;

public class MediaMessageSpeakerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private FullnameDto fullnameDto;
    private String description;
    private String identifier;
    private String pictureURL;

    public FullnameDto getFullnameDto() {
	return fullnameDto;
    }

    public void setFullnameDto(FullnameDto fullnameDto) {
	this.fullnameDto = fullnameDto;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getPictureURL() {
	return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
	this.pictureURL = pictureURL;
    }

}
