package com.laotek.churchguru.web.client.activity.website.listening.cat;

import java.util.List;

import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetAudioMessageCategoriesResult implements Result {
    private List<AudioMessageCategoryDto> categoryDtos;

    public GetAudioMessageCategoriesResult(List<AudioMessageCategoryDto> categoryDtos) {
	super();
	this.categoryDtos = categoryDtos;
    }

    public List<AudioMessageCategoryDto> getCategoryDtos() {
	return categoryDtos;
    }

    public void setCategoryDtos(List<AudioMessageCategoryDto> categoryDtos) {
	this.categoryDtos = categoryDtos;
    }
}
