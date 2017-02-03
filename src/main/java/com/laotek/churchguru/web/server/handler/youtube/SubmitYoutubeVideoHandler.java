package com.laotek.churchguru.web.server.handler.youtube;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.YoutubeVideoDao;
import com.laotek.churchguru.model.YoutubeVideo;
import com.laotek.churchguru.web.client.activity.media.youtube.SubmitYoutubeVideoAction;
import com.laotek.churchguru.web.client.activity.media.youtube.SubmitYoutubeVideoResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class SubmitYoutubeVideoHandler extends AbstractCommandHandler
	implements ActionHandler<SubmitYoutubeVideoAction, SubmitYoutubeVideoResult> {

    @Autowired
    private YoutubeVideoDao eStoreDao;

    @Override
    public SubmitYoutubeVideoResult execute(SubmitYoutubeVideoAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	YoutubeVideo message = eStoreDao.getMessageByIdentifier(identifier);
	message.setSpeakers(action.getSpeaker());
	message.setLocation(action.getLocation());
	message.setMessageDate(action.getMessageDate());
	message.setYoutubeUrl(action.getYoutubeUrl());

	if (action.getYoutubeUrl() != null && !action.getYoutubeUrl().trim().equals("")) {

	}

	message.setLastUpdatedDate(new Date());

	eStoreDao.updateMessage(message);

	return new SubmitYoutubeVideoResult(map(message));
    }

    private YoutubeVideoDto map(YoutubeVideo message) {
	YoutubeVideoDto dto = new YoutubeVideoDto();
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitYoutubeVideoAction> getActionType() {
	return SubmitYoutubeVideoAction.class;
    }

    @Override
    public void rollback(SubmitYoutubeVideoAction action, SubmitYoutubeVideoResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
