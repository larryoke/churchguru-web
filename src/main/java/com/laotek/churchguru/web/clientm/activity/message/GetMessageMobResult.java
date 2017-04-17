package com.laotek.churchguru.web.clientm.activity.message;

import net.customware.gwt.dispatch.shared.Result;

public class GetMessageMobResult implements Result {
    private MessageMobDto dto;

    public MessageMobDto getDto() {
	return dto;
    }

    public void setDto(MessageMobDto dto) {
	this.dto = dto;
    }
}
