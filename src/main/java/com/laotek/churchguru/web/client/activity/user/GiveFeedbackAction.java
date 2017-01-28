package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.FeedbackMessageType;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;

public class GiveFeedbackAction extends AbstractDispatchAction implements Action<GiveFeedbackResult>{
	private FeedbackMessageType type;
	private String body;
	private boolean sendCopyToSender;
	
	public boolean isSendCopyToSender() {
		return sendCopyToSender;
	}
	public void setSendCopyToSender(boolean sendCopyToSender) {
		this.sendCopyToSender = sendCopyToSender;
	}
	
	public FeedbackMessageType getType() {
		return type;
	}
	public void setType(FeedbackMessageType type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
