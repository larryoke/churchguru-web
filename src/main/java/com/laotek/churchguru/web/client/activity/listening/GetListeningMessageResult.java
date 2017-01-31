package com.laotek.churchguru.web.client.activity.listening;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public class GetListeningMessageResult implements Result {

    private AudioMessageMessageDto message;
    private List<AudioMessageSpeakerDto> speakers;
    private List<AudioMessageCategoryDto> categories;
    private List<AudioMessageMessagePictureDto> pictures;
    private Map<String, Boolean> workersSelectedForFreeMessages;

    public GetListeningMessageResult() {
    }

    public GetListeningMessageResult(AudioMessageMessageDto message,
                                     List<AudioMessageSpeakerDto> speakers,
                                     List<AudioMessageCategoryDto> categories,
                                     List<AudioMessageMessagePictureDto> pictures,
                                     Map<String, Boolean> workersSelectedForFreeMessages) {
	this.message = message;
	this.speakers = speakers;
	this.categories = categories;
	this.pictures = pictures;
	this.setWorkersSelectedForFreeMessages(workersSelectedForFreeMessages);
    }

    public AudioMessageMessageDto getMessage() {
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

    public List<AudioMessageMessagePictureDto> getPictures() {
	return pictures;
    }

    public Map<String, Boolean> getWorkersSelectedForFreeMessages() {
	return workersSelectedForFreeMessages;
    }

    public void setWorkersSelectedForFreeMessages(
	    Map<String, Boolean> workersSelectedForFreeMessages) {
	this.workersSelectedForFreeMessages = workersSelectedForFreeMessages;
    }
}
