package com.laotek.churchguru.web.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.AgeGroup;
import com.laotek.churchguru.model.shared.enums.CommsPreference;
import com.laotek.churchguru.model.shared.enums.MaritalStatus;
import com.laotek.churchguru.model.shared.enums.Title;

public abstract class PersonDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private String identifier;
    private Title title;
    private String forenames;
    private String surname;
    private String emailAddress;
    private PhoneDto landNumber;
    private PhoneDto mobileNumber;
    private MaritalStatus maritalStatus;
    private AgeGroup ageGroup;
    private CommsPreference commsPreference;
    private int birthDay;
    private String birthMonth;
    private String picturePath;
    private String currentStatus;

    private String addedDate;
    private String lastUpdatedDate;

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public Title getTitle() {
	return title;
    }

    public void setTitle(Title title) {
	this.title = title;
    }

    public String getForenames() {
	return forenames;
    }

    public void setForenames(String forenames) {
	this.forenames = forenames;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getFullname() {
	return title.getDesc() + " " + forenames + " " + surname;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public PhoneDto getLandNumber() {
	return landNumber;
    }

    public void setLandNumber(PhoneDto landNumber) {
	this.landNumber = landNumber;
    }

    public PhoneDto getMobileNumber() {
	return mobileNumber;
    }

    public void setMobileNumber(PhoneDto mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public AgeGroup getAgeGroup() {
	return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
	this.ageGroup = ageGroup;
    }

    public int getBirthDay() {
	return birthDay;
    }

    public void setBirthDay(int birthDay) {
	this.birthDay = birthDay;
    }

    public String getBirthMonth() {
	return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
	this.birthMonth = birthMonth;
    }

    public String getPicturePath() {
	return picturePath;
    }

    public void setPicturePath(String picturePath) {
	this.picturePath = picturePath;
    }

    public CommsPreference getCommsPreference() {
	return commsPreference;
    }

    public void setCommsPreference(CommsPreference commsPreference) {
	this.commsPreference = commsPreference;
    }

    public String getAddedDate() {
	return addedDate;
    }

    public void setAddedDate(String addedDate) {
	this.addedDate = addedDate;
    }

    public String getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCurrentStatus() {
	return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
	this.currentStatus = currentStatus;
    }

}
