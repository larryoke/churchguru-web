package com.laotek.churchguru.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.DonationThankyouEmailSendStatus;
import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.model.shared.enums.Title;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DONATION_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

    @Column(nullable = false)
    private String forenames;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private boolean isMember;

    @Column(nullable = false)
    private boolean isInMailingList;

    @Column(nullable = false)
    private CountryCode mobileCountryCode;

    @Column(nullable = false)
    private String mobile;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String postcode;

    @Column
    private String townOrCity;

    @Column(nullable = true)
    private String otherGivingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationTransactionStatus donationTransactionStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationThankyouEmailSendStatus donationThankyouEmailSendStatus;

    @Column(nullable = false, unique = true)
    private String paymentId;

    @Column(nullable = false)
    private BigDecimal offering;

    @Column(nullable = false)
    private BigDecimal tithe;

    @Column(nullable = false)
    private BigDecimal buildingFund;

    @Column(nullable = false)
    private BigDecimal thanksGiving;

    @Column(nullable = false)
    private BigDecimal specialOffering;

    @Column(nullable = false)
    private BigDecimal otherOffering;

    @Column(nullable = false)
    private BigDecimal amountTotal;

    @Column(nullable = false)
    private boolean isGiftAidToday;

    @Column(nullable = false)
    private boolean isGiftAidPast4Years;

    @Column(nullable = false)
    private boolean isGiftAidFuture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completeDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdatedDate;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
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

    public CountryCode getMobileCountryCode() {
	return mobileCountryCode;
    }

    public void setMobileCountryCode(CountryCode mobileCountryCode) {
	this.mobileCountryCode = mobileCountryCode;
    }

    public String getOtherGivingType() {
	return otherGivingType;
    }

    public void setOtherGivingType(String otherGivingType) {
	this.otherGivingType = otherGivingType;
    }

    public DonationTransactionStatus getDonationTransactionStatus() {
	return donationTransactionStatus;
    }

    public void setDonationTransactionStatus(
	    DonationTransactionStatus donationTransactionStatus) {
	this.donationTransactionStatus = donationTransactionStatus;
    }

    public DonationThankyouEmailSendStatus getDonationThankyouEmailSendStatus() {
	return donationThankyouEmailSendStatus;
    }

    public void setDonationThankyouEmailSendStatus(
	    DonationThankyouEmailSendStatus donationThankyouEmailSendStatus) {
	this.donationThankyouEmailSendStatus = donationThankyouEmailSendStatus;
    }

    public String getPaymentId() {
	return paymentId;
    }

    public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
    }

    public Date getCancelDate() {
	return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    public Date getCompleteDate() {
	return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
	this.completeDate = completeDate;
    }

    public BigDecimal getAmountTotal() {
	return amountTotal;
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

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

    public String getTownOrCity() {
	return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
	this.townOrCity = townOrCity;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
	this.amountTotal = amountTotal;
    }

    public Title getTitle() {
	return title;
    }

    public void setTitle(Title title) {
	this.title = title;
    }

    public boolean isMember() {
	return isMember;
    }

    public void setMember(boolean isMember) {
	this.isMember = isMember;
    }

    public boolean isInMailingList() {
	return isInMailingList;
    }

    public void setInMailingList(boolean isInMailingList) {
	this.isInMailingList = isInMailingList;
    }

    public boolean isGiftAidToday() {
	return isGiftAidToday;
    }

    public void setGiftAidToday(boolean isGiftAidToday) {
	this.isGiftAidToday = isGiftAidToday;
    }

    public boolean isGiftAidPast4Years() {
	return isGiftAidPast4Years;
    }

    public void setGiftAidPast4Years(boolean isGiftAidPast4Years) {
	this.isGiftAidPast4Years = isGiftAidPast4Years;
    }

    public boolean isGiftAidFuture() {
	return isGiftAidFuture;
    }

    public void setGiftAidFuture(boolean isGiftAidFuture) {
	this.isGiftAidFuture = isGiftAidFuture;
    }

    public BigDecimal getOffering() {
	return offering;
    }

    public void setOffering(BigDecimal offering) {
	this.offering = offering;
    }

    public BigDecimal getTithe() {
	return tithe;
    }

    public void setTithe(BigDecimal tithe) {
	this.tithe = tithe;
    }

    public BigDecimal getBuildingFund() {
	return buildingFund;
    }

    public void setBuildingFund(BigDecimal buildingFund) {
	this.buildingFund = buildingFund;
    }

    public BigDecimal getThanksGiving() {
	return thanksGiving;
    }

    public void setThanksGiving(BigDecimal thanksGiving) {
	this.thanksGiving = thanksGiving;
    }

    public BigDecimal getSpecialOffering() {
	return specialOffering;
    }

    public void setSpecialOffering(BigDecimal specialOffering) {
	this.specialOffering = specialOffering;
    }

    public BigDecimal getOtherOffering() {
	return otherOffering;
    }

    public void setOtherOffering(BigDecimal otherOffering) {
	this.otherOffering = otherOffering;
    }
}
