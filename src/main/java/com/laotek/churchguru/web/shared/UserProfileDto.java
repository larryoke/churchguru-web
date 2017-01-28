package com.laotek.churchguru.web.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.UserRoleName;

public class UserProfileDto implements IsSerializable {
    private String userProfileName;
    private String userProfileIdentifier;

    private UserRoleName memberRole;
    private UserRoleName quickEmailRole;
    private UserRoleName smsRole;
    private UserRoleName departmentRole;
    private UserRoleName appUserRole;
    private UserRoleName organisationRole;
    private UserRoleName notificationRole;
    private UserRoleName donationRole;

    private List<UserDto> userDtos = new ArrayList<UserDto>();

    private String createdDate;
    private String updatedDate;

    public UserProfileDto() {
    }

    public UserRoleName getMemberRole() {
	return memberRole;
    }

    public void setMemberRole(UserRoleName memberRole) {
	this.memberRole = memberRole;
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

    public String getUserProfileName() {
	return userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
	this.userProfileName = userProfileName;
    }

    public List<UserDto> getUserDtos() {
	return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
	this.userDtos = userDtos;
    }

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
	return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
	this.updatedDate = updatedDate;
    }

    public String getUserProfileIdentifier() {
	return userProfileIdentifier;
    }

    public void setUserProfileIdentifier(String userProfileIdentifier) {
	this.userProfileIdentifier = userProfileIdentifier;
    }

    public UserRoleName getNotificationRole() {
	return notificationRole;
    }

    public void setNotificationRole(UserRoleName notificationRole) {
	this.notificationRole = notificationRole;
    }

    public UserRoleName getDonationRole() {
	return donationRole;
    }

    public void setDonationRole(UserRoleName donationRole) {
	this.donationRole = donationRole;
    }

}
