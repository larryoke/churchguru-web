package com.laotek.churchguru.daos.media;

import java.util.List;

import com.laotek.churchguru.model.YoutubeVideo;

public interface YoutubeVideoDao {
    void createNewMessage(String messageId, String title);

    YoutubeVideo getMessageByIdentifier(String identifier);

    List<YoutubeVideo> getMessages();

    void updateMessage(YoutubeVideo eStoreMessage);
}
