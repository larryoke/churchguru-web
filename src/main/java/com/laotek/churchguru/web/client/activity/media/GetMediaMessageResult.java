package com.laotek.churchguru.web.client.activity.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetMediaMessageResult implements Result {

    private MediaMessageDto message;
    private List<MediaMessageSpeakerDto> speakers;
    private List<MediaMessageCategoryDto> categories;
    private List<MediaMessagePictureDto> pictures;
    private Map<String, Boolean> workersSelectedForFreeMessages;

    public GetMediaMessageResult() {
    }

    public GetMediaMessageResult(MediaMessageDto message, List<MediaMessageSpeakerDto> speakers,
	    List<MediaMessageCategoryDto> categories, List<MediaMessagePictureDto> pictures) {
	this.message = message;
	this.speakers = speakers;
	this.categories = categories;
	this.pictures = pictures;
	this.setWorkersSelectedForFreeMessages(workersSelectedForFreeMessages);
    }

    public MediaMessageDto getMessage() {
	return message;
    }

    public List<MediaMessageSpeakerDto> getSpeakers() {
	return speakers;
    }

    public List<MediaMessageCategoryDto> getCategories() {
	return categories;
    }

    public void setCategories(List<MediaMessageCategoryDto> categories) {
	this.categories = categories;
    }

    public List<MediaMessagePictureDto> getPictures() {
	return pictures;
    }

    public Map<String, Boolean> getWorkersSelectedForFreeMessages() {
	return workersSelectedForFreeMessages;
    }

    public void setWorkersSelectedForFreeMessages(Map<String, Boolean> workersSelectedForFreeMessages) {
	this.workersSelectedForFreeMessages = workersSelectedForFreeMessages;
    }
}
