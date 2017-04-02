package com.laotek.churchguru.web.server.handler.media.download;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.web.client.activity.media.CreateNewMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.CreateNewMediaMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class CreateNewMediaMessageHandler extends AbstractCommandHandler
	implements ActionHandler<CreateNewMediaMessageAction, CreateNewMediaMessageResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public CreateNewMediaMessageResult execute(CreateNewMediaMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewMediaMessageResult(identifier);
    }

    @Override
    public Class<CreateNewMediaMessageAction> getActionType() {
	return CreateNewMediaMessageAction.class;
    }

    @Override
    public void rollback(CreateNewMediaMessageAction action, CreateNewMediaMessageResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
