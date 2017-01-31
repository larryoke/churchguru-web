package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import net.customware.gwt.dispatch.shared.Result;

public class GetSingleNoticeOrEventResult implements Result {

    private NoticeOrEventDto dto;

    public GetSingleNoticeOrEventResult() {
    }

    public GetSingleNoticeOrEventResult(NoticeOrEventDto dto) {
	this.setDto(dto);
    }

    public NoticeOrEventDto getDto() {
	return dto;
    }

    public void setDto(NoticeOrEventDto dto) {
	this.dto = dto;
    }
}
