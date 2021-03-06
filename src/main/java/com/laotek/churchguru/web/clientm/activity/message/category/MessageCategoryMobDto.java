package com.laotek.churchguru.web.clientm.activity.message.category;

import java.io.Serializable;

public class MessageCategoryMobDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int count;
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

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }
}
