package com.laotek.churchguru.web.client.activity.donation;

import java.io.Serializable;
import java.math.BigDecimal;

import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.web.shared.FullnameDto;

public class DonationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private FullnameDto fullnameDto;
    private String fullAddress;
    private String donationDateAsString;
    private String currency;
    private String otherSpecified;

    private BigDecimal offering;
    private BigDecimal tithe;
    private BigDecimal buildingFund;
    private BigDecimal thanksGiving;
    private BigDecimal specialOffering;
    private BigDecimal otherOffering;
    private BigDecimal amountTotal;

    private String emailAddress;
    private DonationTransactionStatus donationTransactionStatus;
    private boolean isMember;
    private boolean isInMailingList;

    private boolean isGiftAidToday;
    private boolean isGiftAidPast4Years;
    private boolean isGiftAidFuture;

    public FullnameDto getFullnameDto() {
	return fullnameDto;
    }

    public void setFullnameDto(FullnameDto fullnameDto) {
	this.fullnameDto = fullnameDto;
    }

    public String getDonationDateAsString() {
	return donationDateAsString;
    }

    public void setDonationDateAsString(String donationDateAsString) {
	this.donationDateAsString = donationDateAsString;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public String getFullAddress() {
	if (fullAddress == null)
	    return "";
	return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
	this.fullAddress = fullAddress;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public DonationTransactionStatus getDonationTransactionStatus() {
	return donationTransactionStatus;
    }

    public void setDonationTransactionStatus(
	    DonationTransactionStatus donationTransactionStatus) {
	this.donationTransactionStatus = donationTransactionStatus;
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

    public BigDecimal getAmountTotal() {
	return amountTotal;
    }

    public String getOtherSpecified() {
	return otherSpecified;
    }

    public void setOtherSpecified(String otherSpecified) {
	this.otherSpecified = otherSpecified;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
	this.amountTotal = amountTotal;
    }
}
