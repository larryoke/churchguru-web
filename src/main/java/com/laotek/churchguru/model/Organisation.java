package com.laotek.churchguru.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;

import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.model.shared.enums.OrganisationAccountStatus;

@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORG_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String orgIdentifier;

    @Column(nullable = false, unique = true)
    private String orgName;

    @Column(nullable = false, unique = true)
    private String subdomain;

    @Column(nullable = false, unique = false)
    private String adminEmail;

    @Column(nullable = false, unique = false)
    private String prayerRequestEmail;

    @Column(nullable = false, unique = false)
    private String pastorDeskChurchAppTopic = "From the Pastor's desk";

    @Column(nullable = false, unique = false)
    private boolean pastorDeskChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String donationChurchAppTopic = "Give";

    @Column(nullable = false, unique = false)
    private boolean donationChurchAppTopicFlag = true;

    @Column(nullable = true, unique = false)
    private String websiteUrl = "trinitychapel.org.uk";

    @Column(nullable = true, unique = false)
    private String googleApiUrl;

    @Column(nullable = false, unique = false)
    private String noticesAndEventsChurchAppTopic = "Notices and Events";

    @Column(nullable = false, unique = false)
    private boolean messageChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String twitterChurchAppTopic = "Twitter";

    @Column(nullable = false, unique = false)
    private boolean twitterChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String facebookChurchAppTopic = "Facebook";

    @Column(nullable = false, unique = false)
    private boolean facebookChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String listenChurchAppTopic = "Listen";

    @Column(nullable = false, unique = false)
    private boolean listenChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String watchChurchAppTopic = "Watch";

    @Column(nullable = false, unique = false)
    private boolean watchChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private boolean prayerRequestChurchAppTopicFlag = true;

    @Column(nullable = false, unique = false)
    private String aboutUsChurchAppTopic = "About Us";

    @Column(nullable = false, unique = false)
    private String prayerRequestChurchAppTopic = "Prayer Request";

    @Column(nullable = false, unique = false)
    private boolean aboutUsChurchAppTopicFlag = true;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appLunchDate;

    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String serviceTimes = "Every Sunday 10am to 12:30pm";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String messageFromPastorsDesk;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String aboutUsMessage = "No message found";

    @Column(nullable = true, columnDefinition = "TEXT")
    private String aboutPastorMessage = "No message found";

    @Temporal(TemporalType.TIMESTAMP)
    private Date pastorDeskMessageLastUpdatedDate = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrganisationAccountStatus organisationAccountStatus;

    @OneToMany(mappedBy = "organisation")
    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "organisation")
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @OneToMany(mappedBy = "organisation")
    private Set<MemberMetaData> memberMetaData = new HashSet<MemberMetaData>();

    @OneToMany(mappedBy = "organisation")
    private Set<GuestMetaData> guestMetaDatas = new HashSet<GuestMetaData>();

    @OneToMany
    private Set<LogoItem> logoItems = new HashSet<LogoItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
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

    public Set<User> getUsers() {
	return users;
    }

    public void setUsers(Set<User> users) {
	this.users = users;
    }

    public String getSubdomain() {
	return subdomain;
    }

    public void setSubdomain(String subdomain) {
	this.subdomain = subdomain;
    }

    public Set<GuestMetaData> getGuestMetaDatas() {
	return guestMetaDatas;
    }

    public void setGuestMetaDatas(Set<GuestMetaData> guestMetaDatas) {
	this.guestMetaDatas = guestMetaDatas;
    }

    public Date getAppLunchDate() {
	return appLunchDate;
    }

    public void setAppLunchDate(Date appLunchDate) {
	this.appLunchDate = appLunchDate;
    }

    public String getFullAddress() {
	StringBuffer sb = new StringBuffer();
	sb.append(addressLine1);
	if (!StringUtils.isEmpty(addressLine2)) {
	    sb.append(" ");
	    sb.append(addressLine2);
	}
	sb.append(" ");
	sb.append(postcode);
	return sb.toString();
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

    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
    }

    public OrganisationAccountStatus getOrganisationAccountStatus() {
	return organisationAccountStatus;
    }

    public void setOrganisationAccountStatus(
	    OrganisationAccountStatus organisationAccountStatus) {
	this.organisationAccountStatus = organisationAccountStatus;
    }

    public String getOrgIdentifier() {
	return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
	this.orgIdentifier = orgIdentifier;
    }

    public Set<MemberMetaData> getMemberMetaData() {
	return memberMetaData;
    }

    public void setMemberMetaData(Set<MemberMetaData> memberMetaData) {
	this.memberMetaData = memberMetaData;
    }

    public String getAdminEmail() {
	return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
	this.adminEmail = adminEmail;
    }

    public Set<UserProfile> getUserProfiles() {
	return userProfiles;
    }

    public String getPrayerRequestEmail() {
	return prayerRequestEmail;
    }

    public void setPrayerRequestEmail(String prayerRequestEmail) {
	this.prayerRequestEmail = prayerRequestEmail;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
	this.userProfiles = userProfiles;
    }

    public Set<LogoItem> getLogoItems() {
	return logoItems;
    }

    public void setLogoItems(Set<LogoItem> logoItems) {
	this.logoItems = logoItems;
    }

    public String getMessageFromPastorsDesk() {
	return messageFromPastorsDesk;
    }

    public void setMessageFromPastorsDesk(String messageFromPastorsDesk) {
	this.messageFromPastorsDesk = messageFromPastorsDesk;
	this.pastorDeskMessageLastUpdatedDate = new Date();
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

    public Date getPastorDeskMessageLastUpdatedDate() {
	return pastorDeskMessageLastUpdatedDate;
    }

    public void setPastorDeskMessageLastUpdatedDate(
	    Date pastorDeskMessageLastUpdatedDate) {
	this.pastorDeskMessageLastUpdatedDate = pastorDeskMessageLastUpdatedDate;
    }

    public boolean isPastorDeskChurchAppTopicFlag() {
	return pastorDeskChurchAppTopicFlag;
    }

    public void setPastorDeskChurchAppTopicFlag(
	    boolean pastorDeskChurchAppTopicFlag) {
	this.pastorDeskChurchAppTopicFlag = pastorDeskChurchAppTopicFlag;
    }

    public boolean isDonationChurchAppTopicFlag() {
	return donationChurchAppTopicFlag;
    }

    public void setDonationChurchAppTopicFlag(boolean donationChurchAppTopicFlag) {
	this.donationChurchAppTopicFlag = donationChurchAppTopicFlag;
    }

    public boolean isMessagesChurchAppTopicFlag() {
	return messageChurchAppTopicFlag;
    }

    public void setMessagesChurchAppTopicFlag(boolean messagesChurchAppTopicFlag) {
	this.messageChurchAppTopicFlag = messagesChurchAppTopicFlag;
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

    public boolean isWatchChurchAppTopicFlag() {
	return watchChurchAppTopicFlag;
    }

    public void setWatchChurchAppTopicFlag(boolean watchChurchAppTopicFlag) {
	this.watchChurchAppTopicFlag = watchChurchAppTopicFlag;
    }

    public boolean isAboutUsChurchAppTopicFlag() {
	return aboutUsChurchAppTopicFlag;
    }

    public String getWebsiteUrl() {
	return websiteUrl;
    }

    public boolean isPrayerRequestChurchAppTopicFlag() {
	return prayerRequestChurchAppTopicFlag;
    }

    public void setPrayerRequestChurchAppTopicFlag(
	    boolean prayerRequestChurchAppTopicFlag) {
	this.prayerRequestChurchAppTopicFlag = prayerRequestChurchAppTopicFlag;
    }

    public String getPrayerRequestChurchAppTopic() {
	return prayerRequestChurchAppTopic;
    }

    public void setPrayerRequestChurchAppTopic(
	    String prayerRequestChurchAppTopic) {
	this.prayerRequestChurchAppTopic = prayerRequestChurchAppTopic;
    }

    public void setWebsiteUrl(String websiteUrl) {
	this.websiteUrl = websiteUrl;
    }

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

    public String getGoogleApiUrl() {
	return googleApiUrl;
    }

    public void setGoogleApiUrl(String googleApiUrl) {
	this.googleApiUrl = googleApiUrl;
    }

    public void setServiceTimes(String serviceTimes) {
	this.serviceTimes = serviceTimes;
    }

    public void setAboutUsChurchAppTopicFlag(boolean aboutUsChurchAppTopicFlag) {
	this.aboutUsChurchAppTopicFlag = aboutUsChurchAppTopicFlag;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Organisation other = (Organisation) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
