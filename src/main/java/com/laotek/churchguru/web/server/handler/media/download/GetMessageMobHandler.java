package com.laotek.churchguru.web.server.handler.media.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessageSpeaker;
import com.laotek.churchguru.web.clientm.activity.message.GetMessageMobAction;
import com.laotek.churchguru.web.clientm.activity.message.GetMessageMobResult;
import com.laotek.churchguru.web.clientm.activity.message.MessageMobDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMessageMobHandler extends AbstractCommandHandler
	implements ActionHandler<GetMessageMobAction, GetMessageMobResult> {

    @Autowired
    private MediaMessageDao mediaMessageDao;

    @Override
    public GetMessageMobResult execute(GetMessageMobAction action, ExecutionContext context) throws DispatchException {

	MediaMessage message = mediaMessageDao.getMessageByIdentifier(action.getIdentifier());
	MediaMessageSpeaker speaker = message.geteStoreSpeaker();
	String speakerFullname = speaker.getTitle().getDesc() + " " + speaker.getForenames() + " "
		+ speaker.getSurname();
	MediaMessageCategory category = message.geteStoreCategory();
	GetMessageMobResult result = new GetMessageMobResult();
	MessageMobDto dto = new MessageMobDto();
	dto.setCategoryName(category.getCategoryName());
	dto.setDesc(message.getDescription());
	dto.setDescUrl(message.getDescPictureUrl());
	dto.setEventDateAsStr(dto.getEventDateAsStr());
	dto.setMediaUrl(message.getMediaMessageUrl());
	dto.setSpeakerDesc(speaker.getDescription());
	dto.setSpeakerFullname(speakerFullname);
	dto.setSpeakerPictureUrl(speaker.getPictureUrl());
	dto.setTitle(message.getTitle());
	result.setDto(dto);
	return result;
    }

    @Override
    public Class<GetMessageMobAction> getActionType() {
	return GetMessageMobAction.class;
    }

    @Override
    public void rollback(GetMessageMobAction action, GetMessageMobResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
