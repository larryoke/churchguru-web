package com.laotek.churchguru.web.server.handler.website.estore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.website.listening.cat.GetListeningCategoriesAction;
import com.laotek.churchguru.web.client.activity.website.listening.cat.GetListeningCategoriesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningCategoriesHandler extends AbstractCommandHandler
	implements ActionHandler<GetListeningCategoriesAction, GetListeningCategoriesResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetListeningCategoriesResult execute(GetListeningCategoriesAction action, ExecutionContext context)
	    throws DispatchException {
	List<ListeningCategoryDto> categoryDtos = null;// mapCategoryDto(categories);
	return new GetListeningCategoriesResult(categoryDtos);
    }

    @Override
    public Class<GetListeningCategoriesAction> getActionType() {
	return GetListeningCategoriesAction.class;
    }

    @Override
    public void rollback(GetListeningCategoriesAction action, GetListeningCategoriesResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
