package com.laotek.churchguru.daos.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.AudioCategory;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioSpeaker;

public interface ListeningDao {
    void createNewMessage(String messageId, String title);

    AudioMessage getMessageByIdentifier(String identifier);

    List<AudioMessage> getMessages();

    List<AudioSpeaker> getSpeakers();

    List<AudioCategory> getCategories();

    List<AudioMessagePicture> getEStoreMessagePicture();

    void updateMessage(AudioMessage eStoreMessage, Map<String, String> otherDetails);

    void loadNotifications();

    Map<String, Boolean> getWorkersSelectedForFreeMessages();
}
