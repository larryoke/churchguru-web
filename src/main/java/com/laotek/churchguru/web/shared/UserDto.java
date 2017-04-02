package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;
import com.laotek.churchguru.model.shared.enums.UserRoleName;

public class UserDto implements IsSerializable {
    private Long id;
    private String identifier;
    private String username;
    private String forenames;
    private String surname;
    private String emailAddress;
    private PhoneDto mobile;
    private UserAccountStatus userAccountStatus;
    private String userProfileName;
    private String userProfileIdentifier;

    private UserRoleName memberRole;

    private UserRoleName quickEmailRole;
    private UserRoleName smsRole;
    private UserRoleName departmentRole;

    private UserRoleName appUserRole;
    private UserRoleName organisationRole;
    private UserRoleName notificationRole;

    private String firebaseApiKey;
    private String firebaseAuthDomain;
    private String firebaseDatabaseURL;
    private String firebaseStorageBucket;
    private String firebaseMessagingSenderId;
    private String firebaseCustomToken;

    public UserDto() {
    }

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

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
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

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public PhoneDto getMobile() {
	return mobile;
    }

    public void setMobile(PhoneDto mobile) {
	this.mobile = mobile;
    }

    public UserAccountStatus getUserAccountStatus() {
	return userAccountStatus;
    }

    public void setUserAccountStatus(UserAccountStatus userAccountStatus) {
	this.userAccountStatus = userAccountStatus;
    }

    public String getUserProfileName() {
	return userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
	this.userProfileName = userProfileName;
    }

    public String getUserProfileIdentifier() {
	return userProfileIdentifier;
    }

    public void setUserProfileIdentifier(String userProfileIdentifier) {
	this.userProfileIdentifier = userProfileIdentifier;
    }

    public void setMemberRole(UserRoleName memberRole) {
	this.memberRole = memberRole;
    }

    public UserRoleName getMemberRole() {
	return memberRole;
    }

    public UserRoleName getQuickEmailRole() {
	return quickEmailRole;
    }

    public void setQuickEmailRole(UserRoleName quickEmailRole) {
	this.quickEmailRole = quickEmailRole;
    }

    public UserRoleName getSmsRole() {
	return smsRole;
    }

    public void setSmsRole(UserRoleName smsRole) {
	this.smsRole = smsRole;
    }

    public UserRoleName getDepartmentRole() {
	return departmentRole;
    }

    public void setDepartmentRole(UserRoleName departmentRole) {
	this.departmentRole = departmentRole;
    }

    public UserRoleName getAppUserRole() {
	return appUserRole;
    }

    public void setAppUserRole(UserRoleName appUserRole) {
	this.appUserRole = appUserRole;
    }

    public UserRoleName getOrganisationRole() {
	return organisationRole;
    }

    public void setOrganisationRole(UserRoleName organisationRole) {
	this.organisationRole = organisationRole;
    }

    public UserRoleName getNotificationRole() {
	return notificationRole;
    }

    public void setNotificationRole(UserRoleName notificationRole) {
	this.notificationRole = notificationRole;
    }

    public String getFirebaseApiKey() {
	return firebaseApiKey;
    }

    public void setFirebaseApiKey(String firebaseApiKey) {
	this.firebaseApiKey = firebaseApiKey;
    }

    public String getFirebaseAuthDomain() {
	return firebaseAuthDomain;
    }

    public void setFirebaseAuthDomain(String firebaseAuthDomain) {
	this.firebaseAuthDomain = firebaseAuthDomain;
    }

    public String getFirebaseDatabaseURL() {
	return firebaseDatabaseURL;
    }

    public void setFirebaseDatabaseURL(String firebaseDatabaseURL) {
	this.firebaseDatabaseURL = firebaseDatabaseURL;
    }

    public String getFirebaseStorageBucket() {
	return firebaseStorageBucket;
    }

    public void setFirebaseStorageBucket(String firebaseStorageBucket) {
	this.firebaseStorageBucket = firebaseStorageBucket;
    }

    public String getFirebaseMessagingSenderId() {
	return firebaseMessagingSenderId;
    }

    public void setFirebaseMessagingSenderId(String firebaseMessagingSenderId) {
	this.firebaseMessagingSenderId = firebaseMessagingSenderId;
    }

    public void setFirebaseCustomToken(String firebaseCustomToken) {
	this.firebaseCustomToken = firebaseCustomToken;
    }

    public String getFirebaseCustomToken() {
	return firebaseCustomToken;
    }
}
