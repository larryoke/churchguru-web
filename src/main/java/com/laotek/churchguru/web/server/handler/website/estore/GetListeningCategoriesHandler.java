package com.laotek.churchguru.web.server.handler.website.estore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.website.audio.cat.GetAudioMessageCategoriesAction;
import com.laotek.churchguru.web.client.activity.website.audio.cat.GetAudioMessageCategoriesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningCategoriesHandler extends AbstractCommandHandler
	implements ActionHandler<GetAudioMessageCategoriesAction, GetAudioMessageCategoriesResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetAudioMessageCategoriesResult execute(GetAudioMessageCategoriesAction action, ExecutionContext context)
	    throws DispatchException {
	List<AudioMessageCategoryDto> categoryDtos = null;// mapCategoryDto(categories);
	return new GetAudioMessageCategoriesResult(categoryDtos);
    }

    @Override
    public Class<GetAudioMessageCategoriesAction> getActionType() {
	return GetAudioMessageCategoriesAction.class;
    }

    @Override
    public void rollback(GetAudioMessageCategoriesAction action, GetAudioMessageCategoriesResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
