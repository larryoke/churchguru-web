package com.laotek.churchguru.commons;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class SmsMessageSendResult {

    private String errorMessage;

    private List<SmsMessageSentResultItem> recipients = new ArrayList<SmsMessageSentResultItem>();

    public SmsMessageSendResult() {
    }

    public SmsMessageSendResult(List<SmsMessageSentResultItem> recipients) {
	super();
	this.recipients = recipients;
    }

    public static SmsMessageSendResult createSmsMessageSendResult(
	    String responseData) {

	if (responseData.startsWith("Error")) {
	    SmsMessageSendResult result = new SmsMessageSendResult();
	    result.setErrorMessage(responseData);
	    return result;
	}
	return new SmsMessageSendResult(
		SmsMessageSentResultItem
			.createSmsMessageSentResultItems(responseData));
    }

    public List<SmsMessageSentResultItem> getRecipients() {
	return recipients;
    }

    public void setRecipients(List<SmsMessageSentResultItem> recipients) {
	this.recipients = recipients;
    }

    public int getCreditRequired() {
	int creditRequried = 0;
	for (SmsMessageSentResultItem item : recipients) {
	    creditRequried = item.getCreditRequired() + creditRequried;
	}
	return creditRequried;
    }

    public int getCreditRemaining() {
	int size = recipients.size();
	if (size > 0) {
	    return recipients.get(size - 1).getCreditRemaining();
	}
	return 0;
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
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

}
