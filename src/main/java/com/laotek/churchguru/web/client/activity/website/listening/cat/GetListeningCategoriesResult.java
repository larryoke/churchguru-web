package com.laotek.churchguru.web.client.activity.website.listening.cat;

import java.util.List;

import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetListeningCategoriesResult implements Result {
    private List<ListeningCategoryDto> categoryDtos;

    public GetListeningCategoriesResult(List<ListeningCategoryDto> categoryDtos) {
	super();
	this.categoryDtos = categoryDtos;
    }

    public List<ListeningCategoryDto> getCategoryDtos() {
	return categoryDtos;
    }

    public void setCategoryDtos(List<ListeningCategoryDto> categoryDtos) {
	this.categoryDtos = categoryDtos;
    }
}
