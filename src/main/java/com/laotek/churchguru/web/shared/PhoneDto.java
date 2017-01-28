package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.CountryCode;

public class PhoneDto implements IsSerializable {

    private CountryCode countryCode;
    private String number;

    public PhoneDto() {
    }

    public PhoneDto(CountryCode countryCode, String number) {
	super();
	this.countryCode = countryCode;
	this.number = number;
    }

    public CountryCode getCountryCode() {
	return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
	this.countryCode = countryCode;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    @Override
    public boolean equals(Object another) {
	if (another != null) {
	    if (another instanceof PhoneDto) {
		PhoneDto other = (PhoneDto) another;
		if (other != null) {
		    String otherNumber = other.getNumber();
		    if (number.equals(otherNumber)) {
			CountryCode otherCode = other.getCountryCode();
			if (countryCode.equals(otherCode)) {
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }

    @Override
    public String toString() {
	return "<img src=\"" + countryCode.getImageUrl() + "\"> " + number;
    }

}
