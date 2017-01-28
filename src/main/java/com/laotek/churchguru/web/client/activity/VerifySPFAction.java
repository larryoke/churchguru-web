package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;

public class VerifySPFAction extends AbstractDispatchAction implements Action<VerifySPFResult>{
	
	private String hostname;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
}
