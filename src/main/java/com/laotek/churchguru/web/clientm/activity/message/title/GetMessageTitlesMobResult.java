package com.laotek.churchguru.web.clientm.activity.message.title;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class GetMessageTitlesMobResult implements Result {

    private String categoryName;

    private List<MessageTitleMobDto> titleDtos;

    public GetMessageTitlesMobResult() {
	super();
    }

    public GetMessageTitlesMobResult(String categoryName, List<MessageTitleMobDto> titleDtos) {
	this.categoryName = categoryName;
	this.titleDtos = titleDtos;
    }

    public List<MessageTitleMobDto> getTitleDtos() {
	return titleDtos;
    }

    public void setTitleDtos(List<MessageTitleMobDto> titleDtos) {
	this.titleDtos = titleDtos;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }
}
