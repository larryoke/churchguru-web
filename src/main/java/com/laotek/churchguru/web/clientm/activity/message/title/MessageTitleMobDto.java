package com.laotek.churchguru.web.clientm.activity.message.title;

import java.io.Serializable;

public class MessageTitleMobDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String identifier;

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
}