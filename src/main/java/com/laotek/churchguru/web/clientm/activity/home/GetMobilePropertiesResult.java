package com.laotek.churchguru.web.clientm.activity.home;

import net.customware.gwt.dispatch.shared.Result;

public class GetMobilePropertiesResult implements Result {
    private MobileProperties mobileProperties;

    public MobileProperties getMobileProperties() {
	return mobileProperties;
    }

    public void setMobileProperties(MobileProperties mobileProperties) {
	this.mobileProperties = mobileProperties;
    }
}
