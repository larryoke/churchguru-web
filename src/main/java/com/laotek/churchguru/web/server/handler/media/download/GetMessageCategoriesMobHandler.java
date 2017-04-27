package com.laotek.churchguru.web.server.handler.media.download;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.web.clientm.activity.message.category.GetMessageCategoriesMobAction;
import com.laotek.churchguru.web.clientm.activity.message.category.GetMessageCategoriesMobResult;
import com.laotek.churchguru.web.clientm.activity.message.category.MessageCategoryMobDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMessageCategoriesMobHandler extends AbstractCommandHandler
	implements ActionHandler<GetMessageCategoriesMobAction, GetMessageCategoriesMobResult> {

    @Autowired
    private MediaMessageDao mediaMessageDao;

    @Override
    public GetMessageCategoriesMobResult execute(GetMessageCategoriesMobAction action, ExecutionContext context)
	    throws DispatchException {
	List<MediaMessageCategory> categories = mediaMessageDao.getPublishedCategories();
	List<MessageCategoryMobDto> categoryDtos = new ArrayList<MessageCategoryMobDto>();

	for (MediaMessageCategory cat : categories) {
	    MessageCategoryMobDto dto = new MessageCategoryMobDto();
	    dto.setIdentifier(cat.getIdentifier());
	    dto.setName(cat.getCategoryName());
	    dto.setCount(cat.getMessages().size());
	    categoryDtos.add(dto);
	}
	return new GetMessageCategoriesMobResult(categoryDtos);
    }

    @Override
    public Class<GetMessageCategoriesMobAction> getActionType() {
	return GetMessageCategoriesMobAction.class;
    }

    @Override
    public void rollback(GetMessageCategoriesMobAction action, GetMessageCategoriesMobResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
