package com.laotek.churchguru.web.clientm.activity.prayerrequest;

import net.customware.gwt.dispatch.shared.Action;

public class PrayerRequestAction implements Action<PrayerRequestResult> {
    private String title;
    private String forename;
    private String surname;
    private String emailAddress;
    private String mobileNo;
    private String message;

    public PrayerRequestAction() {
    }

    public PrayerRequestAction(String title, String forename, String surname,
	    String emailAddress, String mobileNo, String message) {
	super();
	this.title = title;
	this.forename = forename;
	this.surname = surname;
	this.emailAddress = emailAddress;
	this.mobileNo = mobileNo;
	this.message = message;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getForename() {
	return forename;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
	return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
