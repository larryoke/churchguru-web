package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum GuestSearchType implements Serializable, IsSerializable {

    ALL_GUESTS("All Guests", "All Guests",
	    "from Guest g where g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), ALL_MALE_GUEST(
		    "All Male Guests", "All Male Guests",
		    "from Guest g where g.title is 'MR' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), ALL_FEMALE_GUEST(
			    "All Female Guests", "All Female Guests",
			    "from Guest g where g.title is not 'MR' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), EX_GUESTS(
				    "Ex-Guests", "Ex-Guests",
				    "from Guest g where g.organisation = :org and g.guestAccountStatus is 'EX_GUESTS'"),

    SINGLE("Singles", "Single Guests",
	    "from Guest g where g.maritalStatus is 'SINGLE' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), MARRIED(
		    "Married", "Married Guests",
		    "from Guest g where g.maritalStatus is 'MARRIED' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), SEPARATED(
			    "Separated", "Separated Guests",
			    "from Guest g where g.maritalStatus is 'SEPARATED' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), DIVORCED(
				    "Divorced", "Divorced Guests",
				    "from Guest g where g.maritalStatus is 'DIVORCED' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), WIDOWED(
					    "Widowed", "Widowed Guests",
					    "from Guest g where g.maritalStatus is 'WIDOWED' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"),

    UNDER_EIGHTEEN("Under 18", "Guests under age 18",
	    "from Guest g where g.ageGroup is 'UNDER_EIGHTEEN' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), EIGHTEEN_TO_TWENTY_TWO(
		    "18-22", "Guests between age 18 and 22",
		    "from Guest g where g.ageGroup is 'EIGHTEEN_TO_TWENTY_TWO' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), TWENTY_THREE_TO_THIRTY(
			    "23-30", "Guests between age 23 and 30",
			    "from Guest g where g.ageGroup is 'TWENTY_THREE_TO_THIRTY' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), THIRTY_ONE_TO_THIRTY_FIVE(
				    "31-35", "Guests between age 31 and 35",
				    "from Guest g where g.ageGroup is 'THIRTY_ONE_TO_THIRTY_FIVE' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), THIRTY_SIX_TO_FORTY(
					    "36-40", "Guests between age 36 and 40",
					    "from Guest g where g.ageGroup is 'THIRTY_SIX_TO_FORTY' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), FORTY_ONE_TO_FORTY_FIVE(
						    "41-45", "Guests between age 41 and 45",
						    "from Guest g where g.ageGroup is 'FORTY_ONE_TO_FORTY_FIVE' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), FORTY_SIX_TO_FIFTY(
							    "46-50", "Guests between age 46 and 50",
							    "from Guest g where g.ageGroup is 'FORTY_SIX_TO_FIFTY' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), FIFTY_ONE_TO_SIX(
								    "51-60", "Guests between age 51 and 60",
								    "from Guest g where g.ageGroup is 'FIFTY_ONE_TO_SIX' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), SIXTY_ONE_TO_SEVENTY(
									    "61-70", "Guests between age 61 and 70",
									    "from Guest g where g.ageGroup is 'SIXTY_ONE_TO_SEVENTY' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), SEVENTY_ONE_PLUS(
										    "71+", "Guests age 71+",
										    "from Guest g where g.ageGroup is 'SEVENTY_ONE_PLUS' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"),

    HAS_EMAIL_ADDRESS("Has a Email", "Has a Email Address",
	    "from Guest g where g.emailAddress is not NULL and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), HAS_LAND_NUMBER(
		    "Has a Land Number", "Has a Land Line Number",
		    "from Guest g where g.landNumber is not NULL and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), HAS_MOBILE_NUMBER(
			    "Has a Mobile Number", "Has a Mobile Number",
			    "from Guest g where g.mobile is not NULL and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"),

    VISITING("Just Visiting", "Just Visiting",
	    "from Guest g where g.guestType is 'VISITING' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), JOINING(
		    "Joing", "Joining",
		    "from Guest g where g.guestType is 'JOINING' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"),

    INVITED_BY_FRIEND("A Friend", "Invited by a friend",
	    "from Guest g where g.invitationType is 'FRIEND' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), INVITED_THROUGH_BANNER(
		    "A Banner", "Invited through a banner",
		    "from Guest g where g.invitationType is 'BANNER' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), INVITED_THROUGH_FLYER(
			    "A Flyer", "Invited through a flyer",
			    "from Guest g where g.invitationType is 'FLYER' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), INVITED_THROUGH_EMAIL(
				    "An Email", "Invited through an Email",
				    "from Guest g where g.invitationType is 'EMAIL' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"), INVITED_BY_OTHER_MEANS(
					    "Other", "Invited through other means",
					    "from Guest g where g.invitationType is 'OTHER' and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'"),

    REQUIRES_COUNCILLOR_VISIT("Requires Councillor Visit", "Requires Councillor Visit",
	    "from Guest g where g.requireCouncillorVisit is true and g.organisation = :org and g.guestAccountStatus is not 'EX_GUEST'");

    private String desc;
    private String longDesc;
    private String query;

    private GuestSearchType(String desc, String longDesc, String query) {
	this.desc = desc;
	this.longDesc = longDesc;
	this.query = query;
    }

    public String getDesc() {
	return desc;
    }

    public String getQuery() {
	return query;
    }

    public String getLongDesc() {
	return longDesc;
    }

}
