package com.laotek.churchguru.web.server.handler.youtube;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.YoutubeVideoDao;
import com.laotek.churchguru.web.client.activity.media.youtube.CreateNewYoutubeVideoAction;
import com.laotek.churchguru.web.client.activity.media.youtube.CreateNewYoutubeVideoResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class CreateNewYoutubeVideoHandler extends AbstractCommandHandler
	implements ActionHandler<CreateNewYoutubeVideoAction, CreateNewYoutubeVideoResult> {

    @Autowired
    private YoutubeVideoDao eStoreDao;

    @Override
    public CreateNewYoutubeVideoResult execute(CreateNewYoutubeVideoAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewYoutubeVideoResult(identifier);
    }

    @Override
    public Class<CreateNewYoutubeVideoAction> getActionType() {
	return CreateNewYoutubeVideoAction.class;
    }

    @Override
    public void rollback(CreateNewYoutubeVideoAction action, CreateNewYoutubeVideoResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
