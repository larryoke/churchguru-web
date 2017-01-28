package com.laotek.churchguru.web.client.activity.api;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.SmsCreditHistoryDto;

public class GetSmsCreditHistoryResult implements Result {

    private List<SmsCreditHistoryDto> smsCreditHistoryDtos;

    public List<SmsCreditHistoryDto> getSmsCreditHistoryDto() {
	return smsCreditHistoryDtos;
    }

    public void setSmsCreditHistoryDto(
	    List<SmsCreditHistoryDto> smsCreditHistoryDtos) {
	this.smsCreditHistoryDtos = smsCreditHistoryDtos;
    }

    public GetSmsCreditHistoryResult() {
    }

}
