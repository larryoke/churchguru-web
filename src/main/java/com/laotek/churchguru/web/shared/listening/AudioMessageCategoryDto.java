package com.laotek.churchguru.web.shared.listening;

import java.io.Serializable;

public class AudioMessageCategoryDto implements Serializable {
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
