package com.laotek.churchguru.web.clientm.activity.notice.titles;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class GetNoticeAndEventTitlesResult implements Result {

    private List<NoticeAndEventDto> messages;

    private String newsTypeLabel;

    public GetNoticeAndEventTitlesResult() {
    }

    public GetNoticeAndEventTitlesResult(String newsTypeLabel,
	    List<NoticeAndEventDto> messages) {
	this.messages = messages;
	this.setNewsTypeLabel(newsTypeLabel);
    }

    public List<NoticeAndEventDto> getMessages() {
	return messages;
    }

    public void setMessages(List<NoticeAndEventDto> messages) {
	this.messages = messages;
    }

    public String getNewsTypeLabel() {
	return newsTypeLabel;
    }

    public void setNewsTypeLabel(String newsTypeLabel) {
	this.newsTypeLabel = newsTypeLabel;
    }
}
