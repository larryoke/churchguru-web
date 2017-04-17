package com.laotek.churchguru.web.server.handler.media.download;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.website.media.cat.GetMediaMessageCategoriesAction;
import com.laotek.churchguru.web.client.activity.website.media.cat.GetMessageCategoriesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMessageCategoriesHandler extends AbstractCommandHandler
	implements ActionHandler<GetMediaMessageCategoriesAction, GetMessageCategoriesResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetMessageCategoriesResult execute(GetMediaMessageCategoriesAction action, ExecutionContext context)
	    throws DispatchException {
	List<MediaMessageCategoryDto> categoryDtos = null;// mapCategoryDto(categories);
	return new GetMessageCategoriesResult(categoryDtos);
    }

    @Override
    public Class<GetMediaMessageCategoriesAction> getActionType() {
	return GetMediaMessageCategoriesAction.class;
    }

    @Override
    public void rollback(GetMediaMessageCategoriesAction action, GetMessageCategoriesResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
