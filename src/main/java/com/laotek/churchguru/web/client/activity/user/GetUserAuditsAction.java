package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;

public class GetUserAuditsAction extends AbstractDispatchAction implements Action<GetUserAuditsResult>{
	private String userId;
	private int beginIndex;
	private int endIndex;
	
	public GetUserAuditsAction() {
	}

	public GetUserAuditsAction(String userId, int beginIndex, int endIndex) {
		this.userId = userId;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	
}
