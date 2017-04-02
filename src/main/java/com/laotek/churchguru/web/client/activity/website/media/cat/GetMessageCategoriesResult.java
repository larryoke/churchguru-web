package com.laotek.churchguru.web.client.activity.website.media.cat;

import java.util.List;

import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetMessageCategoriesResult implements Result {
    private List<MediaMessageCategoryDto> categoryDtos;

    public GetMessageCategoriesResult(List<MediaMessageCategoryDto> categoryDtos) {
	super();
	this.categoryDtos = categoryDtos;
    }

    public List<MediaMessageCategoryDto> getCategoryDtos() {
	return categoryDtos;
    }

    public void setCategoryDtos(List<MediaMessageCategoryDto> categoryDtos) {
	this.categoryDtos = categoryDtos;
    }
}
