package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum AgeGroup implements Serializable, IsSerializable {
    UNDER_EIGHTEEN("Under 18"), EIGHTEEN_TO_TWENTY_TWO("18-22"), TWENTY_THREE_TO_THIRTY(
	    "23-30"), THIRTY_ONE_TO_THIRTY_FIVE("31-35"), THIRTY_SIX_TO_FORTY("36-40"), FORTY_ONE_TO_FORTY_FIVE(
		    "41-45"), FORTY_SIX_TO_FIFTY(
			    "46-50"), FIFTY_ONE_TO_SIX("51-60"), SIXTY_ONE_TO_SEVENTY("61-70"), SEVENTY_ONE_PLUS("71+");

    private String desc;

    private AgeGroup(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static AgeGroup find(String desc) {
	for (AgeGroup group : AgeGroup.values()) {
	    if (group.getDesc().equals(desc)) {
		return group;
	    }
	}
	return null;
    }
}
