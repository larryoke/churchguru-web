package com.laotek.churchguru.web.server.handler.website.estore;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.website.estore.GetEStoreCategoriesAction;
import com.laotek.churchguru.web.client.activity.website.estore.GetEStoreCategoriesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;

@Component
public class GetEStoreCategoriesHandler extends AbstractCommandHandler
	implements ActionHandler<GetEStoreCategoriesAction, GetEStoreCategoriesResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetEStoreCategoriesResult execute(GetEStoreCategoriesAction action, ExecutionContext context)
	    throws DispatchException {
	List<EStoreCategoryDto> categoryDtos = null;// mapCategoryDto(categories);
	return new GetEStoreCategoriesResult(categoryDtos);
    }

    @Override
    public Class<GetEStoreCategoriesAction> getActionType() {
	return GetEStoreCategoriesAction.class;
    }

    @Override
    public void rollback(GetEStoreCategoriesAction action, GetEStoreCategoriesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
