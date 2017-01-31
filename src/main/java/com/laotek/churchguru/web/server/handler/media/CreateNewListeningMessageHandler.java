package com.laotek.churchguru.web.server.handler.media;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.ListeningDao;
import com.laotek.churchguru.web.client.activity.listening.CreateNewListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.CreateNewListeningMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class CreateNewListeningMessageHandler extends AbstractCommandHandler
	implements ActionHandler<CreateNewListeningMessageAction, CreateNewListeningMessageResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public CreateNewListeningMessageResult execute(CreateNewListeningMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewListeningMessageResult(identifier);
    }

    @Override
    public Class<CreateNewListeningMessageAction> getActionType() {
	return CreateNewListeningMessageAction.class;
    }

    @Override
    public void rollback(CreateNewListeningMessageAction action, CreateNewListeningMessageResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
