package com.laotek.churchguru.web.clientm.dispatch;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class AbstractDispatchAction implements IsSerializable{

	private String clientSessionId;

	public void setClientSessionId(String currentSessionId) {
		this.clientSessionId = currentSessionId;
	}

	public String getClientSessionId() {
		return clientSessionId;
	}
}
