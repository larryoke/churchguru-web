package com.laotek.churchguru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false, unique = false)
    private String emailAddress;

    @Column(nullable = false)
    private String forenames;

    @Column(nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryCode mobileCountryCode;

    @Column(nullable = false, unique = false)
    private String mobile;

    @Column
    private String clientSessionId;

    @Enumerated(EnumType.STRING)
    private UserAccountStatus userAccountStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @ManyToOne
    @JoinColumn(name = "USER_PROFILE_ID", insertable = true, updatable = true, nullable = false)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<UserAudit> userAudits = new ArrayList<UserAudit>();

    @ManyToOne
    @JoinColumn(name = "ORG_ID", insertable = true, updatable = false, nullable = false)
    private Organisation organisation;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public UserAccountStatus getUserAccountStatus() {
	return userAccountStatus;
    }

    public void setUserAccountStatus(UserAccountStatus status) {
	this.userAccountStatus = status;
    }

    public List<UserAudit> getUserLogs() {
	return userAudits;
    }

    public String getClientSessionId() {
	return clientSessionId;
    }

    public void setClientSessionId(String currentSessionId) {
	this.clientSessionId = currentSessionId;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Date getLastLoginDate() {
	return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
    }

    public Organisation getOrganisation() {
	return organisation;
    }

    public void setOrganisation(Organisation organisation) {
	this.organisation = organisation;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public CountryCode getMobileCountryCode() {
	return mobileCountryCode;
    }

    public void setMobileCountryCode(CountryCode mobileCountryCode) {
	this.mobileCountryCode = mobileCountryCode;
    }

    public UserProfile getUserProfile() {
	return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
	this.userProfile = userProfile;
    }

}
