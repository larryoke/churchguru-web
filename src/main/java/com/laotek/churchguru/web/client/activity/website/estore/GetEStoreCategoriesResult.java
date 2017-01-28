package com.laotek.churchguru.web.client.activity.website.estore;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;

public class GetEStoreCategoriesResult implements Result {
    private List<EStoreCategoryDto> categoryDtos;

    public GetEStoreCategoriesResult(List<EStoreCategoryDto> categoryDtos) {
	super();
	this.categoryDtos = categoryDtos;
    }

    public List<EStoreCategoryDto> getCategoryDtos() {
	return categoryDtos;
    }

    public void setCategoryDtos(List<EStoreCategoryDto> categoryDtos) {
	this.categoryDtos = categoryDtos;
    }
}
