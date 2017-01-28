package com.laotek.churchguru.web.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EmailRecipientContext {
    private static EmailRecipientContext emailRecipientContext = new EmailRecipientContext();

    /**
     * seen is set once used in the compose screen
     */
    private boolean isSeen = false;

    private Map<String, Set<String>> memberRecipients = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> guestRecipients = new LinkedHashMap<String, Set<String>>();
    private Map<String, Set<String>> userRecipients = new LinkedHashMap<String, Set<String>>();

    public static EmailRecipientContext getInstance() {
	return emailRecipientContext;
    }

    public void addMemberRecipients(String searchType, Set<String> recipients) {
	memberRecipients.put(searchType, recipients);
    }

    public void addGuestRecipients(String searchType, Set<String> recipients) {
	guestRecipients.put(searchType, recipients);
    }

    public void addUserRecipients(String searchType, Set<String> recipients) {
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

    public boolean isSeen() {
	return isSeen;
    }

    public void setSeen(boolean isSeen) {
	this.isSeen = isSeen;
    }

}
