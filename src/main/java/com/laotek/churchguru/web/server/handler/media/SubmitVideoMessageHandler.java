package com.laotek.churchguru.web.server.handler.media;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.model.VideoMessage;
import com.laotek.churchguru.web.client.activity.media.watching.SubmitVideoMessageAction;
import com.laotek.churchguru.web.client.activity.media.watching.SubmitVideoMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.watching.VideoMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class SubmitVideoMessageHandler extends AbstractCommandHandler
	implements ActionHandler<SubmitVideoMessageAction, SubmitVideoMessageResult> {

    @Autowired
    private WatchingDao eStoreDao;

    @Override
    public SubmitVideoMessageResult execute(SubmitVideoMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	VideoMessage message = eStoreDao.getMessageByIdentifier(identifier);
	message.setSpeakers(action.getSpeaker());
	message.setLocation(action.getLocation());
	message.setMessageDate(action.getMessageDate());
	message.setYoutubeUrl(action.getYoutubeUrl());

	if (action.getYoutubeUrl() != null && !action.getYoutubeUrl().trim().equals("")) {

	}

	message.setLastUpdatedDate(new Date());

	eStoreDao.updateMessage(message);

	return new SubmitVideoMessageResult(map(message));
    }

    private VideoMessageDto map(VideoMessage message) {
	VideoMessageDto dto = new VideoMessageDto();
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitVideoMessageAction> getActionType() {
	return SubmitVideoMessageAction.class;
    }

    @Override
    public void rollback(SubmitVideoMessageAction action, SubmitVideoMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
