package com.laotek.churchguru.web.client.activity.churchapp.general;

import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

import net.customware.gwt.dispatch.shared.Action;

public class SubmitAboutUsOrgDetailsAction extends AbstractDispatchAction
	implements Action<SubmitAboutUsOrgDetailsResult>, HasOrganisationCrudRole {
    private String aboutUsMessage;
    private String aboutPastorMessage;
    private String serviceTimes;
    private String adressLine1;
    private String adressLine2;
    private String postcode;
    private Country country;
    private String orgName;
    private String adminEmailAddress;
    private String prayerRequestEmailAddress;
    private String websiteUrl;

    private String googleApiKey;
    private String latitude;
    private String longitude;

    public String getAboutUsMessage() {
	return aboutUsMessage;
    }

    public void setAboutUsMessage(String aboutUsMessage) {
	this.aboutUsMessage = aboutUsMessage;
    }

    public String getAboutPastorMessage() {
	return aboutPastorMessage;
    }

    public void setAboutPastorMessage(String aboutPastorMessage) {
	this.aboutPastorMessage = aboutPastorMessage;
    }

    public String getServiceTimes() {
	return serviceTimes;
    }

    public void setServiceTimes(String serviceTimes) {
	this.serviceTimes = serviceTimes;
    }

    public String getAdressLine1() {
	return adressLine1;
    }

    public void setAdressLine1(String adressLine1) {
	this.adressLine1 = adressLine1;
    }

    public String getAdressLine2() {
	return adressLine2;
    }

    public void setAdressLine2(String adressLine2) {
	this.adressLine2 = adressLine2;
    }

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
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

    public void setWebsiteUrl(String websiteUrl) {
	this.websiteUrl = websiteUrl;
    }

    public String getAdminEmailAddress() {
	return adminEmailAddress;
    }

    public void setAdminEmailAddress(String emailAddress) {
	this.adminEmailAddress = emailAddress;
    }

    public String getPrayerRequestEmailAddress() {
	return prayerRequestEmailAddress;
    }

    public void setPrayerRequestEmailAddress(String prayerRequestEmailAddress) {
	this.prayerRequestEmailAddress = prayerRequestEmailAddress;
    }

    public String getGoogleApiKey() {
	return googleApiKey;
    }

    public void setGoogleApiKey(String googleApiKey) {
	this.googleApiKey = googleApiKey;
    }

    public String getLatitude() {
	return latitude;
    }

    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }

    public String getLongitude() {
	return longitude;
    }

    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }
}
