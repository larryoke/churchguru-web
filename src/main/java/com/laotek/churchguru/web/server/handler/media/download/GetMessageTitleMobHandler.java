package com.laotek.churchguru.web.server.handler.media.download;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.web.clientm.activity.message.title.GetMessageTitlesMobAction;
import com.laotek.churchguru.web.clientm.activity.message.title.GetMessageTitlesMobResult;
import com.laotek.churchguru.web.clientm.activity.message.title.MessageTitleMobDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMessageTitleMobHandler extends AbstractCommandHandler
	implements ActionHandler<GetMessageTitlesMobAction, GetMessageTitlesMobResult> {

    @Autowired
    private MediaMessageDao mediaMessageDao;

    @Override
    public GetMessageTitlesMobResult execute(GetMessageTitlesMobAction action, ExecutionContext context)
	    throws DispatchException {
	MediaMessageCategory category = mediaMessageDao.getCategory(action.getIdentifier());
	List<MessageTitleMobDto> dtos = new ArrayList<MessageTitleMobDto>();

	for (MediaMessage message : category.getMessages()) {
	    MessageTitleMobDto dto = new MessageTitleMobDto();
	    dto.setIdentifier(message.getIdentifier());
	    dto.setName(message.getTitle());
	    String mediaUrl = message.getMediaMessageUrl();
	    if (mediaUrl != null && mediaUrl.contains("mp3")) {
		dto.setMediaType("Audio");

	    } else if (mediaUrl != null && mediaUrl.contains("mp4")) {
		dto.setMediaType("Video");
	    }

	    String descUrl = message.getDescPictureUrl();
	    if (descUrl != null) {
		dto.setDescPicUrl(descUrl);
	    } else {
		dto.setDescPicUrl("/uploadedphotos/photos/org/logo");
	    }

	    dto.setDescPicUrl(descUrl);
	    dtos.add(dto);
	}
	return new GetMessageTitlesMobResult(category.getCategoryName(), dtos);
    }

    @Override
    public Class<GetMessageTitlesMobAction> getActionType() {
	return GetMessageTitlesMobAction.class;
    }

    @Override
    public void rollback(GetMessageTitlesMobAction action, GetMessageTitlesMobResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
