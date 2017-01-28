package com.laotek.churchguru.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class SmsMessageSentResultItem {

    private String messageReceived;
    private long id;
    private String from;
    private boolean testMode;
    private int messageCount;
    private int messageLength;
    private int creditsAvailable;
    private int creditRequired;
    private int creditRemaining;

    public SmsMessageSentResultItem(long id, boolean testMode, String from,
	    String messageReceived, int messageCount, int messageLength,
	    int creditsAvailable, int creditRequired, int creditRemaining) {
	super();
	this.id = id;
	this.testMode = testMode;
	this.from = from;
	this.messageReceived = messageReceived;
	this.messageCount = messageCount;
	this.messageLength = messageLength;
	this.creditsAvailable = creditsAvailable;
	this.creditRequired = creditRequired;
	this.creditRemaining = creditRemaining;
    }

    public SmsMessageSentResultItem() {
    }

    // TestMode=1
    // <br>MessageReceived=testing
    // <br>Custom=1427488278205
    // <br>MessageCount=1
    // <br>From=ChurchGURU - using default (Submitted > 11 chars)
    // <br>CreditsAvailable=76
    // <br>MessageLength=7
    // <br>NumberContacts=1
    // <br>CreditsRequired=1
    // <br>CreditsRemaining=75
    // <br>Testmode Active - Nothing Sent
    // <br />OK

    public static List<SmsMessageSentResultItem> createSmsMessageSentResultItems(
	    String responseData) {
	List<SmsMessageSentResultItem> items = new ArrayList<SmsMessageSentResultItem>();
	for (String dataItem : responseData.split("<br />")) {

	    if (!StringUtils.hasText(dataItem)
		    || (StringUtils.hasText(dataItem) && !dataItem
			    .startsWith("TestMode"))) {
		continue;
	    }

	    String[] fields = dataItem.split("<br>");

	    long id = 0;
	    boolean testMode = false;
	    String from = null;
	    String messageReceived = null;
	    int messageCount = 0;
	    int messageLength = 0;
	    int creditsAvailable = 0;
	    int creditRequired = 0;
	    int creditRemaining = 0;

	    for (String field : fields) {
		if (field.contains("Custom")) {
		    id = Long.parseLong(field.split("=")[1]);

		} else if (field.contains("MessageReceived")) {
		    messageReceived = field.split("=")[1];

		} else if (field.contains("From")) {
		    from = field.split("=")[1];

		} else if (field.contains("TestMode")) {
		    String testModeStr = field.split("=")[1];
		    if (testModeStr.equals("1")) {
			testMode = true;
		    }

		} else if (field.contains("MessageCount")) {
		    messageCount = Integer.parseInt(field.split("=")[1]);

		} else if (field.contains("MessageLength")) {
		    messageLength = Integer.parseInt(field.split("=")[1]);

		} else if (field.contains("CreditsAvailable")) {
		    creditsAvailable = Integer.parseInt(field.split("=")[1]);

		} else if (field.contains("CreditsRequired")) {
		    creditRequired = Integer.parseInt(field.split("=")[1]);

		} else if (field.contains("CreditsRemaining")) {
		    creditRemaining = Integer.parseInt(field.split("=")[1]);
		}
	    }

	    items.add(new SmsMessageSentResultItem(id, testMode, from,
		    messageReceived, messageCount, messageLength,
		    creditsAvailable, creditRequired, creditRemaining));
	}
	return items;
    }

    public String getMessageReceived() {
	return messageReceived;
    }

    public long getId() {
	return id;
    }

    public String getFrom() {
	return from;
    }

    public boolean isTestMode() {
	return testMode;
    }

    public int getMessageCount() {
	return messageCount;
    }

    public int getMessageLength() {
	return messageLength;
    }

    public int getCreditsAvailable() {
	return creditsAvailable;
    }

    public int getCreditRequired() {
	return creditRequired;
    }

    public int getCreditRemaining() {
	return creditRemaining;
    }

    public void setMessageReceived(String messageReceived) {
	this.messageReceived = messageReceived;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public void setTestMode(boolean testMode) {
	this.testMode = testMode;
    }

    public void setMessageCount(int messageCount) {
	this.messageCount = messageCount;
    }

    public void setMessageLength(int messageLength) {
	this.messageLength = messageLength;
    }

    public void setCreditsAvailable(int creditsAvailable) {
	this.creditsAvailable = creditsAvailable;
    }

    public void setCreditRequired(int creditRequired) {
	this.creditRequired = creditRequired;
    }

    public void setCreditRemaining(int creditRemaining) {
	this.creditRemaining = creditRemaining;
    }

}
