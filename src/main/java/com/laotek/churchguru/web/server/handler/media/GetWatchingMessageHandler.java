package com.laotek.churchguru.web.server.handler.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.model.VideoMessage;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessageAction;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetWatchingMessageHandler extends AbstractCommandHandler
	implements ActionHandler<GetWatchingMessageAction, GetWatchingMessageResult> {

    @Autowired
    private WatchingDao eStoreDao;

    @Override
    public GetWatchingMessageResult execute(GetWatchingMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	VideoMessage message = eStoreDao.getMessageByIdentifier(identifier);

	return new GetWatchingMessageResult(mapWatchingMessages(message));
    }

    @Override
    public Class<GetWatchingMessageAction> getActionType() {
	return GetWatchingMessageAction.class;
    }

    @Override
    public void rollback(GetWatchingMessageAction action, GetWatchingMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
