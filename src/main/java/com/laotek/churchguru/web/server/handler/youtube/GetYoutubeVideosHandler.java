package com.laotek.churchguru.web.server.handler.youtube;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.YoutubeVideoDao;
import com.laotek.churchguru.model.YoutubeVideo;
import com.laotek.churchguru.web.client.activity.media.youtube.GetYoutubeVideosAction;
import com.laotek.churchguru.web.client.activity.media.youtube.GetYoutubeVideosResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetYoutubeVideosHandler extends AbstractCommandHandler
	implements ActionHandler<GetYoutubeVideosAction, GetYoutubeVideosResult> {

    @Autowired
    private YoutubeVideoDao eStoreDao;

    @Override
    public GetYoutubeVideosResult execute(GetYoutubeVideosAction action, ExecutionContext context)
	    throws DispatchException {

	List<YoutubeVideo> messages = eStoreDao.getMessages();
	List<YoutubeVideoDto> dtos = new ArrayList<YoutubeVideoDto>();
	messages.stream().forEach(message -> dtos.add(mapYoutubeVideos(message)));
	return new GetYoutubeVideosResult(dtos);
    }

    @Override
    public Class<GetYoutubeVideosAction> getActionType() {
	return GetYoutubeVideosAction.class;
    }

    @Override
    public void rollback(GetYoutubeVideosAction action, GetYoutubeVideosResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
