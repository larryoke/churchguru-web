package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OrganisationDto implements IsSerializable {

    private String orgIdentifier;
    private String orgDomain;
    private String orgName;
    private String adminEmail;
    private String prayerRequestEmail;
    private String appLunchDate;
    private boolean hasLogo;
    private String pastorDeskMessage;

    private String serviceTimes;
    private String addressLine1;
    private String addressLine2;
    private String postcode;

    private String prayerRequestChurchAppTopic;
    private String pastorDeskChurchAppTopic;
    private String donationChurchAppTopic;

    private String noticesAndEventsChurchAppTopic;
    private String websiteChurchAppTopic;
    private String websiteUrl;

    private String twitterChurchAppTopic;
    private String facebookChurchAppTopic;
    private String listenChurchAppTopic;
    private String watchChurchAppTopic;
    private String aboutUsChurchAppTopic;
    private String aboutUsMessage;
    private String aboutPastorMessage;

    private boolean prayerRequestChurchAppTopicFlag;
    private boolean pastorDeskChurchAppTopicFlag;
    private boolean donationChurchAppTopicFlag;
    private boolean messagesChurchAppTopicFlag;
    private boolean twitterChurchAppTopicFlag;
    private boolean facebookChurchAppTopicFlag;
    private boolean listenChurchAppTopicFlag;
    private boolean watchChurchAppTopicFlag;
    private boolean aboutUsChurchAppTopicFlag;

    public String getOrgIdentifier() {
	return orgIdentifier;
    }

    public String getPrayerRequestChurchAppTopic() {
	return prayerRequestChurchAppTopic;
    }

    public void setPrayerRequestChurchAppTopic(String prayerRequestChurchAppTopic) {
	this.prayerRequestChurchAppTopic = prayerRequestChurchAppTopic;
    }

    public boolean isPrayerRequestChurchAppTopicFlag() {
	return prayerRequestChurchAppTopicFlag;
    }

    public void setPrayerRequestChurchAppTopicFlag(boolean prayerRequestChurchAppTopicFlag) {
	this.prayerRequestChurchAppTopicFlag = prayerRequestChurchAppTopicFlag;
    }

    public void setOrgIdentifier(String orgIdentifier) {
	this.orgIdentifier = orgIdentifier;
    }

    public String getOrgDomain() {
	return orgDomain;
    }

    public void setOrgDomain(String orgDomain) {
	this.orgDomain = orgDomain;
    }

    public String getOrgName() {
	return orgName;
    }

    public String getAboutPastorMessage() {
	return aboutPastorMessage;
    }

    public void setAboutPastorMessage(String aboutPastorMessage) {
	this.aboutPastorMessage = aboutPastorMessage;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public String getAdminEmail() {
	return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
	this.adminEmail = adminEmail;
    }

    public String getPrayerRequestEmail() {
	return prayerRequestEmail;
    }

    public void setPrayerRequestEmail(String prayerRequestEmail) {
	this.prayerRequestEmail = prayerRequestEmail;
    }

    public String getAppLunchDate() {
	return appLunchDate;
    }

    public void setAppLunchDate(String setAppLunchDate) {
	this.appLunchDate = setAppLunchDate;
    }

    public boolean hasLogo() {
	return hasLogo;
    }

    public void setHasLogo(boolean hasLogo) {
	this.hasLogo = hasLogo;
    }

    public String getPastorDeskMessage() {
	return pastorDeskMessage;
    }

    public void setPastorDeskMessage(String pastorDeskMessage) {
	this.pastorDeskMessage = pastorDeskMessage;
    }

    public String getPastorDeskChurchAppTopic() {
	return pastorDeskChurchAppTopic;
    }

    public void setPastorDeskChurchAppTopic(String pastorDeskChurchAppTopic) {
	this.pastorDeskChurchAppTopic = pastorDeskChurchAppTopic;
    }

    public String getDonationChurchAppTopic() {
	return donationChurchAppTopic;
    }

    public void setDonationChurchAppTopic(String donationChurchAppTopic) {
	this.donationChurchAppTopic = donationChurchAppTopic;
    }

    public String getNoticesAndEventsChurchAppTopic() {
	return noticesAndEventsChurchAppTopic;
    }

    public void setNoticesAndEventsChurchAppTopic(String otherNewsChurchAppTopic) {
	this.noticesAndEventsChurchAppTopic = otherNewsChurchAppTopic;
    }

    public String getTwitterChurchAppTopic() {
	return twitterChurchAppTopic;
    }

    public void setTwitterChurchAppTopic(String twitterChurchAppTopic) {
	this.twitterChurchAppTopic = twitterChurchAppTopic;
    }

    public String getFacebookChurchAppTopic() {
	return facebookChurchAppTopic;
    }

    public void setFacebookChurchAppTopic(String facebookChurchAppTopic) {
	this.facebookChurchAppTopic = facebookChurchAppTopic;
    }

    public String getListenChurchAppTopic() {
	return listenChurchAppTopic;
    }

    public void setListenChurchAppTopic(String listenChurchAppTopic) {
	this.listenChurchAppTopic = listenChurchAppTopic;
    }

    public String getWatchChurchAppTopic() {
	return watchChurchAppTopic;
    }

    public void setWatchChurchAppTopic(String watchChurchAppTopic) {
	this.watchChurchAppTopic = watchChurchAppTopic;
    }

    public String getAboutUsChurchAppTopic() {
	return aboutUsChurchAppTopic;
    }

    public void setAboutUsChurchAppTopic(String aboutUsChurchAppTopic) {
	this.aboutUsChurchAppTopic = aboutUsChurchAppTopic;
    }

    public String getWebsiteChurchAppTopic() {
	return websiteChurchAppTopic;
    }

    public void setWebsiteChurchAppTopic(String sundaySchoolAppTopic) {
	this.websiteChurchAppTopic = sundaySchoolAppTopic;
    }

    public boolean isDonationChurchAppTopicFlag() {
	return donationChurchAppTopicFlag;
    }

    public void setDonationChurchAppTopicFlag(boolean donationChurchAppTopicFlag) {
	this.donationChurchAppTopicFlag = donationChurchAppTopicFlag;
    }

    public boolean isOtherNewsChurchAppTopicFlag() {
	return messagesChurchAppTopicFlag;
    }

    public void setMessagesChurchAppTopicFlag(boolean otherNewsChurchAppTopicFlag) {
	this.messagesChurchAppTopicFlag = otherNewsChurchAppTopicFlag;
    }

    public boolean isTwitterChurchAppTopicFlag() {
	return twitterChurchAppTopicFlag;
    }

    public void setTwitterChurchAppTopicFlag(boolean twitterChurchAppTopicFlag) {
	this.twitterChurchAppTopicFlag = twitterChurchAppTopicFlag;
    }

    public boolean isFacebookChurchAppTopicFlag() {
	return facebookChurchAppTopicFlag;
    }

    public void setFacebookChurchAppTopicFlag(boolean facebookChurchAppTopicFlag) {
	this.facebookChurchAppTopicFlag = facebookChurchAppTopicFlag;
    }

    public boolean isListenChurchAppTopicFlag() {
	return listenChurchAppTopicFlag;
    }

    public void setListenChurchAppTopicFlag(boolean listenChurchAppTopicFlag) {
	this.listenChurchAppTopicFlag = listenChurchAppTopicFlag;
    }

    public boolean isAboutUsChurchAppTopicFlag() {
	return aboutUsChurchAppTopicFlag;
    }

    public void setAboutUsChurchAppTopicFlag(boolean aboutUsChurchAppTopicFlag) {
	this.aboutUsChurchAppTopicFlag = aboutUsChurchAppTopicFlag;
    }

    public boolean isPastorDeskChurchAppTopicFlag() {
	return pastorDeskChurchAppTopicFlag;
    }

    public void setPastorDeskChurchAppTopicFlag(boolean pastorDeskChurchAppTopicFlag) {
	this.pastorDeskChurchAppTopicFlag = pastorDeskChurchAppTopicFlag;
    }

    public boolean isWatchChurchAppTopicFlag() {
	return watchChurchAppTopicFlag;
    }

    public void setWatchChurchAppTopicFlag(boolean watchChurchAppTopicFlag) {
	this.watchChurchAppTopicFlag = watchChurchAppTopicFlag;
    }

    public String getWebsiteUrl() {
	return websiteUrl;
    }

    public String getAboutUsMessage() {
	return aboutUsMessage;
    }

    public String getServiceTimes() {
	return serviceTimes;
    }

    public void setServiceTimes(String serviceTimes) {
	this.serviceTimes = serviceTimes;
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

    public void setAboutUsMessage(String aboutUsMessage) {
	this.aboutUsMessage = aboutUsMessage;
    }

    public void setWebsiteUrl(String websiteUrl) {
	this.websiteUrl = websiteUrl;
    }

}
