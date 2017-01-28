package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ApplicationConstants extends Constants{
	
	  public static ApplicationConstants instance =  GWT.create(ApplicationConstants.class);
	  
	  @DefaultStringValue("loginPageToken")
	  String loginPageToken();
	  
	  @DefaultStringValue("historyHomePageToken")
	  String historyHomePageToken();
	  
	  @DefaultStringValue("planToken")
	  String historyPlanToken();
	  
	  @DefaultStringValue("createNewAccountToken")
	  String historyCreateNewAccountToken();
	  
	  @DefaultStringValue("newAccountSubmitToken")
	  String newAccountSubmitToken();
	  
	  @DefaultStringValue("chooseThemesToken")
	  String historyChooseThemesToken();
	  
	  @DefaultStringValue("historyMemberPageToken")
	  String historyMemberPageToken();
	  
	  @DefaultStringValue("900px")
	  String screenWidth();
	  
	  @DefaultIntValue(0)
	  int borderLinesSize();
	  
	  @DefaultStringValue("discoSessionId")
	  String sessionIdName();

}
