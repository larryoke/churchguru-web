package com.laotek.churchguru.web.server.handler.estore;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.listening.ListeningDao;
import com.laotek.churchguru.web.client.activity.listening.CreateNewMessageAction;
import com.laotek.churchguru.web.client.activity.listening.CreateNewMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class CreateNewMessageHandler extends AbstractCommandHandler implements
	ActionHandler<CreateNewMessageAction, CreateNewMessageResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public CreateNewMessageResult execute(CreateNewMessageAction action,
	    ExecutionContext context) throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewMessageResult(identifier);
    }

    @Override
    public Class<CreateNewMessageAction> getActionType() {
	return CreateNewMessageAction.class;
    }

    @Override
    public void rollback(CreateNewMessageAction action,
	    CreateNewMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
