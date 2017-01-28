package com.laotek.churchguru.web.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SmsRecipientContext {
	private static SmsRecipientContext textMessageRecipientContext = new SmsRecipientContext();
	
	private Map<String, Set<String>> memberRecipients  = new LinkedHashMap<String, Set<String>>();
	private Map<String, Set<String>> guestRecipients  = new LinkedHashMap<String, Set<String>>();
	private Map<String, Set<String>> userRecipients  = new LinkedHashMap<String, Set<String>>();
	
	public static SmsRecipientContext getInstance(){
		return textMessageRecipientContext;
	}
	
	public void addMemberRecipients(String searchType, Set<String> recipients){
		memberRecipients.put(searchType, recipients);
	}
	
	public void addGuestRecipients(String searchType, Set<String> recipients){
		guestRecipients.put(searchType, recipients);
	}
	
	public void addUserRecipients(String searchType, Set<String> recipients){
		userRecipients.put(searchType, recipients);
	}

	public Map<String, Set<String>> getMemberRecipients() {
		return memberRecipients;
	}

	public Map<String, Set<String>> getGuestRecipients() {
		return guestRecipients;
	}

	public Map<String, Set<String>> getUserRecipients() {
		return userRecipients;
	}
	
}
