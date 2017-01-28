package com.laotek.churchguru.web.clientm.activity.aboutus;

import net.customware.gwt.dispatch.shared.Result;

public class AboutUseDetailsResult implements Result {

    private String aboutUsMessage;
    private String aboutPastorMessage;
    private String serviceTimes;
    private String fullAddress;
    private String orgName;
    private String websiteUrl;
    private String googleApiUrl;

    public AboutUseDetailsResult() {
    }

    public AboutUseDetailsResult(String aboutUsMessage,
	    String aboutPastorMessage, String serviceTimes, String fullAddress,
	    String orgName, String websiteUrl, String googleApiUrl) {
	super();
	this.aboutUsMessage = aboutUsMessage;
	this.aboutPastorMessage = aboutPastorMessage;
	this.serviceTimes = serviceTimes;
	this.fullAddress = fullAddress;
	this.orgName = orgName;
	this.websiteUrl = websiteUrl;
	this.googleApiUrl = googleApiUrl;
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

    public String getGoogleApiUrl() {
	return googleApiUrl;
    }
}
