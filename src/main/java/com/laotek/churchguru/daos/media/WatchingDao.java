package com.laotek.churchguru.daos.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.VideoMessage;

public interface WatchingDao {
    void createNewMessage(String messageId, String title);

    VideoMessage getMessageByIdentifier(String identifier);

    List<VideoMessage> getMessages();

    void updateMessage(VideoMessage eStoreMessage, Map<String, String> otherDetails);
}
