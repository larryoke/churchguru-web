package com.laotek.churchguru.web.client.activity.donation;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class DonationSearchResult implements Result {
    private List<DonationDto> dtos;

    public DonationSearchResult() {
    }

    public DonationSearchResult(List<DonationDto> dtos) {
	this.dtos = dtos;
    }

    public List<DonationDto> getDtos() {
	return dtos;
    }

}
