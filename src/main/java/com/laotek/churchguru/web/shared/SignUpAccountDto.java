package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.Title;

public class SignUpAccountDto implements IsSerializable {
    private String registrationCode;
    private String subdomain;
    private String churchName;

    private Title adminTitle;
    private String adminForenames;
    private String adminSurname;

    private String adminEmailAddress;
    private String adminLandNumber;
    private String adminMobileNumber;

    private String adminUsername;
    private String adminPassword;
    private String adminConfirmPassword;

    public String getRegistrationCode() {
	return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
	this.registrationCode = registrationCode;
    }

    public String getSubdomain() {
	return subdomain;
    }

    public void setSubdomain(String subdomain) {
	this.subdomain = subdomain;
    }

    public String getChurchName() {
	return churchName;
    }

    public void setChurchName(String churchName) {
	this.churchName = churchName;
    }

    public Title getAdminTitle() {
	return adminTitle;
    }

    public void setAdminTitle(Title adminTitle) {
	this.adminTitle = adminTitle;
    }

    public String getAdminForenames() {
	return adminForenames;
    }

    public void setAdminForenames(String adminForenames) {
	this.adminForenames = adminForenames;
    }

    public String getAdminSurname() {
	return adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
	this.adminSurname = adminSurname;
    }

    public String getAdminEmailAddress() {
	return adminEmailAddress;
    }

    public void setAdminEmailAddress(String adminEmailAddress) {
	this.adminEmailAddress = adminEmailAddress;
    }

    public String getAdminLandNumber() {
	return adminLandNumber;
    }

    public void setAdminLandNumber(String adminLandNumber) {
	this.adminLandNumber = adminLandNumber;
    }

    public String getAdminMobileNumber() {
	return adminMobileNumber;
    }

    public void setAdminMobileNumber(String adminMobileNumber) {
	this.adminMobileNumber = adminMobileNumber;
    }

    public String getAdminUsername() {
	return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
	this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
	return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
	this.adminPassword = adminPassword;
    }

    public String getAdminConfirmPassword() {
	return adminConfirmPassword;
    }

    public void setAdminConfirmPassword(String adminConfirmPassword) {
	this.adminConfirmPassword = adminConfirmPassword;
    }

}
