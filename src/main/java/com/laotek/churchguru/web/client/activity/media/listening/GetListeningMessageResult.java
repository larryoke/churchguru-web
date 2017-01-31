package com.laotek.churchguru.web.client.activity.listening;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public class GetListeningMessageResult implements Result {

    private ListeningMessageDto message;
    private List<ListeningSpeakerDto> speakers;
    private List<ListeningCategoryDto> categories;
    private List<ListeningMessagePictureDto> pictures;
    private Map<String, Boolean> workersSelectedForFreeMessages;

    public GetListeningMessageResult() {
    }

    public GetListeningMessageResult(ListeningMessageDto message,
                                     List<ListeningSpeakerDto> speakers,
                                     List<ListeningCategoryDto> categories,
                                     List<ListeningMessagePictureDto> pictures,
                                     Map<String, Boolean> workersSelectedForFreeMessages) {
	this.message = message;
	this.speakers = speakers;
	this.categories = categories;
	this.pictures = pictures;
	this.setWorkersSelectedForFreeMessages(workersSelectedForFreeMessages);
    }

    public ListeningMessageDto getMessage() {
	return message;
    }

    public List<ListeningSpeakerDto> getSpeakers() {
	return speakers;
    }

    public List<ListeningCategoryDto> getCategories() {
	return categories;
    }

    public void setCategories(List<ListeningCategoryDto> categories) {
	this.categories = categories;
    }

    public List<ListeningMessagePictureDto> getPictures() {
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
