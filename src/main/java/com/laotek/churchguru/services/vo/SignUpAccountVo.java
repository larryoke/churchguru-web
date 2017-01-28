package com.laotek.churchguru.services.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.model.shared.enums.Title;

@XmlRootElement(name="SignUpAccountVo")
public class SignUpAccountVo {
	private String subdomain;
	private String churchName;
	

	private String churchAddressLine1;
	private String churchAddressLine2;
	private Country churchCounty;
	private String churchPostcode;
	
	private Title adminTitle;
	private String adminFirstname;
	private String adminSurname;

	private String adminEmailAddress;
	private String adminLandNumber;
	private String adminMobileNumber;

	private String adminUsername;
	private String adminPassword;
	
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
	public String getChurchAddressLine1() {
		return churchAddressLine1;
	}
	public void setChurchAddressLine1(String churchAddressLine1) {
		this.churchAddressLine1 = churchAddressLine1;
	}
	public String getChurchAddressLine2() {
		return churchAddressLine2;
	}
	public void setChurchAddressLine2(String churchAddressLine2) {
		this.churchAddressLine2 = churchAddressLine2;
	}
	public Country getChurchCounty() {
		return churchCounty;
	}
	public void setChurchCounty(Country churchCounty) {
		this.churchCounty = churchCounty;
	}
	public String getChurchPostcode() {
		return churchPostcode;
	}
	public void setChurchPostcode(String churchPostcode) {
		this.churchPostcode = churchPostcode;
	}
	public Title getAdminTitle() {
		return adminTitle;
	}
	public void setAdminTitle(Title adminTitle) {
		this.adminTitle = adminTitle;
	}
	public String getAdminFirstname() {
		return adminFirstname;
	}
	public void setAdminFirstname(String adminFirstname) {
		this.adminFirstname = adminFirstname;
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
}
