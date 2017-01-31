package com.laotek.churchguru.web.client.activity.audio;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetAudioMessageResult implements Result {

    private AudioMessageDto message;
    private List<AudioMessageSpeakerDto> speakers;
    private List<AudioMessageCategoryDto> categories;
    private List<AudioMessagePictureDto> pictures;
    private Map<String, Boolean> workersSelectedForFreeMessages;

    public GetAudioMessageResult() {
    }

    public GetAudioMessageResult(AudioMessageDto message, List<AudioMessageSpeakerDto> speakers,
	    List<AudioMessageCategoryDto> categories, List<AudioMessagePictureDto> pictures) {
	this.message = message;
	this.speakers = speakers;
	this.categories = categories;
	this.pictures = pictures;
	this.setWorkersSelectedForFreeMessages(workersSelectedForFreeMessages);
    }

    public AudioMessageDto getMessage() {
	return message;
    }

    public List<AudioMessageSpeakerDto> getSpeakers() {
	return speakers;
    }

    public List<AudioMessageCategoryDto> getCategories() {
	return categories;
    }

    public void setCategories(List<AudioMessageCategoryDto> categories) {
	this.categories = categories;
    }

    public List<AudioMessagePictureDto> getPictures() {
	return pictures;
    }

    public Map<String, Boolean> getWorkersSelectedForFreeMessages() {
	return workersSelectedForFreeMessages;
    }

    public void setWorkersSelectedForFreeMessages(Map<String, Boolean> workersSelectedForFreeMessages) {
	this.workersSelectedForFreeMessages = workersSelectedForFreeMessages;
    }
}
