package com.laotek.churchguru.web.server.handler.media.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.web.client.activity.website.media.play.GetMediaAction;
import com.laotek.churchguru.web.client.activity.website.media.play.GetMediaResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMediaHandler extends AbstractCommandHandler implements ActionHandler<GetMediaAction, GetMediaResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public GetMediaResult execute(GetMediaAction action, ExecutionContext context) throws DispatchException {

	String identifier = action.getIdentifier();

	MediaMessage message = eStoreDao.getMessageByIdentifier(identifier);

	return new GetMediaResult(message.getMediaMessageUrl(), message.getTitle());
    }

    @Override
    public Class<GetMediaAction> getActionType() {
	return GetMediaAction.class;
    }

    @Override
    public void rollback(GetMediaAction action, GetMediaResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
