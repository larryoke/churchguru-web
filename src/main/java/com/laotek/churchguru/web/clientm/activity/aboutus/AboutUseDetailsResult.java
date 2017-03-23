package com.laotek.churchguru.web.clientm.activity.aboutus;

import java.math.BigDecimal;

import net.customware.gwt.dispatch.shared.Result;

public class AboutUseDetailsResult implements Result {

    private String aboutUsMessage;
    private String aboutPastorMessage;
    private String serviceTimes;
    private String fullAddress;
    private String orgName;
    private String websiteUrl;

    private boolean isFacebook;
    private boolean isTwitter;
    private boolean isYoutube;
    private String googleApiKey;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AboutUseDetailsResult() {
    }

    public AboutUseDetailsResult(String aboutUsMessage, String aboutPastorMessage, String serviceTimes,
	    String fullAddress, String orgName, String websiteUrl, boolean isFacebook, boolean isTwitter,
	    boolean isYoutube, String googleApiKey, BigDecimal latitude, BigDecimal longitude) {
	super();
	this.aboutUsMessage = aboutUsMessage;
	this.aboutPastorMessage = aboutPastorMessage;
	this.serviceTimes = serviceTimes;
	this.fullAddress = fullAddress;
	this.orgName = orgName;
	this.websiteUrl = websiteUrl;
	this.isFacebook = isFacebook;
	this.isTwitter = isTwitter;
	this.isYoutube = isYoutube;

	this.googleApiKey = googleApiKey;
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public String getAboutUsMessage() {
	return aboutUsMessage;
    }

    public String getAboutPastorMessage() {
	return aboutPastorMessage;
    }

    public void setAboutUsMessage(String aboutUsMessage) {
	this.aboutUsMessage = aboutUsMessage;
    }

    public String getServiceTimes() {
	return serviceTimes;
    }

    public void setServiceTimes(String serviceTimes) {
	this.serviceTimes = serviceTimes;
    }

    public String getFullAddress() {
	return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
	this.fullAddress = fullAddress;
    }

    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public String getWebsiteUrl() {
	return websiteUrl;
    }

    public boolean isFacebook() {
	return isFacebook;
    }

    public boolean isTwitter() {
	return isTwitter;
    }

    public boolean isYoutube() {
	return isYoutube;
    }

    public String getGoogleApiKey() {
	return googleApiKey;
    }

    public BigDecimal getLatitude() {
	return latitude;
    }

    public BigDecimal getLongitude() {
	return longitude;
    }
}
