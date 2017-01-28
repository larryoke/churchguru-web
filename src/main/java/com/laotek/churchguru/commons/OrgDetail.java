package com.laotek.churchguru.commons;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orgDetail")
public class OrgDetail {

    private String orgName;
    private String hostName;
    private String subdomain;
    private String adminEmail;
    private String churchGuruEmail;
    private Date appLunchDate;
    private String addressLine1;
    private String addressLine2;
    private String postcode;

    public String getOrgName() {
	return orgName;
    }

    public String getHostName() {
	return hostName;
    }

    public String getSubdomain() {
	return subdomain;
    }

    public String getAdminEmail() {
	return adminEmail;
    }

    public String getChurchGuruEmail() {
	return churchGuruEmail;
    }

    public Date getAppLunchDate() {
	return appLunchDate;
    }

    public String getAddressLine1() {
	return addressLine1;
    }

    public String getAddressLine2() {
	return addressLine2;
    }

    public String getPostcode() {
	return postcode;
    }

    @XmlElement
    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    @XmlElement
    public void setHostName(String hostName) {
	this.hostName = hostName;
    }

    @XmlElement
    public void setSubdomain(String subdomain) {
	this.subdomain = subdomain;
    }

    @XmlElement
    public void setAdminEmail(String adminEmail) {
	this.adminEmail = adminEmail;
    }

    @XmlElement
    public void setChurchGuruEmail(String churchGuruEmail) {
	this.churchGuruEmail = churchGuruEmail;
    }

    @XmlElement
    public void setAppLunchDate(Date appLunchDate) {
	this.appLunchDate = appLunchDate;
    }

    @XmlElement
    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    @XmlElement
    public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
    }

    @XmlElement
    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

}
