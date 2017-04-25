package com.laotek.churchguru.web.clientm.activity.message.title;

import java.io.Serializable;

public class MessageTitleMobDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String identifier;
    private String descPicUrl;
    private String mediaType;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getDescPicUrl() {
	return descPicUrl;
    }

    public void setDescPicUrl(String descPicUrl) {
	this.descPicUrl = descPicUrl;
    }

    public String getMediaType() {
	return mediaType;
    }

    public void setMediaType(String mediaType) {
	this.mediaType = mediaType;
    }
}
