package com.laotek.churchguru.web.clientm.activity.message.category;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class GetMessageCategoriesMobResult implements Result {
    public GetMessageCategoriesMobResult() {
	super();
    }

    private List<MessageCategoryMobDto> categoryDtos;

    public GetMessageCategoriesMobResult(List<MessageCategoryMobDto> categoryDtos) {
	super();
	this.categoryDtos = categoryDtos;
    }

    public List<MessageCategoryMobDto> getCategoryDtos() {
	return categoryDtos;
    }

    public void setCategoryDtos(List<MessageCategoryMobDto> categoryDtos) {
	this.categoryDtos = categoryDtos;
    }
}
