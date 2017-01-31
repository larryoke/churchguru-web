package com.laotek.churchguru.daos.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.AudioMessageCategory;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioMessageSpeaker;

public interface AudioMessageDao {
    void createNewMessage(String messageId, String title);

    AudioMessage getMessageByIdentifier(String identifier);

    List<AudioMessage> getMessages();

    List<AudioMessageSpeaker> getSpeakers();

    List<AudioMessageCategory> getCategories();

    List<AudioMessagePicture> getEStoreMessagePicture();

    void updateMessage(AudioMessage eStoreMessage, Map<String, String> otherDetails);
}
