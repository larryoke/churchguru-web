package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.AgeGroup;
import com.laotek.churchguru.model.shared.enums.CommsPreference;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.MaritalStatus;
import com.laotek.churchguru.model.shared.enums.MemberAccountStatus;
import com.laotek.churchguru.model.shared.enums.Title;

@Entity
public class MemberDraft {

    @Id
    private int rowIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private MaritalStatus maritalStatus;

    @Column(nullable = true)
    private String emailAddress;

    @Column(nullable = true)
    private String forenames;

    @Column(nullable = true)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CountryCode mobileCountryCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CountryCode landNumberCountryCode;

    @Column(nullable = true)
    private String mobile;

    @Column(nullable = true)
    private String landNumber;

    @Column(nullable = true)
    private String addressLine1;

    @Column(nullable = true)
    private String addressLine2;

    @Column(nullable = true)
    private String town;

    @Column(nullable = true)
    private String postcode;

    @Column
    private int birthDay;

    @Column
    private String birthMonth;

    @Enumerated(EnumType.STRING)
    private CommsPreference commsPreference;

    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    private MemberAccountStatus memberAccountStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    public Title getTitle() {
	return title;
    }

    public void setTitle(Title title) {
	this.title = title;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
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

    public String getFullname() {

	return forenames + " " + surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getLandNumber() {
	return landNumber;
    }

    public void setLandNumber(String landNumber) {
	this.landNumber = landNumber;
    }

    public MemberAccountStatus getMemberAccountStatus() {
	return memberAccountStatus;
    }

    public void setMemberAccountStatus(MemberAccountStatus memberAccountStatus) {
	this.memberAccountStatus = memberAccountStatus;
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

    public AgeGroup getAgeGroup() {
	return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
	this.ageGroup = ageGroup;
    }

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public CommsPreference getCommsPreference() {
	return commsPreference;
    }

    public void setCommsPreference(CommsPreference commsPreference) {
	this.commsPreference = commsPreference;
    }

    public CountryCode getMobileCountryCode() {
	return mobileCountryCode;
    }

    public void setMobileCountryCode(CountryCode mobileCountryCode) {
	this.mobileCountryCode = mobileCountryCode;
    }

    public CountryCode getLandNumberCountryCode() {
	return landNumberCountryCode;
    }

    public void setLandNumberCountryCode(CountryCode landNumberCountryCode) {
	this.landNumberCountryCode = landNumberCountryCode;
    }

    public int getRowIndex() {
	return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
	this.rowIndex = rowIndex;
    }

    public String getAddressLine1() {
	return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
	return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
    }

    public String getTown() {
	return town;
    }

    public void setTown(String town) {
	this.town = town;
    }

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }
}
