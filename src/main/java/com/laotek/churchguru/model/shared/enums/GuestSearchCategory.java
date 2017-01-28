package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum GuestSearchCategory implements Serializable, IsSerializable {
    GUESTS("Guests"), MARITAL_STATUSES("Marital Statuses"), AGE_GROUPS("Age Groups"), INVITATION_TYPE(
	    "Invitation Type"), COMMUNICATION("Communication");

    private String desc;

    private GuestSearchCategory(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static GuestSearchCategory findCategory(GuestSearchType searchType) {
	if (GuestSearchType.ALL_GUESTS.equals(searchType) || GuestSearchType.ALL_MALE_GUEST.equals(searchType)
		|| GuestSearchType.ALL_FEMALE_GUEST.equals(searchType)
		|| GuestSearchType.EX_GUESTS.equals(searchType)) {
	    return GuestSearchCategory.GUESTS;

	} else if (GuestSearchType.EIGHTEEN_TO_TWENTY_TWO.equals(searchType)
		|| GuestSearchType.FIFTY_ONE_TO_SIX.equals(searchType)
		|| GuestSearchType.FORTY_ONE_TO_FORTY_FIVE.equals(searchType)
		|| GuestSearchType.FORTY_SIX_TO_FIFTY.equals(searchType)
		|| GuestSearchType.SEVENTY_ONE_PLUS.equals(searchType)
		|| GuestSearchType.SIXTY_ONE_TO_SEVENTY.equals(searchType)
		|| GuestSearchType.THIRTY_ONE_TO_THIRTY_FIVE.equals(searchType)
		|| GuestSearchType.THIRTY_SIX_TO_FORTY.equals(searchType)
		|| GuestSearchType.TWENTY_THREE_TO_THIRTY.equals(searchType)
		|| GuestSearchType.UNDER_EIGHTEEN.equals(searchType)) {
	    return GuestSearchCategory.AGE_GROUPS;

	} else if (GuestSearchType.SINGLE.equals(searchType) || GuestSearchType.MARRIED.equals(searchType)
		|| GuestSearchType.DIVORCED.equals(searchType) || GuestSearchType.SEPARATED.equals(searchType)) {
	    return GuestSearchCategory.MARITAL_STATUSES;

	} else if (GuestSearchType.HAS_EMAIL_ADDRESS.equals(searchType)
		|| GuestSearchType.HAS_LAND_NUMBER.equals(searchType)
		|| GuestSearchType.HAS_MOBILE_NUMBER.equals(searchType)) {
	    return GuestSearchCategory.COMMUNICATION;

	} else if (GuestSearchType.INVITED_BY_FRIEND.equals(searchType)
		|| GuestSearchType.INVITED_THROUGH_BANNER.equals(searchType)
		|| GuestSearchType.INVITED_THROUGH_FLYER.equals(searchType)
		|| GuestSearchType.INVITED_THROUGH_EMAIL.equals(searchType)
		|| GuestSearchType.INVITED_BY_OTHER_MEANS.equals(searchType)) {
	    return GuestSearchCategory.INVITATION_TYPE;

	} else {
	    return null;
	}
    }
}
