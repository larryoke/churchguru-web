package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum CountryCode implements Serializable, IsSerializable {

    //
    UK("images/app/flags/uk.png", "United Kingdom", "+44", "\\+44\\d{9,10}|0[1,2,3,7]\\d{9}"),
    //
    NIGERIA("images/app/flags/nigeria.png", "Nigeria", "+234", "\\+234d{5,12}"),
    //
    USA("images/app/flags/usa.png", "United States", "+1", "\\+1d{5,12}"),
    //
    CANADA("images/app/flags/canada.png", "Canada", "+1", "\\+1d{5,12}"),
    //
    SOUTH_AFRICA("images/app/flags/south_africa.png", "South Africa", "+27", "\\+27d{5,12}"),
    //
    IRELAND("images/app/flags/ireland.png", "Ireland", "+234", "\\+234d{5,12}"),
    //
    GHANA("images/app/flags/ghana.png", "Ghana", "+233", "\\+234d{5,12}");

    private String imageUrl;
    private String name;
    private String code;
    private String regex;

    private CountryCode(String imageUrl, String name, String code, String regex) {
	this.imageUrl = imageUrl;
	this.name = name;
	this.code = code;
	this.regex = regex;
    }

    public String getImageUrl() {
	return imageUrl;
    }

    public String getName() {
	return name;
    }

    public String getCode() {
	return code;
    }

    public String getRegex() {
	return regex;
    }

}
