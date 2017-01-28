package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MemberSearchCategory implements Serializable, IsSerializable {
    MEMBERS("Members"), MARITAL_STATUSES("Marital Statuses"), AGE_GROUPS("Age Groups"), FAMILIES("Families"), BIRTHDAYS(
	    "Birthdays"), BIRTH_MONTHS("Birth Months"), WORKERS("All Workers");

    private String desc;

    private MemberSearchCategory(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static MemberSearchCategory findCategory(MemberSearchType searchType) {
	if (MemberSearchType.ALL_MEMBERS.equals(searchType) || MemberSearchType.ALL_MEN.equals(searchType)
		|| MemberSearchType.ALL_WOMEN.equals(searchType) || MemberSearchType.ALL_PARENTS.equals(searchType)
		|| MemberSearchType.EX_MEMBERS.equals(searchType)) {
	    return MemberSearchCategory.MEMBERS;

	} else if (MemberSearchType.BIRTHDAY_TODAY.equals(searchType)
		|| MemberSearchType.BIRTHDAY_YESTERDAY.equals(searchType)
		|| MemberSearchType.BIRTHDAY_TOMORROW.equals(searchType)
		|| MemberSearchType.BIRTHDAY_LAST_WEEK.equals(searchType)
		|| MemberSearchType.BIRTHDAY_THIS_WEEK.equals(searchType)
		|| MemberSearchType.BIRTHDAY_NEXT_WEEK.equals(searchType)) {
	    return MemberSearchCategory.BIRTHDAYS;

	} else if (MemberSearchType.BIRTH_MONTTH_JAN.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_FEB.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_MAR.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_APR.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_MAY.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_JUN.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_JUL.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_AUG.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_SEP.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_OCT.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_NOV.equals(searchType)
		|| MemberSearchType.BIRTH_MONTTH_DEC.equals(searchType)) {
	    return MemberSearchCategory.BIRTH_MONTHS;

	} else if (MemberSearchType.EIGHTEEN_TO_TWENTY_TWO.equals(searchType)
		|| MemberSearchType.FIFTY_ONE_TO_SIX.equals(searchType)
		|| MemberSearchType.FORTY_ONE_TO_FORTY_FIVE.equals(searchType)
		|| MemberSearchType.FORTY_SIX_TO_FIFTY.equals(searchType)
		|| MemberSearchType.SEVENTY_ONE_PLUS.equals(searchType)
		|| MemberSearchType.SIXTY_ONE_TO_SEVENTY.equals(searchType)
		|| MemberSearchType.THIRTY_ONE_TO_THIRTY_FIVE.equals(searchType)
		|| MemberSearchType.THIRTY_SIX_TO_FORTY.equals(searchType)
		|| MemberSearchType.TWENTY_THREE_TO_THIRTY.equals(searchType)
		|| MemberSearchType.UNDER_EIGHTEEN.equals(searchType)) {
	    return MemberSearchCategory.AGE_GROUPS;

	} else if (MemberSearchType.SINGLE.equals(searchType) || MemberSearchType.MARRIED.equals(searchType)
		|| MemberSearchType.SEPARATED.equals(searchType) || MemberSearchType.DIVORCED.equals(searchType)
		|| MemberSearchType.WIDOWED.equals(searchType)) {
	    return MemberSearchCategory.MARITAL_STATUSES;

	} else if (MemberSearchType.ALL_DEPT_WORKERS.equals(searchType)
		|| MemberSearchType.ALL_NON_WORKERS.equals(searchType)
		|| MemberSearchType.ALL_DEPT_LEADERS.equals(searchType)
		|| MemberSearchType.ALL_DEPT_DEPUTIES.equals(searchType)
		|| MemberSearchType.ALL_DEPT_LEADERS_AND_DEPUTIES.equals(searchType)) {
	    return MemberSearchCategory.WORKERS;

	} else {
	    return MemberSearchCategory.FAMILIES;
	}

    }
}
