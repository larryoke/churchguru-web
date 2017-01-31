package com.laotek.churchguru.web.server.handler.media;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.web.client.activity.media.watching.CreateNewWatchingMessageAction;
import com.laotek.churchguru.web.client.activity.media.watching.CreateNewWatchingMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class WatchingCreateNewMessageHandler extends AbstractCommandHandler
	implements ActionHandler<CreateNewWatchingMessageAction, CreateNewWatchingMessageResult> {

    @Autowired
    private WatchingDao eStoreDao;

    @Override
    public CreateNewWatchingMessageResult execute(CreateNewWatchingMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewWatchingMessageResult(identifier);
    }

    @Override
    public Class<CreateNewWatchingMessageAction> getActionType() {
	return CreateNewWatchingMessageAction.class;
    }

    @Override
    public void rollback(CreateNewWatchingMessageAction action, CreateNewWatchingMessageResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
