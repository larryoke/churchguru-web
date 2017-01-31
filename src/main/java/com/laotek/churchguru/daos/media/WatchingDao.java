package com.laotek.churchguru.daos.media;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.WatchingMessage;

public interface WatchingDao {
    void createNewMessage(String messageId, String title);

    WatchingMessage getMessageByIdentifier(String identifier);

    List<WatchingMessage> getMessages();

    void updateMessage(WatchingMessage eStoreMessage, Map<String, String> otherDetails);
}
