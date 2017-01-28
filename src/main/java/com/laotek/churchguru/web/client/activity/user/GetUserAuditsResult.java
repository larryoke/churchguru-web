package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.UserAuditDto;

public class GetUserAuditsResult implements Result{
	private List<UserAuditDto> dtos;
	
	

	public GetUserAuditsResult() {
	}

	public GetUserAuditsResult(List<UserAuditDto> dtos) {
		this.dtos = dtos;
	}

	public List<UserAuditDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<UserAuditDto> dtos) {
		this.dtos = dtos;
	}
	
}
