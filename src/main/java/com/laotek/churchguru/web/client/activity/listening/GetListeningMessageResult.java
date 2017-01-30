package com.laotek.churchguru.web.client.activity.listening;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessagePictureDto;
import com.laotek.churchguru.web.shared.estore.EStoreSpeakerDto;

public class GetEStoreMessageResult implements Result {

    private EStoreMessageDto message;
    private List<EStoreSpeakerDto> speakers;
    private List<EStoreCategoryDto> categories;
    private List<EStoreMessagePictureDto> pictures;
    private Map<String, Boolean> workersSelectedForFreeMessages;

    public GetEStoreMessageResult() {
    }

    public GetEStoreMessageResult(EStoreMessageDto message,
	    List<EStoreSpeakerDto> speakers,
	    List<EStoreCategoryDto> categories,
	    List<EStoreMessagePictureDto> pictures,
	    Map<String, Boolean> workersSelectedForFreeMessages) {
	this.message = message;
	this.speakers = speakers;
	this.categories = categories;
	this.pictures = pictures;
	this.setWorkersSelectedForFreeMessages(workersSelectedForFreeMessages);
    }

    public EStoreMessageDto getMessage() {
	return message;
    }

    public List<EStoreSpeakerDto> getSpeakers() {
	return speakers;
    }

    public List<EStoreCategoryDto> getCategories() {
	return categories;
    }

    public void setCategories(List<EStoreCategoryDto> categories) {
	this.categories = categories;
    }

    public List<EStoreMessagePictureDto> getPictures() {
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
