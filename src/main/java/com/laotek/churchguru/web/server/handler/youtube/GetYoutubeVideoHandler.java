package com.laotek.churchguru.web.server.handler.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.YoutubeVideoDao;
import com.laotek.churchguru.model.YoutubeVideo;
import com.laotek.churchguru.web.client.activity.media.youtube.GetYoutubeVideoAction;
import com.laotek.churchguru.web.client.activity.media.youtube.GetYoutubeVideoResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetYoutubeVideoHandler extends AbstractCommandHandler
	implements ActionHandler<GetYoutubeVideoAction, GetYoutubeVideoResult> {

    @Autowired
    private YoutubeVideoDao eStoreDao;

    @Override
    public GetYoutubeVideoResult execute(GetYoutubeVideoAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	YoutubeVideo message = eStoreDao.getMessageByIdentifier(identifier);

	return new GetYoutubeVideoResult(mapYoutubeVideos(message));
    }

    @Override
    public Class<GetYoutubeVideoAction> getActionType() {
	return GetYoutubeVideoAction.class;
    }

    @Override
    public void rollback(GetYoutubeVideoAction action, GetYoutubeVideoResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
