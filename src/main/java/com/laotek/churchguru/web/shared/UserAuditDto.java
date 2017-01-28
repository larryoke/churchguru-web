package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;

public class UserAuditDto implements IsSerializable {
	private UserAuditTypeName userAuditTypeName;
	private String dateAndTime;
	private String details;
	
	public UserAuditTypeName getUserAuditTypeName() {
		return userAuditTypeName;
	}
	public void setUserAuditTypeName(UserAuditTypeName userAuditTypeName) {
		this.userAuditTypeName = userAuditTypeName;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
