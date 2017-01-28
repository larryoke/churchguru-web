package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.OrganisationDto;

public class GetOrgDetailResult implements Result {
    private OrganisationDto dto;

    public GetOrgDetailResult(OrganisationDto dto) {
	this.dto = dto;
    }

    public GetOrgDetailResult() {
    }

    public OrganisationDto getDto() {
	return dto;
    }

}
