package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MemberSearchType implements Serializable, IsSerializable {

    ALL_MEMBERS("All Members", "All Members",
	    "from Member m where m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_MEN(
		    "All Men", "All Men",
		    "from Member m where m.title is 'MR' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_WOMEN(
			    "All Women", "AllWomen",
			    "from Member m where m.title is not 'MR' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_PARENTS(
				    "All Parents", "All Parents",
				    "from Member m where m.childrenAtHome is not empty and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), EX_MEMBERS(
					    "Ex-Members", "Ex-Members",
					    "from Member m where m.organisation = :org and m.memberAccountStatus is 'EX_MEMBER'"),

    SINGLE("Singles", "Single Members",
	    "from Member m where m.maritalStatus is 'SINGLE' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), MARRIED(
		    "Married", "Married Members",
		    "from Member m where m.maritalStatus is 'MARRIED' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), SEPARATED(
			    "Separated", "Separated Members",
			    "from Member m where m.maritalStatus is 'SEPARATED' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), DIVORCED(
				    "Divorced", "Divorced Members",
				    "from Member m where m.maritalStatus is 'DIVORCED' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), WIDOWED(
					    "Widowed", "Widowed Members",
					    "from Member m where m.maritalStatus is 'WIDOWED' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_TODAY("Birthdays Today", "Birthdays Today",
	    "from Member m where m.birthDay = :birthDay and m.birthMonth = :birthMonth and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_YESTERDAY("Birthdays Yesterday", "Birthdays Yesterday",
	    "from Member m where m.birthDay = :birthDay and m.birthMonth = :birthMonth and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_TOMORROW("Birthdays Tomorrow", "Birthdays Tomorrow",
	    "from Member m where m.birthDay = :birthDay and m.birthMonth = :birthMonth and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_LAST_WEEK("Birthdays Last Week", "Birthdays Last Week", "from Member m "

	    + " where ((m.birthDay = :birthDay1 and m.birthMonth = :birthMonth1) "

	    + " or (m.birthDay = :birthDay2 and m.birthMonth = :birthMonth2) "

	    + " or (m.birthDay = :birthDay3 and m.birthMonth = :birthMonth3) "

	    + " or (m.birthDay = :birthDay4 and m.birthMonth = :birthMonth4) "

	    + " or (m.birthDay = :birthDay5 and m.birthMonth = :birthMonth5) "

	    + " or (m.birthDay = :birthDay6 and m.birthMonth = :birthMonth6) "

	    + " or (m.birthDay = :birthDay7 and m.birthMonth = :birthMonth7)) "

	    + " and m.organisation = :org "

	    + " and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_NEXT_WEEK("Birthdays Next Week", "Birthdays Next Week", "from Member m "

	    + " where ((m.birthDay = :birthDay1 and m.birthMonth = :birthMonth1) "

	    + " or (m.birthDay = :birthDay2 and m.birthMonth = :birthMonth2) "

	    + " or (m.birthDay = :birthDay3 and m.birthMonth = :birthMonth3) "

	    + " or (m.birthDay = :birthDay4 and m.birthMonth = :birthMonth4) "

	    + " or (m.birthDay = :birthDay5 and m.birthMonth = :birthMonth5) "

	    + " or (m.birthDay = :birthDay6 and m.birthMonth = :birthMonth6) "

	    + " or (m.birthDay = :birthDay7 and m.birthMonth = :birthMonth7)) "

	    + " and m.organisation = :org "

	    + " and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTHDAY_THIS_WEEK("Birthdays This Week", "Birthdays This Week", "from Member m "

	    + " where ((m.birthDay = :birthDay1 and m.birthMonth = :birthMonth1) "

	    + " or (m.birthDay = :birthDay2 and m.birthMonth = :birthMonth2) "

	    + " or (m.birthDay = :birthDay3 and m.birthMonth = :birthMonth3) "

	    + " or (m.birthDay = :birthDay4 and m.birthMonth = :birthMonth4) "

	    + " or (m.birthDay = :birthDay5 and m.birthMonth = :birthMonth5) "

	    + " or (m.birthDay = :birthDay6 and m.birthMonth = :birthMonth6) "

	    + " or (m.birthDay = :birthDay7 and m.birthMonth = :birthMonth7)) "

	    + " and m.organisation = :org "

	    + " and m.memberAccountStatus is not 'EX_MEMBER'"),

    BIRTH_MONTTH_JAN("Jan", "Members born in January",
	    "from Member m where m.birthMonth is 'Jan' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_FEB(
		    "Feb", "Members born in February",
		    "from Member m where m.birthMonth is 'Feb' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_MAR(
			    "Mar", "Members born in March",
			    "from Member m where m.birthMonth is 'Mar' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_APR(
				    "Apr", "Members born in April",
				    "from Member m where m.birthMonth is 'Apr' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_MAY(
					    "May", "Members born in May",
					    "from Member m where m.birthMonth is 'May' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_JUN(
						    "Jun", "Members born in June",
						    "from Member m where m.birthMonth is 'Jun' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_JUL(
							    "Jul", "Members born in July",
							    "from Member m where m.birthMonth is 'Jul' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_AUG(
								    "Aug", "Members born in August",
								    "from Member m where m.birthMonth is 'Aug' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_SEP(
									    "Sep", "Members born in September",
									    "from Member m where m.birthMonth is 'Sep' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_OCT(
										    "Oct", "Members born in October",
										    "from Member m where m.birthMonth is 'Oct' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_NOV(
											    "Nov",
											    "Members born in November",
											    "from Member m where m.birthMonth is 'Nov' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), BIRTH_MONTTH_DEC(
												    "Dec",
												    "Members born in December",
												    "from Member m where m.birthMonth is 'Dec' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    UNDER_EIGHTEEN("Under 18", "Members under age 18",
	    "from Member m where m.ageGroup is 'UNDER_EIGHTEEN' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), EIGHTEEN_TO_TWENTY_TWO(
		    "18-22", "Members between age 18 and 22",
		    "from Member m where m.ageGroup is 'EIGHTEEN_TO_TWENTY_TWO' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), TWENTY_THREE_TO_THIRTY(
			    "23-30", "Members between age 23 and 30",
			    "from Member m where m.ageGroup is 'TWENTY_THREE_TO_THIRTY' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), THIRTY_ONE_TO_THIRTY_FIVE(
				    "31-35", "Members between age 31 and 35",
				    "from Member m where m.ageGroup is 'THIRTY_ONE_TO_THIRTY_FIVE' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), THIRTY_SIX_TO_FORTY(
					    "36-40", "Members between age 36 and 40",
					    "from Member m where m.ageGroup is 'THIRTY_SIX_TO_FORTY' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), FORTY_ONE_TO_FORTY_FIVE(
						    "41-45", "Members between age 41 and 45",
						    "from Member m where m.ageGroup is 'FORTY_ONE_TO_FORTY_FIVE' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), FORTY_SIX_TO_FIFTY(
							    "46-50", "Members between age 46 and 50",
							    "from Member m where m.ageGroup is 'FORTY_SIX_TO_FIFTY' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), FIFTY_ONE_TO_SIX(
								    "51-60", "Members between age 51 and 60",
								    "from Member m where m.ageGroup is 'FIFTY_ONE_TO_SIX' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), SIXTY_ONE_TO_SEVENTY(
									    "61-70", "Members between age 61 and 70",
									    "from Member m where m.ageGroup is 'SIXTY_ONE_TO_SEVENTY' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), SEVENTY_ONE_PLUS(
										    "71+", "Members age 71+",
										    "from Member m where m.ageGroup is 'SEVENTY_ONE_PLUS' and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    ALL_DEPT_WORKERS("All Workers", "All Workers",
	    "from Member m where m.memberDepartments is not empty and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_NON_WORKERS(
		    "None Workers", "None Workers",
		    "from Member m where m.memberDepartments is empty and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_DEPT_LEADERS(
			    "Dept Leaders", "All Department Leaders",
			    "from Member m where m in (select mg.member from MemberDepartment mg where mg.departmentMemberStatus = 'LEADER') and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_DEPT_DEPUTIES(
				    "Dept Deputies", "All Department Deputies",
				    "from Member m where m in (select mg.member from MemberDepartment mg where mg.departmentMemberStatus = 'DEPUTY_LEADER') and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"), ALL_DEPT_LEADERS_AND_DEPUTIES(
					    "Leaders & Deputies", "All Department Leaders And Deputies",
					    "from Member m where m in (select mg.member from MemberDepartment mg where mg.departmentMemberStatus = 'LEADER' or mg.departmentMemberStatus = 'DEPUTY_LEADER') and m.organisation = :org and m.memberAccountStatus is not 'EX_MEMBER'"),

    DEPARTMENT_MEMBERS("Dept Member", "Department Members", null);

    private String desc;
    private String longDesc;
    private String query;

    private MemberSearchType(String desc, String longDesc, String query) {
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
