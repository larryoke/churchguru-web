package com.laotek.churchguru.daos.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessageSpeaker;

public interface MediaMessageDao {
    void createNewMessage(String messageId, String title);

    MediaMessage getMessageByIdentifier(String identifier);

    List<MediaMessage> getMessages();

    List<MediaMessageSpeaker> getSpeakers();

    List<MediaMessageCategory> getCategories();

    MediaMessageCategory getCategory(String identifier);

    void updateMessage(MediaMessage eStoreMessage, Map<String, String> otherDetails);

    void updateSpeakerPictureURL(String identifier, String pictureURL);

    void updateDescPictureURL(String identifier, String pictureURL);

    void updateMediaMessageURL(String identifier, String mediaURL);
}
