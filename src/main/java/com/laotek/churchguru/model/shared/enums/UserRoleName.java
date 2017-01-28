package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum UserRoleName implements Serializable, IsSerializable {

    ORGANISATION_DATA_VIEW_ONLY("Can view organisation"),

    //
    ORGANISATION_DATA_CRUD("Can view, create, or update organisation", UserRoleName.ORGANISATION_DATA_VIEW_ONLY),

    MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY("Can view guest details"),
    //
    MEMBERS_AND_GUESTS_DETAILS_CRUD("Can view, create, or update guest details",
	    UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY),
    //
    //
    //
    QUICK_EMAIL_NOT_ALLOWED("Cannot send quick email"),
    //
    QUICK_EMAIL_ALLOWED("Can send quick email"),
    //
    //
    //
    USER_VIEW_NOT_ALLOWED("Cannot view user details"),
    //
    USER_VIEW_ALLOWED("Can view user details"),
    //
    USER_CRUD("Can view, create, and update users", UserRoleName.USER_VIEW_ALLOWED),
    //
    //
    //
    SMS_SEND_NOT_ALLOWED("Cannot send SMS"),
    //
    SMS_SEND_ALLOWED("Can send SMS"),
    //
    SMS_SEND_AND_BUY_ALLOWED("Can send and Top Up SMS", UserRoleName.SMS_SEND_ALLOWED),
    //
    //
    //
    DEPARTMENT_VIEW_NOT_ALLOWED("Cannot view departments details"),
    //
    DEPARTMENT_VIEW_ONLY("Can view departments details"),
    //
    DEPARTMENT_CRUD("Can view, create, and update department details", UserRoleName.DEPARTMENT_VIEW_ONLY),

    //
    //
    //
    NOTIFICATION_VIEW_NOT_ALLOWED("Cannot view notfications"),
    //
    NOTIFICATION_VIEW_ONLY("Can view notficiations"),
    //
    NOTIFICATION_CRUD("Can view, create, and update notifications", UserRoleName.NOTIFICATION_VIEW_ONLY),

    //
    //
    DONATION_VIEW_NOT_ALLOWED("Cannot view donations"),
    //
    DONATION_VIEW_ALLOWED("Donations view allowed");

    private String desc;

    private UserRoleName[] children;

    private UserRoleName(String desc, UserRoleName... childRoleNames) {
	this.desc = desc;
	this.children = childRoleNames;
    }

    public UserRoleName[] getChildren() {
	return children;
    }

    public String getDesc() {
	return desc;
    }
}
